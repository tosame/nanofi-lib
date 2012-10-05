package nanofi.net.la;

class MinusVector<T extends VectorBase> implements VectorBase{
  private T base;
  public MinusVector(T base){
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
}