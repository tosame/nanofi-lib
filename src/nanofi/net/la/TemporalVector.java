package nanofi.net.la;

final class TemporalVector extends Vector {
  public <T extends VectorBase> TemporalVector(final T copy) {
    super(copy);
  }
  public TemporalVector(final int size) {
    super(size);
  }
}
