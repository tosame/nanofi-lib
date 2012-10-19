/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.la.VectorBase;

/**
 *
 */
public interface LineSearch {

	public double searchStepSize(Gradient function, VectorBase parameter, VectorBase searchDirection);
}
