/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.exception.DimensionMismatchException;
import nanofi.net.la.MatrixBase;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class QuadraticFunction extends Operable implements Gradient {

	private MatrixBase A;
	private VectorBase B;
	private double c;
	private int dimension;

	/**
	 * Constructor.
	 * 
	 * @param A
	 * @param constantVector
	 */
	public QuadraticFunction(MatrixBase A, VectorBase B, double c) {
		this.A = A;
		this.B = B;
		this.dimension = B.size();

		if (A.rows() != dimension || A.columns() != dimension) {
			throw new DimensionMismatchException();
		}
		
		this.c = c;
	}

	@Override
	public int getDimension() {
		return dimension;
	}

	@Override
	public double functionValue(VectorBase vector) {
		double functionValue = c;
		functionValue += mul(t(mul(vector, A)), vector);
		functionValue += mul(t(B), vector);
		return functionValue;
	}

	@Override
	public VectorBase gradient(VectorBase vector) {
		Vector gradient = assign(add(mul(2, mul(A, vector)), B), Vector.class);
		return gradient;
	}

}
