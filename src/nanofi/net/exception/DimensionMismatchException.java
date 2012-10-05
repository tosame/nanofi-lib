package nanofi.net.exception;

public class DimensionMismatchException extends RuntimeException {

  /**
   * Serial version UID
   */
  private static final long serialVersionUID = 2984504436127993181L;
  
  public DimensionMismatchException() {
    super();
  }

  public DimensionMismatchException(String message) {
    super(message);
  }

  public DimensionMismatchException(Throwable cause) {
    super(cause);
  }

  public DimensionMismatchException(String message, Throwable cause) {
    super(message, cause);
  }
}
