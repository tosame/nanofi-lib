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
		VectorBase previousGradient = null;
		Vector searchDirection = assign(mul(-1d, gradient), Vector.class);
		while (!condition.converged(t, gradientNorm)) {
			t ++;
			double stepSize = lineSearch.searchStepSize(function, vector, searchDirection);
			vector = assign(add(vector, mul(stepSize, searchDirection)), Vector.class);
			vectors.add(vector);
			
			previousGradient = gradient;
			gradient = function.gradient(vector);
			double beta = decideBeta(gradient, previousGradient);
			searchDirection = assign(add(mul(-1d, gradient), mul(beta, searchDirection)), Vector.class);
			gradientNorm = Math.sqrt(mul(t(gradient), gradient));
		}
		
		return vectors;
	}

	
}
