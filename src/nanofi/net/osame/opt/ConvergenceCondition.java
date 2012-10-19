/**
 * 
 */
package nanofi.net.osame.opt;

import java.util.logging.Logger;

/**
 *
 */
public class ConvergenceCondition {
	private Logger logger;
	private int updateLimit;
	private double epsilon;

	private void setField(int limit, double eps) {
		this.logger = Logger.getLogger("ConditionLogger");
		this.updateLimit = limit;
		this.epsilon = eps;
		
		this.logger.fine(String.format("[arg]update limit:%d:epsilon:%f", this.updateLimit, this.epsilon));
	}
	
	/**
	 * 更新回数の上限値と目的関数の差分により,　収束条件を作成. 更新回数がこの上限値より大きいか,
	 * 更新における目的関数の差分がこの条件値以下の場合に収束しているとみなす.
	 * 
	 * @param updateLimit
	 *            更新回数の上限値
	 * @param epsilon
	 *            目的関数の差分の収束条件値
	 * @throws IllegalArgumentException
	 *             引数が負の値の場合
	 * 
	 */
	public ConvergenceCondition(int updateLimit, double epsilon) {
		if (updateLimit < 0 || epsilon < 0d) throw new IllegalArgumentException();
		setField(updateLimit, epsilon);
	}

	/**
	 * 更新回数の上限値を定め, 収束条件を作成. この上限値より更新回数が大きい場合に収束しているとみなす.
	 * {@link #ConvergenceCondition(int, double)}の引数を(updateLimit, 0d)とした場合と等価.
	 * 
	 * @param updateLimit
	 *            更新回数の上限値
	 * @throws IllegalArgumentException
	 *             更新回数の上限値が負の値の場合
	 */
	public ConvergenceCondition(int updateLimit) {
		if (updateLimit < 0) throw new IllegalArgumentException();
		setField(updateLimit, 0d);
	}

	/**
	 * 目的関数の差分により, 収束条件を作成. {@link #ConvergenceCondition(int, double)}の 引数を(
	 * {@link Integer#MAX_VALUE}, epsilon)とするのと等価.
	 * 
	 * @param epsilon
	 *            差分の収束条件
	 * @throws IllegalArgumentException
	 *             差分の収束条件の値が負の場合
	 */
	public ConvergenceCondition(double epsilon) {
		if (epsilon < 0d) throw new IllegalArgumentException();
		setField(Integer.MAX_VALUE, epsilon);
	}

	
	
	/**
	 * 保持している条件の更新回数の上限値を取得.
	 * 
	 * @return 収束条件の更新回数の上限値
	 */
	public int getUpdateLimit() {
		return updateLimit;
	}

	/**
	 * 保持している差分の条件を取得. 更新における差分がこの値以下の場合,　収束しているとみなす.
	 * 
	 * @return 収束条件の差分の条件値
	 */
	public double getEpsilon() {
		return epsilon;
	}

	/**
	 * 収束したかどうかを判定
	 * 
	 * @param update
	 * @return 引数の更新回数(update)が保持している更新回数の上限値より大きい場合に収束していると見なし, 収束している場合は
	 *         <code>true</code>, していない場合は<code>false</code>を返す
	 * @throws IllegalArgumentException
	 *             引数の更新回数の値が負の値の場合.
	 */
	public boolean converged(int update) {
		if (update < 0) {
			String msg = String.format("[exception]argument is negative value:%d", update);
			this.logger.fine(msg);
			throw new IllegalArgumentException(msg);
		}

		boolean converged = updateLimit < update ? true : false;
		this.logger.finest(String.format("[info]input:%d:limit:%d:response:%b", update, this.updateLimit, converged));
		return converged;
	}

	/**
	 * 収束したかどうかを判定
	 * 
	 * @param difference
	 *            更新における目的関数の差分
	 * @return 引数の差分の絶対値が保持している差分の条件値以下の場合収束しているとみなし, 収束している場合は<code>true</code>
	 *         , していない場合は<code>false</code>を返す
	 */
	public boolean converged(double difference) {
		boolean converged = Math.abs(difference) <= epsilon ? true : false;
		this.logger.finest(String.format("[info]difference:%f:epsilon:%f:response:%b", difference, this.epsilon, converged));
		return converged;
	}

	/**
	 * 収束したかどうかを判定
	 * 
	 * @param update
	 *            更新回数
	 * @param difference
	 *            更新における差分
	 * @return 引数の更新回数が保持している条件の更新回数の上限値より大きいか, もしくは,
	 *         引数の差分が保持している差分の条件以下の場合収束しているとみなす. 収束している場合は<code>true</code>,
	 *         していない場合は<code>false</code>を返す
	 * @throws IllegalArgumentException
	 *             引数の更新回数の値が負の値の場合.
	 */
	public boolean converged(int update, double difference) {
		if (update < 0) throw new IllegalArgumentException();

		boolean converged = converged(update) | converged(difference);
		this.logger.finest(String.format("[info]update:%d:difference:%f:response:%b", update, difference, converged));
		return converged;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConvergenceCondition [updateLimit=");
		builder.append(updateLimit);
		builder.append(", epsilon=");
		builder.append(epsilon);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 保持しているパラメータを引数の文字列で連結した文字列を返す.
	 * @param delimiter パラメータの区切り文字
	 * @return 引数の区切り文字でパラメータを連結した文字列
	 */
	public String toString(String delimiter) {
		StringBuilder builder = new StringBuilder();
		builder.append(updateLimit);
		builder.append(delimiter);
		builder.append(epsilon);
		return builder.toString();
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(epsilon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + updateLimit;
		return result;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ConvergenceCondition other = (ConvergenceCondition) obj;
		if (Double.doubleToLongBits(epsilon) != Double.doubleToLongBits(other.epsilon)) return false;
		if (updateLimit != other.updateLimit) return false;
		return true;
	}

}
