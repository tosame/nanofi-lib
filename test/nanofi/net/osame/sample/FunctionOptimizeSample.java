/**
 * 
 */
package nanofi.net.osame.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import nanofi.net.la.Operable;
import nanofi.net.la.VectorBase;
import nanofi.net.osame.model.RidgeRegression;
import nanofi.net.osame.opt.ArmijoLineSearch;
import nanofi.net.osame.opt.ConjugateGradientMethod;
import nanofi.net.osame.opt.ConvergenceCondition;
import nanofi.net.osame.opt.Function;
import nanofi.net.osame.opt.FunctionOptimizable;
import nanofi.net.osame.opt.Gradient;
import nanofi.net.osame.opt.LineSearch;
import nanofi.net.osame.opt.QuasiNewtonMethod;
import nanofi.net.osame.opt.SteepestDescentMethod;
import nanofi.net.osame.opt.WolfeLineSearch;
import nanofi.net.primitive.Pair;

/**
 *
 */
public class FunctionOptimizeSample extends Operable {

	private Gradient function;
	private VectorBase initialVector;
	
	public FunctionOptimizeSample(double[][] coefficients, double[] bias, double constant, Random random) {
		//public LinearRegression(List<Pair<Double, VectorBase>> data, double regularizedParameter)
		List<Pair<Double, ? extends VectorBase>> data = new ArrayList<>();
		double lambda = 0d;
		data.add(new Pair<Double, VectorBase>(1d, vector(new double[]{2d})));
		data.add(new Pair<Double, VectorBase>(3d, vector(new double[]{1d})));
		this.function = new RidgeRegression(data, lambda);
		
//		this.function = new QuadraticFunction(matrix(coefficients), vector(bias), constant);
		this.initialVector = generateRandomVector(this.function.getDimension(), random);
		double[] tmp = new double[this.function.getDimension()];
		Arrays.fill(tmp, 1d);
		this.initialVector = vector(tmp);
	}
	
	public void printOptimization(FunctionOptimizable optimizer, String delimiter) {
		List<? extends VectorBase> vectors = optimizer.optimizeForDebug(function, initialVector);
		printVectors(function, vectors, delimiter);
	}
	
	public void printVectors(Function function, List<? extends VectorBase> vectors, String delimiter) {
		int size = vectors.size();
		for (int t = 0; t < size; t ++) {
			VectorBase vector = vectors.get(t);
			String msg = String.format(
					"%d%s%s%s%f", 
					t, delimiter, toString(vector, delimiter), delimiter, function.functionValue(vector));
			System.out.println(msg);
		}
	}

	private VectorBase generateRandomVector(int dim, Random random) {
		double[] array = new double[dim];
		for (int i = 0; i < dim; i++) {
			array[i] = random.nextDouble();
		}
		return vector(array);
	}
	
	private String toString(VectorBase vector, String delimiter) {
		int size = vector.size();
		StringBuilder builder = new StringBuilder();
		builder.append(vector.get(0));
		for (int i = 1; i < size; i ++) {
			builder.append(delimiter);
			builder.append(vector.get(i));
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		
		String delimiter = " ";
		
		double[][] coefficient = {{2, -0.5}, {-0.5, 1}};
		double[] bias = {5, 6};
		double constant = 0d;
//		double[][] coefficient = {{1d}};
//		double[] bias = {-4d};
//		double constant = 4d;
		
		long seed = 1000000L;
		Random random = new Random(seed);
		
		FunctionOptimizeSample instance = new FunctionOptimizeSample(coefficient, bias, constant, random);
		
		
		
		double epsilon = 0.001;
		int limit = 50;
		ConvergenceCondition condition = new ConvergenceCondition(limit, epsilon);
		
		LineSearch armijoLineSearch = new ArmijoLineSearch();
		LineSearch wolfeLineSearch = new WolfeLineSearch();
		
		
		System.out.println("Steepest Descent Method with Armijo Line Search");
		instance.printOptimization(new SteepestDescentMethod(armijoLineSearch, condition), delimiter);
		System.out.println();
		
		System.out.println("Steepest Descent Method with Wolfe Line Search");
		instance.printOptimization(new SteepestDescentMethod(wolfeLineSearch, condition), delimiter);
		System.out.println();
		
		
		
		System.out.println("Conjugate Gradient Method with Armijo Line Search");
		instance.printOptimization(new ConjugateGradientMethod(armijoLineSearch, condition), delimiter);
		System.out.println();
		
		System.out.println("Conjugate Gradient Method with Wolfe Line Search");
		instance.printOptimization(new ConjugateGradientMethod(wolfeLineSearch, condition), delimiter);
		System.out.println();
		
		
		
		System.out.println("quasi-Newton Method with Armijo Line Search");
		instance.printOptimization(new QuasiNewtonMethod(armijoLineSearch, condition), delimiter);
		System.out.println();
		
		System.out.println("quasi-Newton Method with Wolfe Line Search");
		instance.printOptimization(new QuasiNewtonMethod(wolfeLineSearch, condition), delimiter);
		System.out.println();
	}
}
