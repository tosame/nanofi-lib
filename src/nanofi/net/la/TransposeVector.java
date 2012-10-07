package nanofi.net.la;

final class TransposeVector<T extends VectorBase> implements VectorBase {
  private final T base;
  public TransposeVector(final T base) {
    this.base = base;
  }

  public T base() {
    return base;
  }

  @Override
  public double get(final int index) {
    return base.get(index);
  }

  @Override
  public int size() {
    return base.size();
  }
}
