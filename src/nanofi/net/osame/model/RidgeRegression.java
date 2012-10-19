/**
 * 
 */
package nanofi.net.osame.model;

import java.util.List;

import nanofi.net.exception.DimensionMismatchException;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;
import nanofi.net.osame.opt.Gradient;
import nanofi.net.primitive.Pair;

/**
 *
 */
public class RidgeRegression extends Operable implements Gradient {

	private Predictable predictor;
	private int numberOfData;
	private int dimension;
	private double lambda;
	private double[] outputs;
	private VectorBase[] data;
	
	public RidgeRegression(List<Pair<Double, ? extends VectorBase>> dataList, double regularizedParameter) {
		this(dataList, regularizedParameter, new InnerPredictor());
	}
	
	public RidgeRegression(List<Pair<Double, ? extends VectorBase>> dataList, double regularizedParameter, Predictable predictor) {
		
		int d = dataList.get(0).second().size();
		int n = dataList.size();
		this.outputs = new double[n];
		this.data = new VectorBase[n];
		for (int i = 0; i < n; i ++) {
			Pair<Double, ? extends VectorBase> pair = dataList.get(i);
			VectorBase vector = pair.second();
			if (vector.size() != d) {
				throw new DimensionMismatchException();
			}
			this.outputs[i] = pair.first().doubleValue();
			this.data[i] = vector;
		}
		
		this.predictor = predictor;
		this.numberOfData = n;
		this.dimension = d;
		this.lambda = regularizedParameter;
	}
	
	@Override
	public int getDimension() {
		return this.dimension;
	}
	
	private double error(VectorBase vector, int index) {
		double prediction = this.predictor.predict(vector, this.data[index]);
		double error = this.outputs[index] - prediction;
		return error;
	}
	
	@Override
	public double functionValue(VectorBase vector) {
		
		double functionValue = 0d;
		for (int i = 0; i < this.numberOfData; i ++) {
			double error = error(vector, i);
			functionValue += (error * error);
		}
		functionValue /= 2;
		
		if (this.lambda != 0d) {
			functionValue += (this.lambda * mul(t(vector), vector) / 2);
		}
		
		return functionValue;
	}

	@Override
	public VectorBase gradient(VectorBase vector) {
		VectorBase gradient = mul(this.lambda, vector);
		for (int i = 0; i < this.numberOfData; i ++) {
			double error = error(vector, i);
			gradient = sub(gradient, mul(error, this.predictor.gradient(vector, this.data[i])));
		}
		return assign(gradient, Vector.class);
	}

}
