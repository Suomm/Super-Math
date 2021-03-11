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

import com.github.math.number.RationalNumber;
import com.github.math.number.decimal.Decimal;
import com.github.math.number.fraction.Fraction;

/**
 * 
 * 
 * @author 王帅
 */
public final class AcInteger extends RationalNumber<AcInteger> {

	// The serialVersionUID of the class AcInteger.
	private static final long serialVersionUID = 4683260456523485667L;

	/**
	 * 关键字{@code int}的字符串形式。
	 */
	public static final String INTEGER = "int";
	public static final int ONE = 1;
	
    private int value;

    public AcInteger(int value) {
        this.value = value;
    }

    public AcInteger(Number n) {
    	this.value = n.intValue();
    }
    
    public AcInteger(String s) throws NumberFormatException {
        this.value = Integer.parseInt(s, 10);
    }
    
	@Override
	public AcInteger abs() {
		return (value < 0) ? new AcInteger(-value) : this;
	}

	@Override
	public AcInteger pow(int e) {
		return new AcInteger((int) Math.pow(value, e));
	}

	@Override
	public AcInteger min(AcInteger val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcInteger max(AcInteger val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcInteger negate() {
		return new AcInteger(-value);
	}

	@Override
	public Fraction reciprocal() {
		return new Fraction(ONE, value);
	}

	@Override
	public AcInteger add(AcInteger val) {
		return new AcInteger(value + val.value);
	}

	@Override
	public AcInteger add(int i) {
		return new AcInteger(value + i);
	}

	@Override
	public AcInteger subtract(AcInteger val) {
		return new AcInteger(value - val.value);
	}

	@Override
	public AcInteger subtract(int i) {
		return new AcInteger(value - i);
	}

	@Override
	public AcInteger multiply(int i) {
		return new AcInteger(value * i);
	}

	@Override
	public AcInteger multiply(AcInteger val) {
		return new AcInteger(value - val.value);
	}

	@Override
	public Fraction divide(AcInteger val) {
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
	public int intValue() {
		return value;
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
		return Integer.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		// 判断是否为本类对象。
		if (obj instanceof AcInteger) 
            return value == ((AcInteger) obj).value;
		// 判断是否为小数对象。
		try {
			if (obj instanceof Decimal)
				return value == ((Decimal) obj).intValueExact();
		} catch (ArithmeticException e) {
			return false;
		}
		// 判断是否为数字对象。
		if (obj instanceof Number)
			return value == ((Number) obj).intValue();
		return false;
	}

	@Override
	public String toString() {
		return Integer.toString(value, 10);
	}

	@Override
	public int compareTo(AcInteger o) {
		return Integer.compare(value, o.value);
	}

}
