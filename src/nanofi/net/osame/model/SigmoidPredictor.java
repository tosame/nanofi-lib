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
public class SigmoidPredictor extends Operable implements Predictable {

	private double gain;
	private InnerPredictor innerPredictor;
	
	
	public SigmoidPredictor() {
		this(1d);
	}
	
	public SigmoidPredictor(double gain) {
		this.gain = gain;
		this.innerPredictor = new InnerPredictor();
	}
	
	@Override
	public double predict(VectorBase parameter, VectorBase data) {
		double prediction = 1 / (1 + Math.exp(-gain * innerPredictor.predict(parameter, data)));
		return prediction;
	}

	@Override
	public VectorBase gradient(VectorBase parameter, VectorBase data) {
		double prediction = predict(parameter, data);
		double coefficient = gain * prediction * (1 - prediction);
		return assign(mul(coefficient, data), Vector.class);
	}

}
