/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.math.number.radical;

import com.github.math.number.fraction.Fraction;

import java.io.Serializable;

/**
 * 抽象的根式类，定义了作为根式的一些基本方法。
 * 
 * @author 		王帅
 * @see			Radical
 * @see			QuadraticRadical
 */
public abstract class AbstractRadical extends Number implements Serializable {
	
	// The serialVersionUID of the class AbstractRadical.
	private static final long serialVersionUID = 8533279051892799851L;
	
	/** 被开方数 */
	static final String RADICAND = "被开方数";
	/** 平方符号 */
	static final String SQUARE   = " ^ ";
	/** 乘号 */
	static final String MULTIPLY = " × ";

	/** 根指数 */
	protected final Fraction exponent;
	/** 被开方数 */
	protected final int radicand;
	/** 符号函数 */
	protected final int signum;
	/** 系数 */
	protected final int coefficient;
	/** 根式的近似值 */
	protected double doubleValue;
	
	/**
	 * 构造一个根式。
	 * 
	 * @param exponent		指数
	 * @param radicand		被开方数
	 * @param coefficient	系数
	 */
	AbstractRadical(Fraction exponent, int radicand, int coefficient) {
		this.exponent 	 = exponent;
		this.signum 	 = Integer.signum(coefficient);
		this.radicand 	 = radicand;
		this.coefficient = coefficient;
		this.doubleValue = Math.pow(radicand, exponent.doubleValue());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coefficient;
		result = prime * result + radicand;
		result = prime * result + ((exponent == null) ? 0 : exponent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractRadical other = (AbstractRadical) obj;
		if (exponent == null) {
			if (other.exponent != null)
				return false;
		} else if (!exponent.equals(other.exponent))
			return false;
		if (coefficient != other.coefficient)
			return false;
		if (radicand != other.radicand)
			return false;
		return true;
	}

	/**
	 * 将根式写成幂的形式，并返回底数的指数。
	 * 
	 * @return 幂形式的指数
	 */
	public Fraction exponent() {
		return exponent;
	}

	/**
	 * 获取根式的被开方数。
	 * 
	 * @return 被开方数
	 */
	public int radicand() {
		return radicand;
	}

	/**
	 * 返回该根式的正负号函数。
	 * 
	 * @return -1,、0或1，表示此根式的值为负，为零或为正。
	 */
	public int signum() {
		return signum;
	}

	/**
	 * 返回该根式的系数。
	 * 
	 * @return 根式的系数
	 */
	public int coefficient() {
		return coefficient;
	}
	
	@Override
	public String toString() {
		String ret = null;
		if (radicand == 1) {
			ret = Integer.toString(coefficient);
		} else if (coefficient == 1) {
			ret = radicand + SQUARE + exponent.toParenthesisString();
		} else {
			ret = coefficient + MULTIPLY + radicand + SQUARE + exponent.toParenthesisString();
		}
		return ret;
	}
	
	@Override
	public int intValue() {
		return (int) doubleValue;
	}

	@Override
	public long longValue() {
		return (long) doubleValue;
	}

	@Override
	public float floatValue() {
		return (float) doubleValue;
	}

	@Override
	public double doubleValue() {
		return doubleValue;
	}

	public abstract AbstractRadical abs();
	public abstract AbstractRadical negate();
	public abstract AbstractRadical reciprocal();
	public abstract AbstractRadical multiply(int x);
	
}
