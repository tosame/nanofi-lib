package nanofi.net.la;

final class MinusVector<T extends VectorBase> implements VectorBase {
  private final T base;
  public MinusVector(final T base) {
    this.base = base;
  }
  @Override
  public double get(final int index) {
    return -base.get(index);
  }
  @Override
  public int size() {
    return base.size();
  }
}