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

package com.github.math.number.percentage;

import com.github.math.SuperMath;
import com.github.math.number.RationalNumber;
import com.github.math.number.fraction.Fraction;
import com.github.math.number.fraction.Fractional;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author 	王帅
 * @see		Fraction
 * @see		BigPercentage
 */
public final class Percentage 
	extends RationalNumber<Percentage>
	implements Fractional {
	
	// The serialVersionUID of the class Percentage.
	private static final long serialVersionUID = -6994909099105530276L;

	private final double percent;
	private final double decimal;
	private final int 	 signum;
	
	private final Fraction parent;
	
	private static final double HUNDRED = 100.0;
	
	/***/
	public static final Percentage ZERO = new Percentage(0.0);
	/***/
	public static final Percentage ONE = new Percentage(0.01);
	/***/
	public static final Percentage TEN = new Percentage(0.1);
	/***/
	public static final Percentage FIFTY = new Percentage(0.5);
	/***/
	public static final Percentage ONE_HUNDRED = new Percentage(1.0);
	
	
	/**
	 * 
	 * @param value s
	 */
	@Deprecated
	public Percentage(double value) {
		this.parent  = null;
		this.decimal = value;
		this.percent = value * HUNDRED;
		this.signum  = SuperMath.signum(value);
	}
	
	/**
	 * 
	 * @param num v
	 * @param den v
	 */
	public Percentage(int num, int den) {
		this.parent  = new Fraction(num, den);
		this.decimal = parent.doubleValue();
		this.percent = decimal * HUNDRED;
		this.signum  = SuperMath.signum(decimal);
	}

	/**
	 * 
	 * @param num v
	 */
	public Percentage(int num) {
		this.parent  = new Fraction(num);
		this.decimal = parent.doubleValue();
		this.percent = decimal * HUNDRED;
		this.signum  = SuperMath.signum(decimal);
	}

	/**
	 * @param fraction f
	 */
	public Percentage(Fraction fraction) {
		this.parent  = fraction;
		this.decimal = fraction.doubleValue();
		this.percent = decimal * HUNDRED;
		this.signum  = SuperMath.signum(decimal);
	}

	public double percentageValue() {
		return this.percent;
	}

	@Override
	public String toString() {
		return SuperMath.toString(percent);
	}
	
	@Override
	public int hashCode() {
		final long temp = Double.doubleToLongBits(percent);
		return 31 + (int) (temp ^ (temp >>> 32));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Percentage))
			return false;
		final Percentage other = (Percentage) obj;
		if (Double.compare(decimal, other.decimal) != 0)
			return false;
		return true;
	}
	
	@Override
	public Percentage abs() {
		if (signum == -1) {
			return new Percentage(-decimal);
		}
		return this;
	}

	@Override
	public Percentage pow(int i) {
		return new Percentage(Math.pow(decimal, i));
	}

	@Override
	public Percentage negate() {
		return new Percentage(-decimal);
	}

	@Override
	public int signum() {
		return this.signum;
	}

	@Override
	public Percentage reciprocal() {
		return new Percentage(1 / decimal);
	}

	@Override
	public Percentage min(Percentage val) {
		return new Percentage(Double.min(decimal, val.decimal));
	}

	@Override
	public Percentage max(Percentage val) {
		return new Percentage(Double.max(decimal, val.decimal));
	}

	public Percentage add(int i) {
		return new Percentage(decimal + i);
	}

	public Percentage add(double d) {
		return new Percentage(decimal + d);
	}

	public Percentage add(Fraction f) {
		return new Percentage(parent.add(f));
	}

	@Override
	public Percentage add(Percentage p) {
		return new Percentage(decimal + p.decimal);
	}

	@Override
	public Percentage subtract(int i) {
		return new Percentage(decimal - i);
	}
	
	public Percentage subtract(double d) {
		return new Percentage(decimal - d);
	}

	public Percentage subtract(Fraction f) {
		return new Percentage(parent.subtract(f));
	}

	@Override
	public Percentage subtract(Percentage p) {
		return new Percentage(decimal - p.decimal);
	}

	@Override
	public Percentage multiply(int i) {
		return new Percentage(decimal * i);
	}

	public Percentage multiply(double d) {
		return new Percentage(decimal * d);
	}

	public Percentage multiply(Fraction f) {
		return new Percentage(parent.multiply(f));
	}

	@Override
	public Percentage multiply(Percentage p) {
		return new Percentage(decimal * p.decimal);
	}

	public Percentage divide(int i) {
		return new Percentage(decimal / i);
	}
	
	public Percentage divide(double d) {
		return new Percentage(decimal / d);
	}

	public Percentage divide(Fraction f) {
		return new Percentage(parent.divide(f));
	}

	@Override
	public Percentage divide(Percentage p) {
		return new Percentage(decimal / p.decimal);
	}

	@Override
	public double doubleValue() {
		return this.decimal;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Percentage o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
