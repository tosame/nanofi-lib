/**
 * 
 */
package nanofi.net.osame.opt;

import nanofi.net.la.Operable;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class WolfeLineSearch extends Operable implements LineSearch {

	private static final double DEFAULT_XI1 = 0.0001;
	private static final double DEFAULT_XI2 = 0.0001;
	private static final double DEFAULT_TAU = 0.5;
	
	private double xi1;
	private double xi2;
	private double tau;
	
	
	
	
	
	public WolfeLineSearch() {
		this.xi1 = DEFAULT_XI1;
		this.xi2 = DEFAULT_XI2;
		this.tau = DEFAULT_TAU;
	}
	
	/**
	 * Constructor.
	 * @param xi1
	 * @param xi2
	 * @param tau
	 */
	public WolfeLineSearch(double xi1, double xi2, double tau) {
		
		if (!checkValue(xi1) || !checkValue(xi2) || !checkValue(tau)) {
			throw new IllegalArgumentException("The arguments must be in range of (0, 1).");
		}
		
		this.xi1 = xi1;
		this.xi2 = xi2;
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
		
		
		double firstConditionLeft = function.functionValue(add(parameter, mul(beta, searchDirection)));
		double firstConditionRight = functionValue + xi1 * beta * innerValue;
		
		double secondConditionLeft = mul(t(function.gradient(add(parameter, mul(beta, searchDirection)))), searchDirection);
		double secondConditionRight = xi2 * innerValue;
		
		while (!(firstConditionLeft <= firstConditionRight) || !(secondConditionLeft <= secondConditionRight)) {
			
			beta *= tau;
			if (beta == 0d) break;
			
			firstConditionLeft = function.functionValue(add(parameter, mul(beta, searchDirection)));
			firstConditionRight = functionValue + xi1 * beta * innerValue;
			
			secondConditionLeft = mul(t(function.gradient(add(parameter, mul(beta, searchDirection)))), searchDirection);
		}
		
		return beta;
	}

}
