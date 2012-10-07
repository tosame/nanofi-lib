package nanofi.net.la;

import java.util.Arrays;

public class Vector implements VectorBase, VectorWritable {
  protected final double[] values;
  public Vector(final int size) {
    values = new double[size];
  }
  public Vector(final double... values) {
    this.values = Arrays.copyOf(values, values.length);
  }
  public <T extends VectorBase> Vector(final T copy) {
    values = new double[copy.size()];
    for(int i = 0; i < values.length; i++)
      values[i] = copy.get(i);
  }

  @Override
  public double get(final int index) {
    return values[index];
  }
  @Override
  public int size() {
    return values.length;
  }
  @Override
  public double set(final int index, final double value) {
    return (values[index] = value);
  }

  @Override
  public String toString() {
    return Arrays.toString(values);
  }
}
