package nanofi.net.la;

import java.util.Arrays;

public class Matrix implements MatrixBase, MatrixWritable {
  protected int rows;
  protected int columns;
  protected double[][] values;
  public Matrix(final int size) {
    this(size, size);
  }
  public Matrix(final int rows, final int columns) {
    this.rows = rows;
    this.columns = columns;
    values = new double[rows][columns];
  }
  public Matrix(final int columns, final double... copy) {
    this.rows = copy.length / columns;
    this.columns = columns;
    values = new double[rows][columns];
    for(int i = 0; i < rows; i++) {
      System.arraycopy(copy, i * columns, values[i], 0, columns);
    }
  }
  public Matrix(final double[]... copy) {
    rows = copy.length;
    columns = copy[0].length;
    values = new double[rows][columns];
    for(int i = 0; i < copy.length; i++) {
      System.arraycopy(copy[i], 0, values[i], 0, columns);
    }
  }
  public <T extends MatrixBase> Matrix(final T copy) {
    rows = copy.rows();
    columns = copy.columns();
    values = new double[rows][columns];
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < columns; j++){
        values[i][j] = copy.get(i, j);
      }
    }
  }

  @Override
  public double set(final int row, final int column, final double value) {
    return values[row][column] = value;
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
  public double get(final int row, final int column) {
    return values[row][column];
  }

  @Override
  public String toString() {
    return Arrays.deepToString(values);
  }
}
