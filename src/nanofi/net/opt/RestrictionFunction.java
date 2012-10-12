package nanofi.net.opt;

import nanofi.net.la.Vector;

public interface RestrictionFunction extends Function {
  void restrict(Vector solution);
}
