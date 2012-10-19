/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.la.Operable;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class ArmijoLineSearch extends Operable implements LineSearch {

	private static final double DEFAULT_XI = 0.0001;
	private static final double DEFAULT_TAU = 0.5;
	
	private double xi;
	private double tau;

	
	/**
	 * generate instance using default parameters.(xi = 0.0001, xi = 0.5)
	 * Constructor.
	 */
	public ArmijoLineSearch() {
		this.xi = DEFAULT_XI;
		this.tau = DEFAULT_TAU;
	}
	
	public ArmijoLineSearch(double xi, double tau) {
		
		if (!checkValue(xi) || !checkValue(tau)) {
			throw new IllegalArgumentException("The arguments must be in range of (0, 1).");
		}
		
		this.xi = xi;
		this.tau = tau;
	}
	
	private boolean checkValue(double value) {
		if (0 < value && value <1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public double searchStepSize(Gradient function, VectorBase parameter, VectorBase searchDirection) {
		
		VectorBase gradientVector = function.gradient(parameter);
		double functionValue = function.functionValue(parameter);
		double innerValue = mul(t(gradientVector), searchDirection);
		double beta = 1d;
		
		while ((functionValue + xi * beta * innerValue) < function.functionValue(add(parameter, mul(beta, searchDirection)))) {
			beta *= tau;
		}
		
		return beta;
	}
}
