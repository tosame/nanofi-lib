/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.exception.DimensionMismatchException;
import nanofi.net.la.VectorBase;

/**
 *
 */
public interface Function {

	public int getDimension();
	
	/**
	 * calculate the function value.
	 * @param vector input vector
	 * @return
	 * @throws DimensionMismatchException
	 */
	public double functionValue(VectorBase vector);
}
