package nanofi.net.primitive;

import java.util.Map.Entry;

/**
 * Pair class has ability of hold two type values.
 * <p>
 * Make pair instance can from helper methods belows:
 * 
 * <pre>
 * {
 *   &#064;code
 *   Pair&lt;&gt; pair = Pair.create(10, &quot;Test&quot;); // Return Pair&lt;Integer, String&gt; instance.
 * }
 * </pre>
 * 
 * </p>
 * 
 * @author Kazuto Fukuchi
 * 
 * @param <First>
 *        First value type
 * @param <Second>
 *        Second value type
 */
public class Pair<First, Second> implements Entry<First, Second> {
  /**
   * Create Pair instance from parameters.
   * <p>
   * In use this method, you must not write type parameter since use type inferece.
   * </p>
   * 
   * @param first
   *        First value.
   * @param second
   *        Second value.
   * @return Pair instance that values is method parameters.
   */
  public static <First, Second> Pair<First, Second> create(final First first, final Second second) {
    return new Pair<First, Second>(first, second);
  }

  private First first;
  private Second second;

  /**
   * Construct pair object initizlied default values.
   */
  public Pair() {}

  /**
   * Construct pair object initialized from parameters.
   * 
   * @param first
   *        First value.
   * @param second
   *        Second value.
   */
  public Pair(final First first, final Second second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Getter for first value.
   * 
   * @return First value.
   */
  public First first() {
    return first;
  }
  /**
   * Setter for first value.
   * 
   * @param value
   *        First value.
   * @return First value. It's same as parameter value.
   */
  public First first(final First value) {
    return this.first = value;
  }
  /**
   * Getter for second value.
   * 
   * @return Second value.
   */
  public Second second() {
    return second;
  }
  /**
   * Setter for second value.
   * 
   * @param value
   *        Second value.
   * @return Second value. It's same as parameter value.
   */
  public Second second(final Second value) {
    return this.second = value;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map.Entry#getKey()
   */
  @Override
  public First getKey() {
    return first;
  }
  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map.Entry#getValue()
   */
  @Override
  public Second getValue() {
    return second;
  }
  /*
   * (non-Javadoc)
   * 
   * @see java.util.Map.Entry#setValue(java.lang.Object)
   */
  @Override
  public Second setValue(final Second value) {
    return this.second = value;
  }
}
