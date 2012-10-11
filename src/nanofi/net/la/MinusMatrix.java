package nanofi.net.la;

public class MinusMatrix<T extends MatrixBase> implements MatrixBase {
  private final T base;

  public MinusMatrix(final T base) {
    this.base = base;
  }

  @Override
  public int rows() {
    return base.rows();
  }

  @Override
  public int columns() {
    return base.columns();
  }

  @Override
  public double get(int row, int column) {
    return -base.get(row, column);
  }

}
