package nanofi.net.la;

public interface MatrixBase {
  int rows();
  int columns();
  double get(int row, int column);
}
