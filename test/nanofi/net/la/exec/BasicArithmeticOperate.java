package nanofi.net.la.exec;

import nanofi.net.la.Operable;
import nanofi.net.la.Vector;

public class BasicArithmeticOperate extends Operable {

  public void test() {
    final Vector a = new Vector(1, 2, 3);
    final Vector b = new Vector(2, 4, 6);
    final Vector c = new Vector(0, 1, 6);
    System.out.println(add(add(a, b), add(a, c)));
    System.out.println(sub(sub(b, c), sub(a, c)));
    System.out.println(mul(a, b));
    System.out.println(mul(t(a), b));
    System.out.println(mul(a, t(b)));
    System.out.println(mul(t(a), t(b)));
  }

  public static void main(String[] args) {
    BasicArithmeticOperate op = new BasicArithmeticOperate();
    op.test();
  }
}
