package nanofi.net.la;

public interface VectorBase {
  /**
   * Get vector element for index.
   * 
   * @param index
   *        get element index.
   * @return element at index.
   */
  double get(int index);
  /**
   * Get vector size.
   * 
   * @return vector size.
   */
  int size();
}
