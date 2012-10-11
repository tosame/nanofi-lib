package nanofi.net.la.exec;

import nanofi.net.la.Matrix;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;

public class BasicArithmeticOperate extends Operable {

  public void test() {
    final Vector a = vector(1, 2, 3);
    final Vector b = vector(2, 4, 6);
    final Vector c = vector(0, 1, 6);
    final Matrix d = matrix(row(10, -2), row(-5, 2));
    final Matrix e = matrix(row(2, -1), row(1, -2));
    System.out.println(add(add(a, b), add(a, c)));
    System.out.println(sub(sub(b, c), sub(a, c)));
    System.out.println(mul(a, b));
    System.out.println(mul(t(a), b));
    System.out.println(mul(a, t(b)));
    System.out.println(mul(t(a), t(b)));
    System.out.println(mul(d, e));
    mul(d, e);
  }

  public static void main(String[] args) {
    BasicArithmeticOperate op = new BasicArithmeticOperate();
    op.test();
  }
}
