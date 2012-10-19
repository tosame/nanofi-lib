/**
 * 
 */
package nanofi.net.osame.opt;

import java.util.List;

import nanofi.net.exception.DimensionMismatchException;
import nanofi.net.la.VectorBase;

/**
 *
 */
public interface FunctionOptimizable {
	
	/**
	 * 
	 * @param function
	 * @param initialVector
	 * @return
	 * @throws DimensionMismatchException
	 */
	public List<? extends VectorBase> optimize(Gradient function, VectorBase initialVector);
	
}
