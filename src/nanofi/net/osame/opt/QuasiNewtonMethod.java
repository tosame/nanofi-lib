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
	public List<? extends VectorBase> optimize(Gradient function, VectorBase initialVector) {
		
		int dimension = function.getDimension();
		MatrixBase coefficientMatrix = generateUnitMatrix(dimension);
		VectorBase vector = initialVector;
		VectorBase gradient = function.gradient(vector);
		
		VectorBase previousVector = null;
		VectorBase previousGradient = null;
		
		List<VectorBase> vectors = new ArrayList<>();
		vectors.add(vector);
		
		int t = 0;
		while (true) {
			t ++;
			// decide search direction
			Vector searchDirection = assign(mul(-1, mul(coefficientMatrix, gradient)), Vector.class);
			double stepSize = lineSearch.searchStepSize(function, vector, searchDirection);
			
			// update vectors
			previousVector = vector;
			previousGradient = gradient;
			vector = assign(add(vector, mul(stepSize, searchDirection)), Vector.class);
			vectors.add(vector);
			gradient = function.gradient(vector);
			
			// convergent check
			if (condition.converged(t, Math.sqrt(mul(t(gradient), gradient)))) {
				// convergent
				break;
			}
			
			// make difference vectors
			Vector vectorDifference = assign(sub(vector, previousVector), Vector.class);
			Vector gradientDifference = assign(sub(gradient, previousGradient), Vector.class);
			
			if (isZeroVector(vectorDifference) || isZeroVector(gradientDifference)) {
				break;
			}
			
			// update coefficient matrix
			// sk:vector difference , yk : gradient difference
			Matrix tmp1 = assign(
					div(
							add(mul(coefficientMatrix, mul(gradientDifference, t(vectorDifference))), 
									mul(vectorDifference, t(mul(coefficientMatrix, gradientDifference)))), 
							mul(t(vectorDifference), gradientDifference)), Matrix.class);
			
			// mul(TransposeVecotr<Vector>, TemporalVector)があいまいらしい
//			mul(t(gradientDifference), mul(coefficientMatrix, gradientDifference));
			double tmp2 = 1.0 + 
					mul(t(mul(coefficientMatrix, gradientDifference)), gradientDifference) / 
					mul(t(vectorDifference), gradientDifference);
			Matrix tmp3 = assign(
					mul(mul(vectorDifference, t(vectorDifference)) , tmp2 / mul(t(vectorDifference), gradientDifference)), 
					Matrix.class);
			
			coefficientMatrix = assign(sub(coefficientMatrix, add(tmp1, tmp3)), Matrix.class);
		}
		
		return vectors;
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
