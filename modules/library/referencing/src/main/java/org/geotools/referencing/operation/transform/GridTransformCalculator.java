package org.geotools.referencing.operation.transform;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;

public class GridTransformCalculator {

    /**
     * Used to compare numbers to 0 and integers in general
     */
    static final double EPS = 1e-6;

    /**
     * The max distance tolerated between the actual projected point and the approximate version
     * built by the optimized warp transform
     */
    final double maxDistanceSquared;

    /**
     * Maximum allowed transformation depth
     */
    int maxDepth = 20;

    /**
     * The array used to perform all the reprojections
     */
    final double[] ordinates = new double[10];

    public GridTransformCalculator(double maxDistance) {
        this.maxDistanceSquared = maxDistance * maxDistance;
    }

    /**
     * Performs recursive slicing of the area to find the optimal number of subdivisions along the x
     * and y axis.
     * 
     * @param mt
     * @param minx
     * @param maxx
     * @param miny
     * @param maxy
     */
    int[] computeOptimalDepths(MathTransform2D mt, double minx, double maxx, double miny,
            double maxy, int rowDepth, int colDepth) throws TransformException {
        if (maxx - minx < 4 || maxy - miny < 4) {
            throw new ExcessiveDepthException("Warp grid getting as dense as the original data",
                    rowDepth, colDepth);
        } else if (rowDepth + colDepth > 20) {
            // this would take 2^(20) points, way too much already
            throw new ExcessiveDepthException(
                    "Warp grid getting too large to fit in memory, bailing out", rowDepth,
                    colDepth);
        }

        // center of this rectangle
        final double midx = (minx + maxx) / 2;
        final double midy = (miny + maxy) / 2;

        // test tolerance along the y axis
        boolean withinTolVertical = isWithinTolerance(mt, minx, miny, minx, midy, minx, maxy)
                && isWithinTolerance(mt, maxx, miny, maxx, midy, maxx, maxy);
        // test tolerance along the x axis
        boolean withinTolHorizontal = isWithinTolerance(mt, minx, miny, midx, miny, maxx, miny)
                && isWithinTolerance(mt, minx, maxy, midx, maxy, maxx, maxy);
        // if needed, check tolerance along the diagonal as well
        if (withinTolVertical && withinTolHorizontal) {
            if (!isWithinTolerance(mt, minx, miny, midx, midy, maxx, maxy)
                    || !isWithinTolerance(mt, minx, maxy, midx, midy, maxx, miny)) {
                withinTolVertical = false;
                withinTolHorizontal = false;
            }
        }

        // check what kind of split are we going to make
        // (and try not to get fooled by symmetrical projections)
        if ((!withinTolHorizontal && !withinTolVertical)) {
            // quad split
            rowDepth++;
            colDepth++;
            int[] d1 = computeOptimalDepths(mt, minx, midx, miny, midy, rowDepth, colDepth);
            int[] d2 = computeOptimalDepths(mt, minx, midx, midy, maxy, rowDepth, colDepth);
            int[] d3 = computeOptimalDepths(mt, midx, maxx, miny, midy, rowDepth, colDepth);
            int[] d4 = computeOptimalDepths(mt, midx, maxx, midy, maxy, rowDepth, colDepth);
            return new int[] { max(max(d1[0], d2[0]), max(d3[0], d4[0])),
                    max(max(d1[1], d2[1]), max(d3[1], d4[1])) };
        } else if (!withinTolHorizontal) {
            // slice in two at midx (creating two more colums)
            colDepth++;
            int[] d1 = computeOptimalDepths(mt, minx, midx, miny, maxy, rowDepth, colDepth);
            int[] d2 = computeOptimalDepths(mt, midx, maxx, miny, maxy, rowDepth, colDepth);
            return new int[] { max(d1[0], d2[0]), max(d1[1], d2[1]) };
        } else if (!withinTolVertical) {
            // slice in two at midy (creating two rows)
            rowDepth++;
            int[] d1 = computeOptimalDepths(mt, minx, maxx, miny, midy, rowDepth, colDepth);
            int[] d2 = computeOptimalDepths(mt, minx, maxx, midy, maxy, rowDepth, colDepth);
            return new int[] { max(d1[0], d2[0]), max(d1[1], d2[1]) };
        }

        return new int[] { rowDepth, colDepth };
    }

    /**
     * Checks if the point predicted by a WarpGrid between the specified points
     * 
     * @param mt
     * @param minx
     * @param miny
     * @param minx2
     * @param midy
     * @param minx3
     * @param maxy
     * @return
     */
    boolean isWithinTolerance(MathTransform2D mt, double x1, double y1, double x2, double y2,
            double x3, double y3) throws TransformException {
        // transform the points (use two extra points at quarter distance to avoid being
        // fooled by symmetrical projections
        ordinates[0] = x1;
        ordinates[1] = y1;
        ordinates[2] = (x1 + x2) / 2;
        ordinates[3] = (y1 + y2) / 2;
        ordinates[4] = x2;
        ordinates[5] = y2;
        ordinates[6] = (x2 + x3) / 2;
        ordinates[7] = (y2 + y3) / 2;
        ordinates[8] = x3;
        ordinates[9] = y3;
        mt.transform(ordinates, 0, ordinates, 0, 5);

        boolean withinTolerance = true;
        for (int i = 1; i < 4 && withinTolerance; i++) {
            // apply to local variables for readability
            final double tx1 = ordinates[0];
            final double ty1 = ordinates[1];
            final double tx2 = ordinates[i * 2];
            final double ty2 = ordinates[i * 2 + 1];
            final double tx3 = ordinates[8];
            final double ty3 = ordinates[9];

            // check the differences
            double dx = 0;
            if (abs(x3 - x1) > EPS) {
                double xmid;
                if (i == 1) {
                    xmid = (x1 + x2) / 2;
                } else if (i == 2) {
                    xmid = x2;
                } else {
                    xmid = (x2 + x3) / 2;
                }

                dx = tx2 - (tx3 - tx1) / (x3 - x1) * (xmid - x1) - tx1;
            }
            double dy = 0;
            if (abs(y3 - y1) > EPS) {
                double ymid;
                if (i == 1) {
                    ymid = (y1 + y2) / 2;
                } else if (i == 2) {
                    ymid = y2;
                } else {
                    ymid = (y2 + y3) / 2;
                }
                dy = ty2 - (ty3 - ty1) / (y3 - y1) * (ymid - y1) - ty1;
            }

            // see if the total distance between predicted and actual is lower than the tolerance
            final double distance = dx * dx + dy * dy;
            withinTolerance &= distance < maxDistanceSquared;
        }

        return withinTolerance;
    }

    /**
     * Convenience exception to bail out when the grid evaluation code gets too deep
     * 
     * @author Andrea Aime - GeoSolutions
     */
    class ExcessiveDepthException extends RuntimeException {
        private static final long serialVersionUID = -3533898904532522502L;

        int rowDepth, colDepth;

        public ExcessiveDepthException(String message, int rowDepth, int colDepth) {
            super(message);
            this.rowDepth = rowDepth;
            this.colDepth = colDepth;
        }

        /**
         * The rowDepth at which the exception was thrown
         * 
         * @return the rowDepth
         */
        public int getRowDepth() {
            return rowDepth;
        }

        /**
         * The colDepth at which the exception was thrown
         * 
         * @return the colDepth
         */
        public int getColDepth() {
            return colDepth;
        }

    }

}
