package nanofi.net.la.exec;

import nanofi.net.la.Matrix;
import nanofi.net.la.MatrixBase;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;
import nanofi.net.la.VectorWritable;
import nanofi.net.la.ZeroVector;
import nanofi.net.opt.BroydenFletcherGoldfarbShannon;
import nanofi.net.opt.GradableFunction;

public class Optimize extends Operable implements GradableFunction {
  public static void main(String[] args) {
    Optimize optimize = new Optimize();
    BroydenFletcherGoldfarbShannon bfgs = new BroydenFletcherGoldfarbShannon(optimize, 1);
    for (int i = 0; i < 1000; i++) {
      System.out.println(bfgs.solution() + " " + optimize.obtain(bfgs.solution()));
      bfgs.reiterate();
    }
    System.out.println(bfgs.solution() + " " + optimize.obtain(bfgs.solution()));
  }

  @Override
  public double obtain(VectorBase param) {
    VectorBase offset = sub(param, 2.0);
    return mul(t(offset), offset);
  }

  @Override
  public VectorBase gradient(VectorBase param) {
    VectorBase offset = sub(param, 2.0);
    return mul(offset, 2.0);
  }
}
