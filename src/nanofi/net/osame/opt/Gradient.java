/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.la.VectorBase;

/**
 *
 */
public interface Gradient extends Function {
	
	/**
	 * calculate the gradient of function.
	 * @param vector
	 * @return
	 */
	public VectorBase gradient(VectorBase vector);
}
