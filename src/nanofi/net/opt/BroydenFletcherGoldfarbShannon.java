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
  private Vector diffGradient;
  private Matrix hesseInvert;

  public final VectorWritable solution() {
    return solution;
  }
  public final MatrixBase hesseInvert() {
    return hesseInvert;
  }
  public final VectorBase diffGradient() {
    return diffGradient;
  }

  public BroydenFletcherGoldfarbShannon(GradableFunction objective, int size) {
    this(objective, new ZeroVector(size), null);
  }
  public BroydenFletcherGoldfarbShannon(GradableFunction objective, VectorBase init) {
    this(objective, init, null);
  }
  public BroydenFletcherGoldfarbShannon(GradableFunction objective, VectorBase init, MatrixBase hesseInvert) {
    solution = vector(init);
    this.hesseInvert = hesseInvert == null ? matrix(identity(solution.size())) : matrix(hesseInvert);
  }

  @Override
  public void reiterate() {
    if(diffGradient == null){
      diffGradient = vector(objective.gradient(solution));
    }
  }

}
