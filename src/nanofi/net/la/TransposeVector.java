package nanofi.net.la;

class TransposeVector<T extends VectorBase> implements VectorBase {
  private T base;
  public TransposeVector(T base) {
    this.base = base;
  }
  
  public T base(){
    return base;
  }

  @Override
  public double get(int index) {
    return base.get(index);
  }

  @Override
  public int size() {
    return base.size();
  }
}
