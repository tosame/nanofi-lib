package nanofi.net.primitive;

public class Tuple<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> {
  public static <T1, T2> Pair<T1, T2> create(T1 first, T2 second) {
    return Pair.create(first, second);
  }
}
