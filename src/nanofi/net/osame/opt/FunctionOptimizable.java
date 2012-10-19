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
	 * @param initialParameter
	 * @return
	 * @throws DimensionMismatchException
	 */
	public VectorBase optimize(Gradient function, VectorBase initialParameter);
	
	/**
	 * 
	 * @param function
	 * @param initialParameter
	 * @return
	 * @throws DimensionMismatchException
	 */
	public List<? extends VectorBase> optimizeForDebug(Gradient function, VectorBase initialParameter);
}
