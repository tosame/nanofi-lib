package nanofi.net.primitive;

public class Unit<First> extends Tuple<First, None, None, None, None, None, None, None, None, None> {
  public static <First> Unit<First> create(First first) {
    return new Unit<>(first);
  }

  private First first;

  public Unit() {
  }

  public Unit(First first) {
    this.first = first;
  }

  public First getFirst() {
    return first;
  }

  public void setFirst(First first) {
    this.first = first;
  }
}
