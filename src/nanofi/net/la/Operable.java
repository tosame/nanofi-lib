package nanofi.net.la;

import java.lang.reflect.InvocationTargetException;
import nanofi.net.exception.DimensionMismatchException;

public class Operable {
  // Assign operation
  @SuppressWarnings("unchecked")
  public <T, V> T assign(V value, Class<T> klass) {
    return (T) value;
  }

  public <T extends VectorWritable> T assign(VectorBase value, Class<T> klass) {
    try {
      return klass.getConstructor(VectorBase.class).newInstance(value);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
      return null;
    }
  }

  // Basic arithmetic operation
  // Add operations
  private <A extends VectorWritable, B extends VectorBase> A _addAssign(A a, B b) {
    final int length = a.size();
    if (length != b.size()) throw new DimensionMismatchException();
    for (int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b.get(i));
    }
    return a;
  }

  private <A extends VectorWritable, B extends VectorBase> A _subAssign(A a, B b) {
    final int length = a.size();
    if (length != b.size()) throw new DimensionMismatchException();
    for (int i = 0; i < length; i++) {
      a.set(i, b.get(i) - a.get(i));
    }
    return a;
  }

  public <A extends VectorWritable, B extends VectorBase> A addAssign(A a, B b) {
    return _addAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector add(A a, B b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(TemporalVector a, B b) {
    return addAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector add(A a, TemporalVector b) {
    return addAssign(b, a);
  }

  public <A extends VectorWritable> A addAssign(A a, Double b) {
    final int length = a.size();
    for (int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b);
    }
    return a;
  }

  public <A extends VectorBase> TemporalVector add(A a, Double b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(Double a, B b) {
    final TemporalVector result = new TemporalVector(b);
    return addAssign(result, a);
  }

  public TemporalVector add(TemporalVector a, Double b) {
    return addAssign(a, b);
  }

  public TemporalVector add(Double a, TemporalVector b) {
    return addAssign(b, a);
  }

  public TemporalVector add(TemporalVector a, TemporalVector b) {
    return addAssign(a, b);
  }

  // Minus unary operations
  public <A extends VectorBase> MinusVector<A> minus(A a) {
    return new MinusVector<A>(a);
  }

  // Sub operations
  private <A extends VectorWritable> A _subAssign(A a, Double b) {
    final int length = a.size();
    for (int i = 0; i < length; i++) {
      a.set(i, b - a.get(i));
    }
    return a;
  }
  public <A extends VectorWritable, B extends VectorBase> A subAssign(A a, B b) {
    return addAssign(a, minus(b));
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector sub(A a, B b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(TemporalVector a, B b) {
    return subAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector sub(A a, TemporalVector b) {
    return _subAssign(b, a);
  }

  public <A extends VectorWritable> A subAssign(A a, Double b) {
    return addAssign(a, -b);
  }

  public <A extends VectorBase> TemporalVector sub(A a, Double b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(Double a, B b) {
    final TemporalVector result = new TemporalVector(b);
    return _subAssign(result, a);
  }

  public TemporalVector sub(TemporalVector a, Double b) {
    return subAssign(a, b);
  }

  public TemporalVector sub(Double a, TemporalVector b) {
    return _subAssign(b, a);
  }
  
  // Transpose operations
  
  // Mul operations
  // Div operations
}
