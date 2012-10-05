package nanofi.net.la;

import java.util.Arrays;

public class Vector implements VectorBase, VectorWritable {
  protected final double[] values;
  public Vector(int size) {
    values = new double[size];
  }
  public Vector(double[] values) {
    this.values = Arrays.copyOf(values, values.length);
  }
  public <T extends VectorBase> Vector(T copy) {
    values = new double[copy.size()];
    for(int i = 0; i < values.length; i++)
      values[i] = copy.get(i);
  }

  @Override
  public double get(int index) {
    return values[index];
  }
  @Override
  public int size() {
    return values.length;
  }
  @Override
  public double set(int index, double value) {
    return (values[index] = value);
  }
}
