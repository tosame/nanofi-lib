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
public class SteepestDescentMethod extends Operable implements FunctionOptimizable {

	private LineSearch lineSearch;
	private ConvergenceCondition condition;
	
	
	
	/**
	 * Constructor.
	 * @param lineSearch
	 * @param condition
	 */
	public SteepestDescentMethod(LineSearch lineSearch, ConvergenceCondition condition) {
		this.lineSearch = lineSearch;
		this.condition = condition;
	}
	
	@Override
	public VectorBase optimize(Gradient function, VectorBase initialParameter) {
		if (function.getDimension() != initialParameter.size()) {
			throw new DimensionMismatchException();
		}
		
		VectorBase parameter = initialParameter;
		List<VectorBase> parameters = new ArrayList<>();
		parameters.add(parameter);
		
		int t = 0;
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (!condition.converged(t, previousValue - currentValue)) {
			t ++;
			parameter = updateParameter(parameter, function);
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
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (!condition.converged(t, previousValue - currentValue)) {
			t ++;
			parameter = updateParameter(parameter, function);
			parameters.add(parameter);
			previousValue = currentValue;
			currentValue = function.functionValue(parameter);
		}
		
		return parameters;
	}
	
	private VectorBase updateParameter(VectorBase currentParameter, Gradient function) {
		VectorBase searchDirection = mul(-1d, function.gradient(currentParameter));
		double stepSize = lineSearch.searchStepSize(function, currentParameter, searchDirection);
		return assign(add(currentParameter, mul(stepSize, searchDirection)), Vector.class);
	}
}
