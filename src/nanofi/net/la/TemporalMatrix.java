package nanofi.net.la;

final class TemporalMatrix extends Matrix {

  public <T extends MatrixBase> TemporalMatrix(final T copy) {
    super(copy);
  }
  public TemporalMatrix(final int rows, final int columns) {
    super(rows, columns);
  }

}
