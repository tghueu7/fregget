package org.geotools.renderer.crs;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;
import org.opengis.referencing.operation.TransformException;

public class GeographicOffsetWrapper implements MathTransform {

	MathTransform delegate;

	public int getSourceDimensions() {
		return delegate.getSourceDimensions();
	}

	public int getTargetDimensions() {
		return delegate.getTargetDimensions();
	}

	public DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst)
			throws MismatchedDimensionException, TransformException {
		return delegate.transform(ptSrc, ptDst);
	}

	public void transform(double[] srcPts, int srcOff, double[] dstPts,
			int dstOff, int numPts) throws TransformException {
		delegate.transform(srcPts, srcOff, dstPts, dstOff, numPts);
	}

	public void transform(float[] srcPts, int srcOff, float[] dstPts,
			int dstOff, int numPts) throws TransformException {
		delegate.transform(srcPts, srcOff, dstPts, dstOff, numPts);
	}

	public void transform(float[] srcPts, int srcOff, double[] dstPts,
			int dstOff, int numPts) throws TransformException {
		delegate.transform(srcPts, srcOff, dstPts, dstOff, numPts);
	}

	public void transform(double[] srcPts, int srcOff, float[] dstPts,
			int dstOff, int numPts) throws TransformException {
		delegate.transform(srcPts, srcOff, dstPts, dstOff, numPts);
	}

	public Matrix derivative(DirectPosition point)
			throws MismatchedDimensionException, TransformException {
		return delegate.derivative(point);
	}

	public MathTransform inverse() throws NoninvertibleTransformException {
		MathTransform inverse = delegate.inverse();
		if(inverse instanceof GeographicOffsetWrapper) {
			return inverse;
		} else {
			return new GeographicOffsetWrapper(inverse);
		}
	}

	public boolean isIdentity() {
		return delegate.isIdentity();
	}

	public String toWKT() throws UnsupportedOperationException {
		return delegate.toWKT();
	}
	
	
}
