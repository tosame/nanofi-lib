package nanofi.net.la;

public class TransposeMatrix<T extends MatrixBase> implements MatrixBase {
  private final T base;

  public TransposeMatrix(final T base) {
    this.base = base;
  }

  public T base() {
    return base;
  }

  @Override
  public int rows() {
    return base.columns();
  }

  @Override
  public int columns() {
    return base.rows();
  }

  @Override
  public double get(final int row, final int column) {
    return base.get(column, row);
  }
}
