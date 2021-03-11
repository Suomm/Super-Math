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

package com.github.math.number.basic;

import com.github.math.exception.NumberOverbrimException;
import com.github.math.number.RationalNumber;
import com.github.math.number.decimal.Decimal;
import com.github.math.number.fraction.Fraction;

/**
 * 
 * 
 * @author 王帅
 */
public final class AcShort extends RationalNumber<AcShort> {
	
	// The serialVersionUID of the class AcShort.
	private static final long serialVersionUID = 4683260456523485667L;

	/**
	 * 关键字{@code short}的字符串形式。
	 */
	public static final String SHORT = "short";
	public static final int ONE = 1;
	
    private short value;

    public AcShort(short value) {
        this.value = value;
    }

    public AcShort(Number n) {
    	this.value = n.shortValue();
    }
    
    public AcShort(String s) throws NumberFormatException {
        this.value = Short.parseShort(s, 10);
    }
    
	private AcShort(int value) {
		if (value > Short.MAX_VALUE)
			throw new NumberOverbrimException(AcInteger.INTEGER, AcByte.BYTE);
		if (value < Short.MIN_VALUE)
			throw new NumberOverbrimException(AcInteger.INTEGER, AcByte.BYTE);
		this.value = (short) value;
	}

	@Override
	public AcShort abs() {
		return (value < 0) ? new AcShort(-value) : this;
	}

	@Override
	public AcShort pow(int e) {
		return new AcShort((int) Math.pow(value, e));
	}

	@Override
	public AcShort min(AcShort val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcShort max(AcShort val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcShort negate() {
		return new AcShort(-value);
	}

	@Override
	public Fraction reciprocal() {
		return new Fraction(ONE, value);
	}

	@Override
	public AcShort add(AcShort val) {
		return new AcShort(value + val.value);
	}

	@Override
	public AcShort add(int i) {
		return new AcShort(value + i);
	}

	@Override
	public AcShort subtract(AcShort val) {
		return new AcShort(value +- val.value);
	}

	@Override
	public AcShort subtract(int i) {
		return new AcShort(value - i);
	}


	@Override
	public AcShort multiply(AcShort val) {
		return new AcShort(value - val.value);
	}
	
	@Override
	public AcShort multiply(int i) {
		return new AcShort(value * i);
	}

	@Override
	public Fraction divide(AcShort val) {
		return new Fraction(value, val.value);
	}

	@Override
	public Fraction divide(int i) {
		return new Fraction(value, i);
	}

	@Override
	public int signum() {
		if (value == 0)
			return 0;
		return (value > 0) ? 1 : -1;
	}

	@Override
	public byte byteValue() {
		return (byte) value;
	}

	@Override
	public short shortValue() {
		return value;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return (long) value;
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

	@Override
	public double doubleValue() {
		return (double) value;
	}

	@Override
	public int hashCode() {
		return Short.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		// 判断是否为本类对象。
		if (obj instanceof AcShort) 
            return value == ((AcShort) obj).value;
		// 判断是否为小数对象。
		try {
			if (obj instanceof Decimal)
				return value == ((Decimal) obj).shortValueExact();
		} catch (ArithmeticException e) {
			return false;
		}
		// 判断是否为数字对象。
		if (obj instanceof Number)
			return value == ((Number) obj).shortValue();
		return false;
	}

	@Override
	public String toString() {
		return Short.toString(value);
	}

	@Override
	public int compareTo(AcShort o) {
		return Short.compare(value, o.value);
	}

}
