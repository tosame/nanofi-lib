/**
 * 
 */
package nanofi.net.osame.model;

import nanofi.net.la.VectorBase;

/**
 *
 */
public interface Predictable {

	public double predict(VectorBase parameter, VectorBase data);
	
	public VectorBase gradient(VectorBase parameter, VectorBase data);
}
