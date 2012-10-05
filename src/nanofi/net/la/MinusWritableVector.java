package nanofi.net.la;

public class MinusWritableVector<T extends VectorWritable> implements VectorWritable {
  private T base;
  public MinusWritableVector(T base){
    this.base = base;
  }
  @Override
  public double get(int index) {
    return -base.get(index);
  }
  @Override
  public int size() {
    return base.size();
  }
  @Override
  public double set(int index, double value) {
    return -base.set(index, -value);
  }
}
