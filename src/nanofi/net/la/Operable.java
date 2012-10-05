package nanofi.net.la;

import java.lang.reflect.InvocationTargetException;
import nanofi.net.exception.DimensionMismatchException;

public class Operable {
  // As cast operation
  public <T, V extends T> T as(V value, Class<T> klass) {
    return (T)value;
  }
  public <T extends VectorWritable> T as(VectorBase value, Class<T> klass) {
    try {
      return klass.getConstructor(VectorBase.class).newInstance(value);
    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      return null;
    }
  }

  // Basic arithmetic operation
  public <A extends VectorWritable, B extends VectorBase> A plusAssign(A a, B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b.get(i));
    }
    return a;
  }
  public <A extends VectorBase, B extends VectorBase> TemporalVector plus(A a, B b) {
    final TemporalVector result = new TemporalVector(a);
    return plusAssign(result, b);
  }
  public <B extends VectorBase> TemporalVector plus(TemporalVector a, B b) {
    return plusAssign(a, b);
  }
  public <A extends VectorBase> TemporalVector plus(A a, TemporalVector b) {
    return plusAssign(b, a);
  }
  public <A extends VectorBase> MinusVector<A> minus(A a){
    return new MinusVector<A>(a);
  }
  public <A extends VectorWritable> MinusWritableVector<A> minus(A a){
    return new MinusWritableVector<A>(a);
  }
  public <A extends VectorWritable, B extends VectorBase> A minusAssign(A a, B b) {
    return plusAssign(a, minus(b));
  }
  public <A extends VectorBase, B extends VectorBase> TemporalVector minus(A a, B b) {
    final TemporalVector result = new TemporalVector(a);
    return minusAssign(result, b);
  }
  public <B extends VectorBase> TemporalVector minus(TemporalVector a, B b) {
    return minusAssign(a, b);
  }
  public <A extends VectorBase> TemporalVector minus(A a, TemporalVector b) {
    return minusAssign(b, a);
  }
}
