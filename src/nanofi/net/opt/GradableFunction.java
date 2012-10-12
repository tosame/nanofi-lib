package nanofi.net.opt;

import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

public interface GradableFunction extends Function {
  VectorBase gradient(VectorBase param);
}
