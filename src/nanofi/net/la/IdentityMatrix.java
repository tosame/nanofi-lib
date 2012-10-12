package nanofi.net.la;

public class IdentityMatrix implements MatrixBase {
  private int rows;
  private int columns;
  public IdentityMatrix(int size){
    this(size, size);
  }
  public IdentityMatrix(int rows, int columns){
    this.rows=rows;
    this.columns=columns;
  }

  @Override
  public int rows() {
    return rows;
  }

  @Override
  public int columns() {
    return columns;
  }

  @Override
  public double get(int row, int column) {
    return row == column ? 1 : 0;
  }

}
