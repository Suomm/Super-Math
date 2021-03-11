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

import com.github.math.number.BigArithmetic;
import com.github.math.number.RationalNumber;
import com.github.math.number.fraction.BigFraction;
import com.github.math.number.fraction.Fraction;
import com.github.math.number.fraction.Fractional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * 
 * @author 王帅
 */
public final class BigPercentage 
	extends RationalNumber<BigPercentage>
	implements Fractional, BigArithmetic<BigPercentage> {
	// The serialVersionUID of the class BigPercentage.
	private static final long serialVersionUID = -936486592268435870L;
	
	private final BigDecimal percent;
	private final BigDecimal decimal;
	
	private final BigFraction parent;
	
	/***/
	public static final BigPercentage ZERO = new BigPercentage(0.0);
	/***/
	public static final BigPercentage ONE = new BigPercentage(0.01);
	/***/
	public static final BigPercentage ONE_HUNDRED = new BigPercentage(1.0);
	
	/**
	 * 
	 * @param value g
	 */
	public BigPercentage(double value) {
		this.parent  = new BigFraction(value);
		this.decimal = BigDecimal.valueOf(value);
		this.percent = decimal.movePointRight(2).stripTrailingZeros();
	}
	
	/**
	 * 
	 * @param value g
	 */
	public BigPercentage(BigDecimal value) {
		this(0.0);
	}
	
	/**
	 * 
	 * @param num hb
	 * @param den bn
	 */
	public BigPercentage(BigInteger num, BigInteger den) {
		this.parent  = new BigFraction(num, den);
		this.decimal = parent.bigDecimalValue(scale, roundingMode);
		this.percent = decimal.movePointRight(2).stripTrailingZeros();
	}

	/**
	 * 
	 * @param num fvc
	 */
	public BigPercentage(BigInteger num) {
		this(num, BigInteger.ONE);
	}

	/**
	 * 
	 * @param num cv
	 * @param den cgnnn
	 */
	public BigPercentage(int num, int den) {
		this.parent  = new BigFraction(num, den);
		this.decimal = parent.bigDecimalValue(scale, roundingMode);
		this.percent = decimal.movePointRight(2).stripTrailingZeros();
	}

	/**
	 * 
	 * @param num vgbn
	 */
	public BigPercentage(int num) {
		this(num, 1);
	}

	/**
	 * 
	 * @param num vgn
	 * @param den vgbn
	 */
	public BigPercentage(long num, long den) {
		this.parent  = new BigFraction(num, den);
		this.decimal = parent.bigDecimalValue(scale, roundingMode);
		this.percent = decimal.movePointRight(2).stripTrailingZeros();
	}

	/**
	 * 
	 * @param num vgb
	 */
	public BigPercentage(long num) {
		this(num, 1L);
	}

	/**
	 * 
	 * @param fraction v
	 */
	public BigPercentage(BigFraction fraction) {
		this(fraction.getNumerator(), fraction.getDenominator());
	}
	
	public BigPercentage(Fractional divide) {
		// TODO Auto-generated constructor stub
		this(1.2);
	}

	private static int scale = 16;
	private static int roundingMode = 0; 
	
	/**
	 * 
	 * @param scale g
	 * @param roundingMode g
	 */
	public static void setRound(int scale, int roundingMode) {
		BigPercentage.scale = scale;
		BigPercentage.roundingMode = roundingMode;
	}
	
	@Override
	public String toString() {
		return this.percent.toPlainString();
	}
	
	@Override
	public int hashCode() {
		return 31 + ((percent == null) ? 0 : percent.hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BigPercentage))
			return false;
		final BigPercentage other = (BigPercentage) obj;
		if (!percent.equals(other.percent))
			return false;
		return true;
	}
	
	@Override
	public BigPercentage add(long l) {
		return new BigPercentage(parent.add(l));
	}

	public BigPercentage add(BigFraction fraction) {
		return new BigPercentage(parent.add(fraction));
	}

	@Override
	public BigPercentage divide(long l) {
		return new BigPercentage(parent.divide(l));
	}

	public BigPercentage divide(BigFraction fraction) {
		return new BigPercentage(parent.divide(fraction));
	}

	@Override
	public BigPercentage multiply(long l) {
		return new BigPercentage(parent.multiply(l));
	}

	public BigPercentage multiply(BigFraction fraction) {
		return new BigPercentage(parent.multiply(fraction));
	}

	public double percentageValue() {
		return this.percent.doubleValue();
	}

	@Override
	public BigPercentage pow(long e) {
		return new BigPercentage(parent.pow(e));
	}
	
	@Override
	public BigPercentage subtract(long l) {
		return new BigPercentage(parent.subtract(l));
	}

	public BigPercentage subtract(BigFraction fraction) {
		return new BigPercentage(parent.subtract(fraction));
	}

	@Override
	public BigPercentage add(BigPercentage b) {
		return new BigPercentage(this.decimal.add(b.decimal));
	}

	@Override
	public BigPercentage add(int i) {
		return add(BigDecimal.valueOf(i));
	}

	@Override
	public BigPercentage add(BigInteger b) {
		return add(new BigDecimal(b));
	}

	public BigPercentage add(BigDecimal b) {
		return new BigPercentage(decimal.add(b));
	}

	@Override
	public BigPercentage subtract(BigPercentage b)  {
		return new BigPercentage(decimal.subtract(b.decimal));
	}

	@Override
	public BigPercentage subtract(int i) {
		return subtract(BigDecimal.valueOf(i));
	}

	@Override
	public BigPercentage subtract(BigInteger b) {
		return subtract(new BigDecimal(b));
	}

	public BigPercentage subtract(BigDecimal b) {
		return new BigPercentage(decimal.subtract(b));
	}

	@Override
	public BigPercentage multiply(BigPercentage a)  {
		return new BigPercentage(decimal.multiply(a.decimal));
	}

	@Override
	public BigPercentage multiply(BigInteger b) {
		return multiply(new BigDecimal(b));
	}

	public BigPercentage multiply(BigDecimal b) {
		return new BigPercentage(decimal.multiply(b));
	}

	@Override
	public BigPercentage multiply(int i) {
		return multiply(BigDecimal.valueOf(i));
	}

	@Override
	public BigPercentage divide(BigPercentage b)  {
		return new BigPercentage(decimal.divide(b.decimal));
	}

	@Override
	public BigPercentage divide(int i) {
		return divide(BigDecimal.valueOf(i));
	}

	public BigPercentage divide(BigDecimal b) {
		return new BigPercentage(decimal.divide(b));
	}
	
	@Override
	public BigPercentage min(BigPercentage val) {
		return this.decimal.compareTo(val.decimal) < 0 ? this : val;
	}

	@Override
	public BigPercentage max(BigPercentage val) {
		return this.decimal.compareTo(val.decimal) > 0 ? this : val;
	}

	public BigPercentage pow(int i) {
		return new BigPercentage(parent.pow(i));
	}

	public BigPercentage pow(BigInteger e) {
		return new BigPercentage(parent.pow(e));
	}

	@Override
	public BigPercentage abs() {
		if (this.decimal.signum() == -1) {
			return new BigPercentage(decimal.negate());
		}
		return this;
	}

	@Override
	public BigPercentage negate() {
		return new BigPercentage(decimal.negate());
	}

	@Override
	public BigPercentage reciprocal()  {
		return new BigPercentage(parent.getDenominator(), parent.getNumerator());
	}

	public BigDecimal bigDecimalValue() {
		return this.decimal;
	}
	
	public BigDecimal bigDecimalValueExact(MathContext mc) {
		return this.decimal.round(mc);
	}

	public BigInteger bigIntegerValue() {
		return this.decimal.toBigInteger();
	}

	public BigInteger bigIntegerValueExact() {
		return this.decimal.toBigIntegerExact();
	}

	@Deprecated
	public Fraction fractionValue() {
		return null;
	}

	public int intValueExact() {
		return this.decimal.intValueExact();
	}

	@Override
	public double doubleValue() {
		return this.decimal.doubleValue();
	}

	@Override
	public int signum() {
		return parent.getNumerator().signum();
	}

	@Override
	public BigPercentage divide(BigInteger b) {
		return divide(new BigDecimal(b));
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
	public int compareTo(BigPercentage o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
