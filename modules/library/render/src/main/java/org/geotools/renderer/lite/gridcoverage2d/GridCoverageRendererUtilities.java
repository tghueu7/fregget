/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2015, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.renderer.lite.gridcoverage2d;

import com.sun.media.jai.util.Rational;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.util.List;
import javax.media.jai.Interpolation;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridCoverageFactory;
import org.geotools.coverage.grid.GridEnvelope2D;
import org.geotools.coverage.grid.GridGeometry2D;
import org.geotools.coverage.processing.CoverageProcessor;
import org.geotools.coverage.processing.operation.Crop;
import org.geotools.coverage.processing.operation.Mosaic;
import org.geotools.coverage.processing.operation.Resample;
import org.geotools.geometry.Envelope2D;
import org.geotools.geometry.GeneralEnvelope;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.renderer.i18n.ErrorKeys;
import org.geotools.renderer.i18n.Errors;
import org.geotools.util.factory.Hints;
import org.huldra.math.BigInt;
import org.opengis.coverage.grid.GridEnvelope;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;

/** @author Simone Giannecchini, GeoSolutions */
final class GridCoverageRendererUtilities {

    private static final int MAX_TERMS = 20;

    private static final CoverageProcessor processor =
            CoverageProcessor.getInstance(new Hints(Hints.LENIENT_DATUM_SHIFT, Boolean.TRUE));

    // FORMULAE FOR FORWARD MAP are derived as follows
    //     Nearest
    //        Minimum:
    //            srcMin = floor ((dstMin + 0.5 - trans) / scale)
    //            srcMin <= (dstMin + 0.5 - trans) / scale < srcMin + 1
    //            srcMin*scale <= dstMin + 0.5 - trans < (srcMin + 1)*scale
    //            srcMin*scale - 0.5 + trans
    //                       <= dstMin < (srcMin + 1)*scale - 0.5 + trans
    //            Let A = srcMin*scale - 0.5 + trans,
    //            Let B = (srcMin + 1)*scale - 0.5 + trans
    //
    //            dstMin = ceil(A)
    //
    //        Maximum:
    //            Note that srcMax is defined to be srcMin + dimension - 1
    //            srcMax = floor ((dstMax + 0.5 - trans) / scale)
    //            srcMax <= (dstMax + 0.5 - trans) / scale < srcMax + 1
    //            srcMax*scale <= dstMax + 0.5 - trans < (srcMax + 1)*scale
    //            srcMax*scale - 0.5 + trans
    //                       <= dstMax < (srcMax+1) * scale - 0.5 + trans
    //            Let float A = (srcMax + 1) * scale - 0.5 + trans
    //
    //            dstMax = floor(A), if floor(A) < A, else
    //            dstMax = floor(A) - 1
    //            OR dstMax = ceil(A - 1)
    //
    //     Other interpolations
    //
    //        First the source should be shrunk by the padding that is
    //        required for the particular interpolation. Then the
    //        shrunk source should be forward mapped as follows:
    //
    //        Minimum:
    //            srcMin = floor (((dstMin + 0.5 - trans)/scale) - 0.5)
    //            srcMin <= ((dstMin + 0.5 - trans)/scale) - 0.5 < srcMin+1
    //            (srcMin+0.5)*scale <= dstMin+0.5-trans <
    //                                                  (srcMin+1.5)*scale
    //            (srcMin+0.5)*scale - 0.5 + trans
    //                       <= dstMin < (srcMin+1.5)*scale - 0.5 + trans
    //            Let A = (srcMin+0.5)*scale - 0.5 + trans,
    //            Let B = (srcMin+1.5)*scale - 0.5 + trans
    //
    //            dstMin = ceil(A)
    //
    //        Maximum:
    //            srcMax is defined as srcMin + dimension - 1
    //            srcMax = floor (((dstMax + 0.5 - trans) / scale) - 0.5)
    //            srcMax <= ((dstMax + 0.5 - trans)/scale) - 0.5 < srcMax+1
    //            (srcMax+0.5)*scale <= dstMax + 0.5 - trans <
    //                                                   (srcMax+1.5)*scale
    //            (srcMax+0.5)*scale - 0.5 + trans
    //                       <= dstMax < (srcMax+1.5)*scale - 0.5 + trans
    //            Let float A = (srcMax+1.5)*scale - 0.5 + trans
    //
    //            dstMax = floor(A), if floor(A) < A, else
    //            dstMax = floor(A) - 1
    //            OR dstMax = ceil(A - 1)
    //
    private static float RATIONAL_TOLERANCE = 0.000001F;
    /**
     * Checks whether the provided object is null or not. If it is null it throws an {@link
     * IllegalArgumentException} exception.
     *
     * @param source the object to check.
     * @param name the operation we are trying to run.
     */
    static void ensureNotNull(final Object source, final String name) {
        if (source == null)
            throw new IllegalArgumentException(Errors.format(ErrorKeys.NULL_ARGUMENT_$1, name));
    }

    /**
     * Checks whether the provided source object is null or not. If it is null it throws an {@link
     * IllegalArgumentException} exception.
     *
     * @param source the object to check.
     * @param name the operation we are trying to run.
     */
    static void ensureSourceNotNull(final Object source, final String name) {
        if (source == null)
            throw new IllegalArgumentException(
                    Errors.format(ErrorKeys.SOURCE_CANT_BE_NULL_$1, name));
    }

    static Rectangle2D layoutHelper(
            RenderedImage source,
            float scaleX,
            float scaleY,
            float transX,
            float transY,
            Interpolation interp) {

        // Represent the scale factors as Rational numbers.
        // Since a value of 1.2 is represented as 1.200001 which
        // throws the forward/backward mapping in certain situations.
        // Convert the scale and translation factors to Rational numbers
        Rational scaleXRational = approximate(scaleX, RATIONAL_TOLERANCE);
        Rational scaleYRational = approximate(scaleY, RATIONAL_TOLERANCE);

        BigInt scaleXRationalNum = new BigInt(scaleXRational.num);
        BigInt scaleXRationalDenom = new BigInt(scaleXRational.denom);
        BigInt scaleYRationalNum = new BigInt(scaleYRational.num);
        BigInt scaleYRationalDenom = new BigInt(scaleYRational.denom);

        Rational transXRational = approximate(transX, RATIONAL_TOLERANCE);
        Rational transYRational = approximate(transY, RATIONAL_TOLERANCE);

        BigInt transXRationalNum = new BigInt(transXRational.num);
        BigInt transXRationalDenom = new BigInt(transXRational.denom);
        BigInt transYRationalNum = new BigInt(transYRational.num);
        BigInt transYRationalDenom = new BigInt(transYRational.denom);

        int x0 = source.getMinX();
        int y0 = source.getMinY();
        int w = source.getWidth();
        int h = source.getHeight();

        // Variables to store the calculated destination upper left coordinate
        BigInt dx0Num, dx0Denom, dy0Num, dy0Denom;

        // Variables to store the calculated destination bottom right
        // coordinate
        BigInt dx1Num, dx1Denom, dy1Num, dy1Denom;

        // Start calculations for destination
        dx0Num = new BigInt(x0);
        dx0Denom = new BigInt(1);

        dy0Num = new BigInt(y0);
        dy0Denom = new BigInt(1);

        // Formula requires srcMaxX + 1 = (x0 + w - 1) + 1 = x0 + w
        dx1Num = new BigInt(x0 + w);
        dx1Denom = new BigInt(1);

        // Formula requires srcMaxY + 1 = (y0 + h - 1) + 1 = y0 + h
        dy1Num = new BigInt(y0 + h);
        dy1Denom = new BigInt(1);

        dx0Num.mul(scaleXRationalNum);
        dx0Denom.mul(scaleXRationalDenom);

        dy0Num.mul(scaleYRationalNum);
        dy0Denom.mul(scaleYRationalDenom);

        dx1Num.mul(scaleXRationalNum);
        dx1Denom.mul(scaleXRationalDenom);

        dy1Num.mul(scaleYRationalNum);
        dy1Denom.mul(scaleYRationalDenom);

        // Equivalent to subtracting 0.5
        dx0Num.mul(2);
        dx0Num.sub(dx0Denom);
        dx0Denom.mul(2);

        dy0Num.mul(2);
        dy0Num.sub(dy0Denom);
        dy0Denom.mul(2);

        // Equivalent to subtracting 1.5
        dx1Num = subnew(mulnew(dx1Num, 2), mulnew(dx1Denom, 3));
        dx1Denom.mul(2);

        dy1Num = subnew(mulnew(dy1Num, 2), mulnew(dy1Denom, 3));
        dy1Denom.mul(2);

        // Adding translation factors

        // Equivalent to float dx0 += transX
        dx0Num.mul(transXRationalDenom);
        dx0Num.add(mulnew(transXRationalNum, dx0Denom));
        dx0Denom.mul(transXRationalDenom);

        // Equivalent to float dy0 += transY
        dy0Num.mul(transYRationalDenom);
        dy0Num.add(mulnew(transYRationalNum, dy0Denom));
        dy0Denom.mul(transYRationalDenom);

        // Equivalent to float dx1 += transX
        dx1Num.mul(transXRationalDenom);
        dx1Num.add(mulnew(transXRationalNum, dx1Denom));
        dx1Denom.mul(transXRationalDenom);

        // Equivalent to float dy1 += transY
        dy1Num.mul(transYRationalDenom);
        dy1Num.add(mulnew(transYRationalNum, dy1Denom));
        dy1Denom.mul(transYRationalDenom);

        // Get the integral coordinates
        int l_x0, l_y0, l_x1, l_y1;

        // We may want to not use Rational.ceil and use BigInts again
        l_x0 = Rational.ceil(dx0Num.longValue(), dx0Denom.longValue());
        l_y0 = Rational.ceil(dy0Num.longValue(), dy0Denom.longValue());

        l_x1 = Rational.ceil(dx1Num.longValue(), dx1Denom.longValue());
        l_y1 = Rational.ceil(dy1Num.longValue(), dy1Denom.longValue());

        // Set the top left coordinate of the destination
        final Rectangle2D retValue = new Rectangle2D.Double();
        retValue.setFrame(l_x0, l_y0, l_x1 - l_x0 + 1, l_y1 - l_y0 + 1);
        return retValue;
    }

    /** Returns a Rational that is within the given tolerance of a given float value. */
    static Rational approximate(float f, float tol) {
        // Expand f as a continued fraction by repeatedly removing the integer
        // part and inverting.
        double rem = f;
        long[] d = new long[MAX_TERMS];
        int index = 0;
        for (int i = 0; i < MAX_TERMS; i++) {
            long k = (long) Math.floor(rem);
            d[index++] = k;

            rem -= k;
            if (rem == 0) {
                break;
            }
            rem = 1.0F / rem;
        }

        // Evaluate with increasing number of terms until the tolerance
        // has been reached
        Rational r = null;
        for (int i = 1; i <= index; i++) {
            r = Rational.createFromFrac(d, i);
            if (Math.abs(r.floatValue() - f) < tol) {
                return r;
            }
        }

        return r;
    }

    protected static BigInt subnew(BigInt a, BigInt b) {
        BigInt result = a.copy();
        result.sub(b);
        return result;
    }

    protected static BigInt mulnew(BigInt a, BigInt b) {
        BigInt result = a.copy();
        result.mul(b);
        return result;
    }

    protected static BigInt mulnew(BigInt a, int b) {
        BigInt result = a.copy();
        result.mul(b);
        return result;
    }

    /**
     * Reprojecting the input coverage using the provided parameters.
     *
     * @param gc
     * @param crs
     * @param interpolation
     * @return
     * @throws FactoryException
     */
    static GridCoverage2D reproject(
            final GridCoverage2D gc,
            CoordinateReferenceSystem crs,
            final Interpolation interpolation,
            final GeneralEnvelope destinationEnvelope,
            final double bkgValues[],
            final Hints hints)
            throws FactoryException {
        // paranoiac check
        assert CRS.equalsIgnoreMetadata(destinationEnvelope.getCoordinateReferenceSystem(), crs)
                || CRS.findMathTransform(destinationEnvelope.getCoordinateReferenceSystem(), crs)
                        .isIdentity();

        final ParameterValueGroup param =
                processor.getOperation("Resample").getParameters().clone();
        param.parameter("source").setValue(gc);
        param.parameter("CoordinateReferenceSystem").setValue(crs);
        param.parameter("InterpolationType").setValue(interpolation);
        if (bkgValues != null) {
            param.parameter("BackgroundValues").setValue(bkgValues);
        }
        return (GridCoverage2D)
                ((Resample) processor.getOperation("Resample")).doOperation(param, hints);
    }

    /**
     * Cropping the provided coverage to the requested geographic area.
     *
     * @param gc
     * @param envelope
     * @param crs
     * @return
     */
    static GridCoverage2D crop(
            GridCoverage2D gc, GeneralEnvelope envelope, double[] background, final Hints hints) {
        final GeneralEnvelope oldEnvelope = (GeneralEnvelope) gc.getEnvelope();
        // intersect the envelopes in order to prepare for cropping the coverage
        // down to the neded resolution
        final GeneralEnvelope intersectionEnvelope = new GeneralEnvelope(envelope);
        intersectionEnvelope.setCoordinateReferenceSystem(envelope.getCoordinateReferenceSystem());
        intersectionEnvelope.intersect(oldEnvelope);

        // Do we have something to show? After the crop I could get a null
        // coverage which would mean nothing to show.
        if (intersectionEnvelope.isEmpty()) {
            return null;
        }

        // crop
        final ParameterValueGroup param =
                processor.getOperation("CoverageCrop").getParameters().clone();
        param.parameter("source").setValue(gc);
        param.parameter("Envelope").setValue(intersectionEnvelope);
        return (GridCoverage2D)
                ((Crop) processor.getOperation("CoverageCrop")).doOperation(param, hints);
    }

    static GridCoverage2D displace(
            GridCoverage2D coverage,
            double tx,
            double ty,
            GridCoverageFactory gridCoverageFactory) {
        // let's compute the new grid geometry
        GridGeometry2D originalGG = coverage.getGridGeometry();
        GridEnvelope gridRange = originalGG.getGridRange();
        Envelope2D envelope = originalGG.getEnvelope2D();

        double minx = envelope.getMinX() + tx;
        double miny = envelope.getMinY() + ty;
        double maxx = envelope.getMaxX() + tx;
        double maxy = envelope.getMaxY() + ty;
        ReferencedEnvelope translatedEnvelope =
                new ReferencedEnvelope(
                        minx, maxx, miny, maxy, envelope.getCoordinateReferenceSystem());

        GridGeometry2D translatedGG = new GridGeometry2D(gridRange, translatedEnvelope);

        GridCoverage2D translatedCoverage =
                gridCoverageFactory.create(
                        coverage.getName(),
                        coverage.getRenderedImage(),
                        translatedGG,
                        coverage.getSampleDimensions(),
                        new GridCoverage2D[] {coverage},
                        coverage.getProperties());
        return translatedCoverage;
    }

    /**
     * Mosaicking the provided coverages to the requested geographic area.
     *
     * @param alphas
     * @param background
     * @param gc
     * @param envelope
     * @param crs
     * @return
     */
    static GridCoverage2D mosaic(
            List<GridCoverage2D> coverages,
            List<GridCoverage2D> alphas,
            GeneralEnvelope renderingEnvelope,
            final Hints hints,
            double[] background) {

        // setup the grid geometry
        try {
            // find the intersection between the target envelope and the coverages one
            ReferencedEnvelope targetEnvelope = ReferencedEnvelope.reference(renderingEnvelope);
            ReferencedEnvelope coveragesEnvelope = null;
            for (GridCoverage2D coverage : coverages) {
                ReferencedEnvelope re = ReferencedEnvelope.reference(coverage.getEnvelope2D());
                if (coveragesEnvelope == null) {
                    coveragesEnvelope = re;
                } else {
                    coveragesEnvelope.expandToInclude(re);
                }
            }
            targetEnvelope =
                    new ReferencedEnvelope(
                            targetEnvelope.intersection(coveragesEnvelope),
                            renderingEnvelope.getCoordinateReferenceSystem());
            if (targetEnvelope.isEmpty() || targetEnvelope.isNull()) {
                return null;
            }

            MathTransform2D mt = coverages.get(0).getGridGeometry().getCRSToGrid2D();
            Rectangle rasterSpaceEnvelope;
            rasterSpaceEnvelope = CRS.transform(mt, targetEnvelope).toRectangle2D().getBounds();
            GridEnvelope2D gridRange = new GridEnvelope2D(rasterSpaceEnvelope);
            GridGeometry2D gridGeometry = new GridGeometry2D(gridRange, targetEnvelope);

            // mosaic
            final ParameterValueGroup param =
                    processor.getOperation("Mosaic").getParameters().clone();
            param.parameter("sources").setValue(coverages);
            param.parameter("geometry").setValue(gridGeometry);

            if (background != null) {
                param.parameter(Mosaic.OUTNODATA_NAME).setValue(background);
            }
            if (!alphas.isEmpty()) {
                param.parameter(Mosaic.ALPHA_NAME).setValue(alphas);
            }
            return (GridCoverage2D)
                    ((Mosaic) processor.getOperation("Mosaic")).doOperation(param, hints);
        } catch (Exception e) {
            throw new RuntimeException("Failed to mosaic the input coverages", e);
        }
    }

    /**
     * @param inputEnvelope
     * @return
     * @throws Exception
     */
    static GeneralEnvelope reprojectEnvelope(
            GeneralEnvelope inputEnvelope, final CoordinateReferenceSystem outputCRS)
            throws Exception {

        GeneralEnvelope outputEnvelope = null;
        CoordinateReferenceSystem inputCRS = inputEnvelope.getCoordinateReferenceSystem();
        if (!CRS.equalsIgnoreMetadata(inputCRS, outputCRS)) {
            outputEnvelope = CRS.transform(inputEnvelope, outputCRS);
            outputEnvelope.setCoordinateReferenceSystem(outputCRS);
        }
        // simple copy
        if (outputEnvelope == null) {
            outputEnvelope = new GeneralEnvelope(inputEnvelope);
            outputEnvelope.setCoordinateReferenceSystem(inputCRS);
        }
        return null;
    }

    /**
     * @param inputEnvelope
     * @param targetCRS
     * @return
     * @throws Exception
     */
    static GeneralEnvelope reprojectEnvelopeWithWGS84Pivot(
            GeneralEnvelope inputEnvelope, CoordinateReferenceSystem targetCRS) throws Exception {

        GridCoverageRendererUtilities.ensureNotNull(inputEnvelope, "destinationEnvelope");
        GridCoverageRendererUtilities.ensureNotNull(targetCRS, "coverageCRS");
        final CoordinateReferenceSystem destinationCRS =
                inputEnvelope.getCoordinateReferenceSystem();

        // //
        //
        // Try to convert the destination envelope in the source crs. If
        // this fails we pass through WGS84 as an intermediate step
        //
        // //
        try {
            // convert the destination envelope to the source coverage
            // native crs in order to try and crop it. If we get an error we
            // try to
            // do this in two steps using WGS84 as a pivot. This introduces
            // some erros (it usually
            // increases the envelope we want to check) but it is still
            // useful.
            CoordinateOperation operation =
                    CRS.getCoordinateOperationFactory(true)
                            .createOperation(destinationCRS, targetCRS);
            GeneralEnvelope output = CRS.transform(operation, inputEnvelope);
            output.setCoordinateReferenceSystem(targetCRS);
            return output;
        } catch (TransformException te) {
            // //
            //
            // Convert the destination envelope to WGS84 if needed for safer
            // comparisons later on with the original crs of this coverage.
            //
            // //
            final GeneralEnvelope destinationEnvelopeWGS84 =
                    GridCoverageRendererUtilities.reprojectEnvelope(
                            inputEnvelope, DefaultGeographicCRS.WGS84);

            // //
            //
            // Convert the destination envelope from WGS84 to the source crs
            // for cropping the provided coverage.
            //
            // //
            return GridCoverageRendererUtilities.reprojectEnvelope(
                    destinationEnvelopeWGS84, targetCRS);
        }
    }

    /**
     * @param color
     * @return
     */
    public static double[] colorToArray(Color color) {
        if (color == null) {
            return null;
        }

        return new double[] {color.getRed(), color.getGreen(), color.getBlue()};
    }
}
