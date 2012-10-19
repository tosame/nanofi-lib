/**
 * 
 */
package nanofi.net.osame.model;

import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class InnerPredictor extends Operable implements Predictable {

	@Override
	public double predict(VectorBase parameter, VectorBase data) {
		return mul(t(parameter), data);
	}

	@Override
	public VectorBase gradient(VectorBase parameter, VectorBase data) {
		return assign(data, Vector.class);
	}

}
