/**
 * 
 */
package nanofi.net.osame.opt;

import java.util.ArrayList;
import java.util.List;

import nanofi.net.exception.DimensionMismatchException;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class ConjugateGradientMethod extends Operable implements FunctionOptimizable {
	
	private LineSearch lineSearch;
	private ConvergenceCondition condition;

	/**
	 * Constructor.
	 * @param lineSearch
	 * @param condition
	 */
	public ConjugateGradientMethod(LineSearch lineSearch, ConvergenceCondition condition) {
		this.lineSearch = lineSearch;
		this.condition = condition;
	}

	/**
	 * 
	 * @param vector
	 * @return
	 */
	private double squaredNorm(VectorBase vector) {
		return mul(t(vector), vector);
	}
	
	/**
	 * Nanかえすかも
	 * @param currentGradient
	 * @param previousGradient
	 * @return
	 */
	private double decideBeta(VectorBase currentGradient, VectorBase previousGradient) {
		
		// Fletcher-Reeves
		double tmp = squaredNorm(previousGradient);
		double beta = squaredNorm(currentGradient);
		if (tmp == 0d) {
			beta = Double.NaN;
		} else {
			beta /= tmp;
		}
		
		
		return beta;
	}
	
	
	
	@Override
	public VectorBase optimize(Gradient function, VectorBase initialParameter) {
		if (function.getDimension() != initialParameter.size()) {
			throw new DimensionMismatchException();
		}
		
		VectorBase parameter = initialParameter;
		
		int t = 0;
		VectorBase previousGradient = null;
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (!condition.converged(t, previousValue - currentValue)) {
			t ++;
			VectorBase gradient = function.gradient(parameter);
			
			parameter = updateParameter(parameter, gradient, previousGradient, function);
			
			previousGradient = gradient;
			previousValue = currentValue;
			currentValue = function.functionValue(parameter);
		}
		return parameter;
	}

	@Override
	public List<? extends VectorBase> optimizeForDebug(Gradient function, VectorBase initialParameter) {
		if (function.getDimension() != initialParameter.size()) {
			throw new DimensionMismatchException();
		}
		
		VectorBase parameter = initialParameter;
		List<VectorBase> parameters = new ArrayList<>();
		parameters.add(parameter);
		
		int t = 0;
		VectorBase previousGradient = null;
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (!condition.converged(t, previousValue - currentValue)) {
			t ++;
			
			VectorBase gradient = function.gradient(parameter);
			
			parameter = updateParameter(parameter, gradient, previousGradient, function);
			parameters.add(parameter);
			
			previousGradient = gradient;
			previousValue = currentValue;
			currentValue = function.functionValue(parameter);
		}
		
		return parameters;
	}

	private VectorBase updateParameter(VectorBase currentParameter, 
			VectorBase currentGradient, VectorBase previousGradient, Gradient function) {
		VectorBase searchDirection = mul(-1d, currentGradient);
		if (previousGradient != null) {
			double beta = decideBeta(currentGradient, previousGradient);
			searchDirection = add(searchDirection, mul(beta, searchDirection));
		}
		double stepSize = lineSearch.searchStepSize(function, currentParameter, searchDirection);
		return assign(add(currentParameter, mul(stepSize, searchDirection)), Vector.class);
	}
}
