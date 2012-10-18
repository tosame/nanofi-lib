package nanofi.net.la;

public class ZeroVector implements VectorBase {
  private int size;
  public ZeroVector(int size){
    this.size = size;
  }

  @Override
  public double get(int index) {
    return 0;
  }

  @Override
  public int size() {
    return size;
  }

}
