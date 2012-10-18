package nanofi.net.opt;

import nanofi.net.la.Matrix;
import nanofi.net.la.MatrixBase;
import nanofi.net.la.Operable;
import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;
import nanofi.net.la.VectorWritable;
import nanofi.net.la.ZeroVector;

public class BroydenFletcherGoldfarbShannon extends Operable implements Reiterable {
  private GradableFunction objective;

  private Vector solution;
  private Vector gradient;
  private Matrix hesseInvert;

  public final VectorWritable solution() {
    return solution;
  }

  public final MatrixBase hesseInvert() {
    return hesseInvert;
  }

  public final VectorBase gradient() {
    return gradient;
  }

  public BroydenFletcherGoldfarbShannon(GradableFunction objective, int size) {
    this(objective, new ZeroVector(size), null);
  }

  public BroydenFletcherGoldfarbShannon(GradableFunction objective, VectorBase init) {
    this(objective, init, null);
  }

  public BroydenFletcherGoldfarbShannon(GradableFunction objective, VectorBase init, MatrixBase hesseInvert) {
    this.objective = objective;
    solution = vector(init);
    this.hesseInvert = hesseInvert == null ? matrix(identity(solution.size())) : matrix(hesseInvert);
    gradient = vector(objective.gradient(solution));
  }

  @Override
  public void reiterate() {
    final Vector sk = mul(hesseInvert, gradient);
    double alpha = 0.1;
    mulAssign(sk, -alpha);

    addAssign(solution, sk);

    final Vector gk = gradient;
    gradient = vector(objective.gradient(solution));
    final Vector yk = sub(gradient, gk);
    final double divide = mul(t(yk), sk);
    if (Math.abs(divide) < 1.0e-10) return;
    final MatrixBase sy = div(mul(sk, t(yk)), divide);
    hesseInvert = add(mul(sub(identity(solution.size()), sy), mul(hesseInvert, sub(identity(solution.size()), t(sy)))), div(mul(sk, t(sk)), divide));
  }
}
