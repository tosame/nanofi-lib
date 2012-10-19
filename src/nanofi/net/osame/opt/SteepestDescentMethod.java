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
	public List<? extends VectorBase> optimize(Gradient function, VectorBase initialVector) {
		if (function.getDimension() != initialVector.size()) {
			throw new DimensionMismatchException();
		}
		
		VectorBase vector = initialVector;
		List<VectorBase> vectors = new ArrayList<>();
		vectors.add(vector);
		
		int t = 0;
		VectorBase gradient = function.gradient(vector);
		double gradientNorm = Math.sqrt(mul(t(gradient), gradient));
		while (!condition.converged(t, gradientNorm)) {
			t ++;
			Vector searchDirection = assign(mul(-1d, gradient), Vector.class);
			double stepSize = lineSearch.searchStepSize(function, vector, searchDirection);
			vector = assign(add(vector, mul(stepSize, searchDirection)), Vector.class);
			vectors.add(vector);
			gradient = function.gradient(vector);
			gradientNorm = Math.sqrt(mul(t(gradient), gradient));
		}
		
		return vectors;
	}
}
