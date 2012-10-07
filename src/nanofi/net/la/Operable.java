package nanofi.net.la;

import java.lang.reflect.InvocationTargetException;
import nanofi.net.exception.DimensionMismatchException;

public class Operable {
  // Assign operation
  @SuppressWarnings("unchecked")
  public <T, V> T assign(final V value, final Class<T> klass) {
    return (T)value;
  }

  public <T extends VectorWritable> T assign(final VectorBase value, final Class<T> klass) {
    try {
      return klass.getConstructor(VectorBase.class).newInstance(value);
    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      return null;
    }
  }

  // Basic arithmetic operation
  // Add operations
  private <A extends VectorWritable, B extends VectorBase> A _addAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b.get(i));
    }
    return a;
  }

  private <A extends VectorWritable, B extends VectorBase> A _subAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, b.get(i) - a.get(i));
    }
    return a;
  }

  public <A extends VectorWritable, B extends VectorBase> A addAssign(final A a, final B b) {
    return _addAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector add(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(final TemporalVector a, final B b) {
    return addAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector add(final A a, final TemporalVector b) {
    return addAssign(b, a);
  }

  public <A extends VectorWritable> A addAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b);
    }
    return a;
  }

  public <A extends VectorBase> TemporalVector add(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return addAssign(result, a);
  }

  public TemporalVector add(final TemporalVector a, final double b) {
    return addAssign(a, b);
  }

  public TemporalVector add(final double a, final TemporalVector b) {
    return addAssign(b, a);
  }

  public TemporalVector add(final TemporalVector a, final TemporalVector b) {
    return addAssign(a, b);
  }

  // Minus unary operations
  public <A extends VectorBase> MinusVector<A> minus(final A a) {
    return new MinusVector<A>(a);
  }

  // Sub operations
  private <A extends VectorWritable> A _subAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, b - a.get(i));
    }
    return a;
  }
  public <A extends VectorWritable, B extends VectorBase> A subAssign(final A a, final B b) {
    return addAssign(a, minus(b));
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector sub(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(final TemporalVector a, final B b) {
    return subAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector sub(final A a, final TemporalVector b) {
    return _subAssign(b, a);
  }
  public TemporalVector sub(final TemporalVector a, final TemporalVector b) {
    return subAssign(a, b);
  }

  public <A extends VectorWritable> A subAssign(final A a, final double b) {
    return addAssign(a, -b);
  }

  public <A extends VectorBase> TemporalVector sub(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return _subAssign(result, a);
  }

  public TemporalVector sub(final TemporalVector a, final double b) {
    return subAssign(a, b);
  }

  public TemporalVector sub(final double a, final TemporalVector b) {
    return _subAssign(b, a);
  }

  // Transpose operations
  public <A extends VectorBase> TransposeVector<A> t(final A a) {
    return new TransposeVector<A>(a);
  }
  public <A extends VectorBase> A t(final TransposeVector<A> a) {
    return a.base();
  }

  // Mul operations
  private <A extends VectorWritable, B extends VectorBase> A _mulAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) * b.get(i));
    }
    return a;
  }
  public <A extends VectorWritable, B extends VectorBase> A mulAssign(final A a, final B b) {
    return _mulAssign(a, b);
  }
  public <A extends VectorBase, B extends VectorBase> TemporalVector mul(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return mulAssign(result, b);
  }
  public <B extends VectorBase> TemporalVector mul(final TemporalVector a, final B b) {
    return mulAssign(a, b);
  }
  public <A extends VectorBase> TemporalVector mul(final A a, final TemporalVector b) {
    return mulAssign(b, a);
  }
  public TemporalVector mul(final TemporalVector a, final TemporalVector b) {
    return mulAssign(a, b);
  }
  private <A extends VectorWritable> A _mulAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) * b);
    }
    return a;
  }
  public <A extends VectorWritable, B extends VectorBase> A mulAssign(final A a, final double b) {
    return _mulAssign(a, b);
  }
  public <A extends VectorBase> TemporalVector mul(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return mulAssign(result, b);
  }
  public <B extends VectorBase> TemporalVector mul(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return mulAssign(result, a);
  }
  public  TemporalVector mul(final TemporalVector a, final double b) {
    return mulAssign(a, b);
  }
  public  TemporalVector mul(final double a, final TemporalVector b) {
    return mulAssign(b, a);
  }

  private <A extends VectorBase, B extends VectorBase> double _innerProduct(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    double result = 0.0;
    for(int i = 0; i < length; i++) {
      result += a.get(i) * b.get(i);
    }
    return result;
  }
  public <A extends VectorBase, B extends VectorBase> double mul(final TransposeVector<A> a, final B b) {
    return _innerProduct(a.base(), b);
  }
  private <A extends VectorBase, B extends VectorBase> TemporalMatrix _outerProduct(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a.size(), b.size());
    for(int i = 0; i < result.rows; i++) {
      for(int j = 0; j < result.columns; j++) {
        result.values[i][j] = a.get(i) * b.get(j);
      }
    }
    return result;
  }
  public <A extends VectorBase, B extends VectorBase> TemporalMatrix mul(final A a, final TransposeVector<B> b) {
    return _outerProduct(a, b.base());
  }
  public <A extends VectorBase, B extends VectorBase> TemporalVector mul(final TransposeVector<A> a, final TransposeVector<B> b) {
    return mul(a.base(), b.base());
  }
  // Div operations
}
