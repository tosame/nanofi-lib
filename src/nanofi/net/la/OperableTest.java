package nanofi.net.la;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class OperableTest {

  public static class BasicArithmetic {
    private BasicArithmeticOperate op = new BasicArithmeticOperate();

    @Test
    public void test() {
      System.out.println(op.test());
    }

    public class BasicArithmeticOperate extends Operable {
      public Vector test() {
        Vector a = new Vector(1, 2, 3);
        Vector b = new Vector(2, 4, 6);
        Vector c = new Vector(0, 1, 6);
        return assign(sub(20.0, add(a, 10.0)), Vector.class);
        /*
         * <code>
         * mul(t(a), b) // inner product
         * mul(a, t(b)) // outer product
         * mul(a, b) // product of each elements
         * </code>
         */
      }
    }
  }
}
