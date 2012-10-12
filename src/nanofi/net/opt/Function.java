package nanofi.net.opt;

import nanofi.net.la.Vector;
import nanofi.net.la.VectorBase;

public interface Function {
  double obtain(VectorBase param);
}
