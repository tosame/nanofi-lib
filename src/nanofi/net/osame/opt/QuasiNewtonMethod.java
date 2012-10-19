/**
 * 
 */
package nanofi.net.osame.opt;

import java.util.ArrayList;
import java.util.List;

import nanofi.net.la.Matrix;
import nanofi.net.la.MatrixBase;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

/**
 *
 */
public class QuasiNewtonMethod extends Operable implements FunctionOptimizable {

	
	private LineSearch lineSearch;
	private ConvergenceCondition condition;
	
	
	/**
	 * Constructor.
	 * @param lineSearch
	 * @param condition
	 */
	public QuasiNewtonMethod(LineSearch lineSearch, ConvergenceCondition condition) {
		this.lineSearch = lineSearch;
		this.condition = condition;
	}


	@Override
	public VectorBase optimize(Gradient function, VectorBase initialParameter) {
		
		int dimension = function.getDimension();
		MatrixBase coefficientMatrix = generateUnitMatrix(dimension);
		VectorBase parameter = initialParameter;
		VectorBase gradient = function.gradient(parameter);
		
		VectorBase previousVector = null;
		VectorBase previousGradient = null;
		
		int t = 0;
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (true) {
			t ++;
			
			// update vectors
			previousVector = parameter;
			previousGradient = gradient;
			parameter = updateParameter(parameter, coefficientMatrix, gradient, function);
			gradient = function.gradient(parameter);
			
			previousValue = currentValue;
			currentValue = function.functionValue(parameter);
			
			// convergent check
			if (condition.converged(t, previousValue - currentValue)) {
				// convergent
				break;
			}
			
			// make difference vectors
			Vector sk = assign(sub(parameter, previousVector), Vector.class);
			Vector yk = assign(sub(gradient, previousGradient), Vector.class);
			if (isZeroVector(sk) || isZeroVector(yk)) {
				break;
			}
			
			// update coefficient matrix
			coefficientMatrix = updateCoefficientMatrix(coefficientMatrix, sk, yk);
		}
		
		return parameter;
	}
	
	@Override
	public List<? extends VectorBase> optimizeForDebug(Gradient function, VectorBase initialParameter) {
		int dimension = function.getDimension();
		MatrixBase coefficientMatrix = generateUnitMatrix(dimension);
		VectorBase parameter = initialParameter;
		VectorBase gradient = function.gradient(parameter);
		
		VectorBase previousVector = null;
		VectorBase previousGradient = null;
		
		List<VectorBase> parameters = new ArrayList<>();
		parameters.add(parameter);
		
		int t = 0;
		double previousValue = Double.MAX_VALUE;
		double currentValue = function.functionValue(parameter);
		while (true) {
			t ++;
			
			// update vectors
			previousVector = parameter;
			previousGradient = gradient;
			parameter = updateParameter(parameter, coefficientMatrix, gradient, function);
			parameters.add(parameter);
			gradient = function.gradient(parameter);
			
			previousValue = currentValue;
			currentValue = function.functionValue(parameter);
			
			// convergent check
			if (condition.converged(t, previousValue - currentValue)) {
				// convergent
				break;
			}
			
			// make difference vectors
			Vector sk = assign(sub(parameter, previousVector), Vector.class);
			Vector yk = assign(sub(gradient, previousGradient), Vector.class);
			if (isZeroVector(sk) || isZeroVector(yk)) {
				break;
			}
			
			// update coefficient matrix
			coefficientMatrix = updateCoefficientMatrix(coefficientMatrix, sk, yk);
		}
		
		return parameters;
	}


	private Vector updateParameter(VectorBase currentParameter, MatrixBase coefficientMatrix, 
			VectorBase gradient, Gradient function) {
		Vector searchDirection = mul(-1, mul(coefficientMatrix, gradient));
		double stepSize = lineSearch.searchStepSize(function, currentParameter, searchDirection);
		return assign(add(currentParameter, mul(stepSize, searchDirection)), Vector.class);
	}
	
	private Matrix updateCoefficientMatrix(MatrixBase currentMatrix, VectorBase sk, VectorBase yk) {
		double inner = mul(t(sk), yk);
		Matrix tmp1 = div(add(mul(currentMatrix, mul(yk, t(sk))), 
						mul(sk, t(mul(currentMatrix, yk)))), inner);
		
		double tmp2 = 1.0 + mul(t(yk), mul(currentMatrix, yk)) / inner; 
		Matrix tmp3 = mul(mul(sk, t(sk)) , tmp2 / inner);

		return assign(sub(currentMatrix, add(tmp1, tmp3)), Matrix.class);
	}

	private boolean isZeroVector(VectorBase vector) {
		int size = vector.size();
		for (int i = 0; i < size; i ++) {
			if (vector.get(i) != 0d) {
				return false;
			}
		}
		return true;
	}
	
	private MatrixBase generateUnitMatrix(int dimension) {
		Matrix matrix = new Matrix(dimension);
		for (int i = 0; i < dimension; i ++) {
			matrix.set(i, i, 1d);
		}
		
		return matrix;
	}
}
