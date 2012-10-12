package nanofi.net.la;

import java.lang.reflect.InvocationTargetException;
import nanofi.net.exception.DimensionMismatchException;

public class Operable {
  // Assign operation
  @SuppressWarnings("unchecked")
  public <T, V> T assign(final V value, final Class<T> klass) {
    return (T)value;
  }

  public <T extends VectorWritable> T assign(final VectorBase value, final Class<T> klass) {
    try {
      return klass.getConstructor(VectorBase.class).newInstance(value);
    } catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      return null;
    }
  }

  // creational method
  public Vector vector(double... values) {
    return new Vector(values);
  }
  public <T extends VectorBase> Vector vector(T copy) {
    return new Vector(copy);
  }
  public VectorBase zero(int size){
    return new ZeroVector(size);
  }

  public double[] row(double... values) {
    return values;
  }

  public Matrix matrix(double[]... values) {
    return new Matrix(values);
  }
  public <T extends MatrixBase> Matrix matrix(T copy) {
    return new Matrix(copy);
  }
  public MatrixBase identity(int size) {
    return new IdentityMatrix(size);
  }
  public MatrixBase identity(int rows, int columns) {
    return new IdentityMatrix(rows, columns);
  }

  // Basic arithmetic operation
  // Add operations

  // Start: Add operations for Vector + Vector

  private <A extends VectorWritable, B extends VectorBase> A _addAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b.get(i));
    }
    return a;
  }

  public <A extends VectorWritable, B extends VectorBase> A addAssign(final A a, final B b) {
    return _addAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector add(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(final TemporalVector a, final B b) {
    return addAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector add(final A a, final TemporalVector b) {
    return addAssign(b, a);
  }

  public TemporalVector add(final TemporalVector a, final TemporalVector b) {
    return addAssign(a, b);
  }

  // End: Add operations for Vector + Vector

  // Start: Add operations for Vector + double

  public <A extends VectorWritable> A addAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) + b);
    }
    return a;
  }

  public <A extends VectorBase> TemporalVector add(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return addAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector add(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return addAssign(result, a);
  }

  public TemporalVector add(final TemporalVector a, final double b) {
    return addAssign(a, b);
  }

  public TemporalVector add(final double a, final TemporalVector b) {
    return addAssign(b, a);
  }

  // End: Add operations for Vector + double

  // Start: Add operations for Matrix + Matrix

  private <A extends MatrixWritable, B extends MatrixBase> A _addAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() && columns != b.columns()) new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) + b.get(i, j));
      }
    }
    return a;
  }

  public <A extends MatrixWritable, B extends MatrixBase> A addAssign(final A a, final B b) {
    return _addAssign(a, b);
  }

  public <A extends MatrixBase, B extends MatrixBase> TemporalMatrix add(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return addAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix add(final TemporalMatrix a, final B b) {
    return addAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix add(final A a, final TemporalMatrix b) {
    return addAssign(b, a);
  }

  public TemporalMatrix add(final TemporalMatrix a, final TemporalMatrix b) {
    return addAssign(a, b);
  }

  // End: Add operations for Matrix + Matrix

  // Start: Add operations for Matrix + double

  public <A extends MatrixWritable> A addAssign(final A a, final double b) {
    final int rows = a.rows();
    final int columns = a.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) + b);
      }
    }
    return a;
  }

  public <A extends MatrixBase> TemporalMatrix add(final A a, final double b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return addAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix add(final double a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(b);
    return addAssign(result, a);
  }

  public TemporalMatrix add(final TemporalMatrix a, final double b) {
    return addAssign(a, b);
  }

  public TemporalMatrix add(final double a, final TemporalMatrix b) {
    return addAssign(b, a);
  }

  // End: Add operations for Matrix + double

  // Minus unary operations
  public <A extends VectorBase> MinusVector<A> minus(final A a) {
    return new MinusVector<A>(a);
  }

  public <A extends MatrixBase> MinusMatrix<A> minus(final A a) {
    return new MinusMatrix<A>(a);
  }

  // Sub operations

  // Start: Sub operations for Vector + Vector

  private <A extends VectorWritable, B extends VectorBase> A _subAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) - b.get(i));
    }
    return a;
  }

  private <A extends VectorBase, B extends VectorWritable> B _subAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      b.set(i, b.get(i) - a.get(i));
    }
    return b;
  }

  public <A extends VectorWritable, B extends VectorBase> A subAssign(final A a, final B b) {
    return _subAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector sub(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(final TemporalVector a, final B b) {
    return subAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector sub(final A a, final TemporalVector b) {
    return _subAssign(a, b);
  }

  public TemporalVector sub(final TemporalVector a, final TemporalVector b) {
    return subAssign(a, b);
  }

  // End: Sub operations for Vector + Vector

  // Start: Sub operations for Vector + double

  private <A extends VectorWritable> A _subAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) - b);
    }
    return a;
  }

  private <B extends VectorWritable> B _subAssign(final double a, final B b) {
    final int length = b.size();
    for(int i = 0; i < length; i++) {
      b.set(i, a - b.get(i));
    }
    return b;
  }

  public <A extends VectorWritable> A subAssign(final A a, final double b) {
    return _subAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector sub(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return subAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector sub(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return _subAssign(result, a);
  }

  public TemporalVector sub(final TemporalVector a, final double b) {
    return subAssign(a, b);
  }

  public TemporalVector sub(final double a, final TemporalVector b) {
    return _subAssign(a, b);
  }

  // End: Sub operations for Vector + double

  // Start: Sub operations for Matrix + Matrix

  private <A extends MatrixWritable, B extends MatrixBase> A _subAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() && columns != b.columns()) new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) - b.get(i, j));
      }
    }
    return a;
  }

  private <A extends MatrixBase, B extends MatrixWritable> B _subAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() && columns != b.columns()) new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        b.set(i, j, b.get(i, j) - a.get(i, j));
      }
    }
    return b;
  }

  public <A extends MatrixWritable, B extends MatrixBase> A subAssign(final A a, final B b) {
    return _subAssign(a, b);
  }

  public <A extends MatrixBase, B extends MatrixBase> TemporalMatrix sub(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return subAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix sub(final TemporalMatrix a, final B b) {
    return subAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix sub(final A a, final TemporalMatrix b) {
    return _subAssign(a, b);
  }

  public TemporalMatrix sub(final TemporalMatrix a, final TemporalMatrix b) {
    return subAssign(a, b);
  }

  // End: Sub operations for Matrix + Matrix

  // Start: Sub operations for Matrix + double

  private <A extends MatrixWritable> A _subAssign(final A a, final double b) {
    final int rows = a.rows();
    final int columns = a.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) - b);
      }
    }
    return a;
  }

  private <B extends MatrixWritable> B _subAssign(final double a, final B b) {
    final int rows = b.rows();
    final int columns = b.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        b.set(i, j, b.get(i, j) - a);
      }
    }
    return b;
  }

  public <A extends MatrixWritable> A subAssign(final A a, final double b) {
    return _subAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix sub(final A a, final double b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return subAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix sub(final double a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(b);
    return _subAssign(result, a);
  }

  public TemporalMatrix sub(final TemporalMatrix a, final double b) {
    return subAssign(a, b);
  }

  public TemporalMatrix sub(final double a, final TemporalMatrix b) {
    return _subAssign(a, b);
  }

  // End: Sub operations for Matrix + double

  // Transpose operations
  public <A extends VectorBase> TransposeVector<A> t(final A a) {
    return new TransposeVector<A>(a);
  }

  public <A extends VectorBase> A t(final TransposeVector<A> a) {
    return a.base();
  }

  public <A extends MatrixBase> TransposeMatrix<A> t(final A a) {
    return new TransposeMatrix<A>(a);
  }

  public <A extends MatrixBase> A t(final TransposeMatrix<A> a) {
    return a.base();
  }

  // Mul operations

  // Start: Mul operations for Vector + Vector

  private <A extends VectorWritable, B extends VectorBase> A _mulAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) * b.get(i));
    }
    return a;
  }

  public <A extends VectorWritable, B extends VectorBase> A mulAssign(final A a, final B b) {
    return _mulAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector mul(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return mulAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector mul(final TemporalVector a, final B b) {
    return mulAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector mul(final A a, final TemporalVector b) {
    return mulAssign(b, a);
  }

  public TemporalVector mul(final TemporalVector a, final TemporalVector b) {
    return mulAssign(a, b);
  }

  private <A extends VectorBase, B extends VectorBase> double _innerProduct(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    double result = 0.0;
    for(int i = 0; i < length; i++) {
      result += a.get(i) * b.get(i);
    }
    return result;
  }

  public <A extends VectorBase, B extends VectorBase> double mul(final TransposeVector<A> a, final B b) {
    return _innerProduct(a.base(), b);
  }

  private <A extends VectorBase, B extends VectorBase> TemporalMatrix _outerProduct(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a.size(), b.size());
    for(int i = 0; i < result.rows; i++) {
      for(int j = 0; j < result.columns; j++) {
        result.values[i][j] = a.get(i) * b.get(j);
      }
    }
    return result;
  }

  public <A extends VectorBase, B extends VectorBase> TemporalMatrix mul(final A a, final TransposeVector<B> b) {
    return _outerProduct(a, b.base());
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector mul(final TransposeVector<A> a, final TransposeVector<B> b) {
    return mul(a.base(), b.base());
  }

  // End: Mul operations for Vector + Vector

  // Start: Mul operations for Vector + double

  private <A extends VectorWritable> A _mulAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) * b);
    }
    return a;
  }

  public <A extends VectorWritable, B extends VectorBase> A mulAssign(final A a, final double b) {
    return _mulAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector mul(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return mulAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector mul(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return mulAssign(result, a);
  }

  public TemporalVector mul(final TemporalVector a, final double b) {
    return mulAssign(a, b);
  }

  public TemporalVector mul(final double a, final TemporalVector b) {
    return mulAssign(b, a);
  }

  // End: Mult operations for Vector + double

  // Start: Mul operations for Matrix + Matrix

  private <A extends MatrixBase, B extends MatrixBase> TemporalMatrix _mul(final A a, final B b) {
    final int size = a.columns();
    if(size != b.rows()) new DimensionMismatchException();
    final int rows = a.rows();
    final int columns = b.columns();
    TemporalMatrix result = new TemporalMatrix(rows, columns);
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        double r = 0.0;
        for(int k = 0; k < size; k++) {
          r += a.get(i, k) * b.get(k, j);
        }
        result.set(i, j, r);
      }
    }
    return result;
  }

  public <A extends MatrixBase, B extends MatrixBase> TemporalMatrix mul(final A a, final B b) {
    return _mul(a, b);
  }

  // End: Mul operations for Matrix + Matrix

  // Start: Mul operations for Matrix + Vector

  private <A extends MatrixBase, B extends VectorBase> TemporalVector _mul(final A a, final B b) {
    final int columns = a.columns();
    if(columns != b.size()) throw new DimensionMismatchException();
    final int rows = a.rows();
    final TemporalVector result = new TemporalVector(rows);
    for(int i = 0; i < rows; i++) {
      double r = 0.0;
      for(int j = 0; j < columns; j++) {
        r += a.get(i, j) * b.get(j);
      }
      result.set(i, r);
    }
    return result;
  }

  private <A extends VectorBase, B extends MatrixBase> TemporalVector _mul(final A a, final B b) {
    final int rows = b.rows();
    if(rows != a.size()) throw new DimensionMismatchException();
    final int columns = b.columns();
    final TemporalVector result = new TemporalVector(columns);
    for(int i = 0; i < columns; i++) {
      double r = 0.0;
      for(int j = 0; j < rows; j++) {
        r += a.get(j) * b.get(j, i);
      }
      result.set(i, r);
    }
    return result;
  }

  public <A extends MatrixBase, B extends VectorBase> TemporalVector mul(final A a, final B b) {
    return _mul(a, b);
  }

  public <A extends VectorBase, B extends MatrixBase> TemporalVector mul(final A a, final B b) {
    return _mul(a, b);
  }

  // End: Mult operations for Matrix + Vector

  // Start: Mul operations for Matrix + double

  private <A extends MatrixWritable> A _mulAssign(final A a, final double b) {
    final int rows = a.rows();
    final int columns = a.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) * b);
      }
    }
    return a;
  }

  public <A extends MatrixWritable> A mulAssign(final A a, final double b) {
    return _mulAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix mul(final A a, final double b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return mulAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix mul(final double a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(b);
    return mulAssign(result, a);
  }

  public TemporalMatrix mul(final TemporalMatrix a, final double b) {
    return mulAssign(a, b);
  }

  public TemporalMatrix mul(final double a, final TemporalMatrix b) {
    return mulAssign(b, a);
  }

  // End: Mul operations for Matrix + double

  // Div operations

  // Start: Div operations for Vector + Vector

  private <A extends VectorWritable, B extends VectorBase> A _divAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) / b.get(i));
    }
    return a;
  }

  private <A extends VectorBase, B extends VectorWritable> B _divAssign(final A a, final B b) {
    final int length = a.size();
    if(length != b.size()) throw new DimensionMismatchException();
    for(int i = 0; i < length; i++) {
      b.set(i, b.get(i) / a.get(i));
    }
    return b;
  }

  public <A extends VectorWritable, B extends VectorBase> A divAssign(final A a, final B b) {
    return _divAssign(a, b);
  }

  public <A extends VectorBase, B extends VectorBase> TemporalVector div(final A a, final B b) {
    final TemporalVector result = new TemporalVector(a);
    return divAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector div(final TemporalVector a, final B b) {
    return divAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector div(final A a, final TemporalVector b) {
    return _divAssign(a, b);
  }

  public TemporalVector div(final TemporalVector a, final TemporalVector b) {
    return divAssign(a, b);
  }

  // End: Div operations for Vector + Vector

  // Start: Div operations for Vector + double

  private <A extends VectorWritable> A _divAssign(final A a, final double b) {
    final int length = a.size();
    for(int i = 0; i < length; i++) {
      a.set(i, a.get(i) / b);
    }
    return a;
  }

  private <B extends VectorWritable> B _divAssign(final double a, final B b) {
    final int length = b.size();
    for(int i = 0; i < length; i++) {
      b.set(i, a / b.get(i));
    }
    return b;
  }

  public <A extends VectorWritable> A divAssign(final A a, final double b) {
    return _divAssign(a, b);
  }

  public <A extends VectorBase> TemporalVector div(final A a, final double b) {
    final TemporalVector result = new TemporalVector(a);
    return divAssign(result, b);
  }

  public <B extends VectorBase> TemporalVector div(final double a, final B b) {
    final TemporalVector result = new TemporalVector(b);
    return _divAssign(a, result);
  }

  public TemporalVector div(final TemporalVector a, final double b) {
    return divAssign(a, b);
  }

  public TemporalVector div(final double a, final TemporalVector b) {
    return _divAssign(a, b);
  }

  // End: Div operations for Vector + double

  // Start: Div operations for Matrix + Matrix

  private <A extends MatrixWritable, B extends MatrixBase> A _divAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() && columns != b.columns()) new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) / b.get(i, j));
      }
    }
    return a;
  }

  private <A extends MatrixBase, B extends MatrixWritable> B _divAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() && columns != b.columns()) new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        b.set(i, j, b.get(i, j) / a.get(i, j));
      }
    }
    return b;
  }

  public <A extends MatrixWritable, B extends MatrixBase> A divAssign(final A a, final B b) {
    return _divAssign(a, b);
  }

  public <A extends MatrixBase, B extends MatrixBase> TemporalMatrix div(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return divAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix div(final TemporalMatrix a, final B b) {
    return divAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix div(final A a, final TemporalMatrix b) {
    return _divAssign(a, b);
  }

  public TemporalMatrix div(final TemporalMatrix a, final TemporalMatrix b) {
    return divAssign(a, b);
  }

  // End: Div operations for Matrix + Matrix

  // Start: Hadamard operations for Matrix + double

  private <A extends MatrixWritable, B extends MatrixBase> A _hadamardAssign(final A a, final B b) {
    final int rows = a.rows();
    final int columns = a.columns();
    if(rows != b.rows() || columns != b.columns()) throw new DimensionMismatchException();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) * b.get(i, j));
      }
    }
    return a;
  }

  public <A extends MatrixWritable, B extends MatrixBase> A hadamardAssign(final A a, final B b) {
    return _hadamardAssign(a, b);
  }

  public <A extends MatrixBase, B extends MatrixBase> TemporalMatrix hadamard(final A a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return hadamardAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix hadamard(final TemporalMatrix a, final B b) {
    return hadamardAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix hadamard(final A a, final TemporalMatrix b) {
    return hadamardAssign(b, a);
  }

  public TemporalMatrix hadamard(final TemporalMatrix a, final TemporalMatrix b) {
    return hadamardAssign(b, a);
  }

  // End: Add operations for Matrix + double

  // Start: Div operations for Matrix + double

  private <A extends MatrixWritable> A _divAssign(final A a, final double b) {
    final int rows = a.rows();
    final int columns = a.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        a.set(i, j, a.get(i, j) / b);
      }
    }
    return a;
  }

  private <B extends MatrixWritable> B _divAssign(final double a, final B b) {
    final int rows = b.rows();
    final int columns = b.columns();
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < columns; j++) {
        b.set(i, j, b.get(i, j) / a);
      }
    }
    return b;
  }

  public <A extends MatrixWritable> A divAssign(final A a, final double b) {
    return _divAssign(a, b);
  }

  public <A extends MatrixBase> TemporalMatrix div(final A a, final double b) {
    final TemporalMatrix result = new TemporalMatrix(a);
    return divAssign(result, b);
  }

  public <B extends MatrixBase> TemporalMatrix div(final double a, final B b) {
    final TemporalMatrix result = new TemporalMatrix(b);
    return divAssign(result, a);
  }

  public TemporalMatrix div(final TemporalMatrix a, final double b) {
    return divAssign(a, b);
  }

  public TemporalMatrix div(final double a, final TemporalMatrix b) {
    return _divAssign(a, b);
  }

  // End: Div operations for Matrix + double
}
