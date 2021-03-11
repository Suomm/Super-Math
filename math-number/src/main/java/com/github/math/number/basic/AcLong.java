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
import com.github.math.number.fraction.BigFraction;

/**
 * 
 * 
 * @author 王帅
 */
public final class AcLong extends RationalNumber<AcLong> {

	// The serialVersionUID of the class AcLong.
	private static final long serialVersionUID = 4683260456523485667L;

	/**
	 * 关键字{@code long}的字符串形式。
	 */
	public static final String LONG = "long";
	public static final long ONE = 1L;
	
    private long value;

    public AcLong(long value) {
        this.value = value;
    }

    public AcLong(Number n) {
    	this.value = n.longValue();
    }
    
    public AcLong(String s) throws NumberFormatException {
        this.value = Long.parseLong(s, 10);
    }
    
	@Override
	public AcLong abs() {
		return (value < 0) ? new AcLong(-value) : this;
	}

	@Override
	public AcLong pow(int e) {
		return new AcLong((int) Math.pow(value, e));
	}

	@Override
	public AcLong min(AcLong val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcLong max(AcLong val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcLong negate() {
		return new AcLong(-value);
	}

	@Override
	public BigFraction reciprocal() {
		return new BigFraction(ONE, value);
	}

	@Override
	public AcLong add(AcLong val) {
		return new AcLong(value + val.value);
	}

	@Override
	public AcLong add(int i) {
		return new AcLong(value + i);
	}

	@Override
	public AcLong subtract(AcLong val) {
		return new AcLong(value - val.value);
	}

	@Override
	public AcLong subtract(int i) {
		return new AcLong(value - i);
	}

	@Override
	public AcLong multiply(AcLong val) {
		return new AcLong(value - val.value);
	}

	
	@Override
	public AcLong multiply(int i) {
		return new AcLong(value * i);
	}
	
	@Override
	public BigFraction divide(AcLong val) {
		return new BigFraction(value, val.value);
	}

	@Override
	public BigFraction divide(int i) {
		return new BigFraction(value, (long) i);
	}

	@Override
	public int signum() {
		if (value == 0)
			return 0;
		return (value > 0) ? 1 : -1;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return value;
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
		return Long.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		// 判断是否为本类对象。
		if (obj instanceof AcLong) 
            return value == ((AcLong) obj).value;
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
		return Long.toString(value, 10);
	}

	@Override
	public int compareTo(AcLong o) {
		return Long.compare(value, o.value);
	}

}
