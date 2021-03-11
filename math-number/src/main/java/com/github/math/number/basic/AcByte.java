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
public final class AcByte extends RationalNumber<AcByte> {
	
	// The serialVersionUID of the class AcByte.
	private static final long serialVersionUID = 4683260456523485667L;

	/**
	 * 关键字{@code byte}的字符串形式。
	 */
	public static final String BYTE = "byte";
	
	public static final byte ONE = 1;
	
    private byte value;

    public AcByte(byte value) {
        this.value = value;
    }

    public AcByte(Number n) {
    	this.value = n.byteValue();
    }
    
    public AcByte(String s) throws NumberFormatException {
        this.value = Byte.parseByte(s, 10);
    }

	private AcByte(int value) {
		if (value > Byte.MAX_VALUE)
			throw new NumberOverbrimException(AcInteger.INTEGER, AcByte.BYTE);
		if (value < Byte.MIN_VALUE)
			throw new NumberOverbrimException(AcInteger.INTEGER, AcByte.BYTE);
		this.value = (byte) value;
	}
	
	public static AcByte decode(String nm) throws NumberFormatException {
		int ret = Integer.decode(nm);
		return new AcByte(ret);
	}

	@Override
	public AcByte abs() {
		return (value < 0) ? new AcByte(-value) : this;
	}

	@Override
	public AcByte pow(int e) {
		return new AcByte((byte) Math.pow(value, e));
	}

	@Override
	public AcByte min(AcByte val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcByte max(AcByte val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcByte negate() {
		return new AcByte(-value);
	}

	@Override
	public Fraction reciprocal() {
		return new Fraction(ONE, value);
	}

	@Override
	public AcByte add(AcByte val) {
		return new AcByte(value + val.value);
	}

	@Override
	public AcByte add(int i) {
		return new AcByte(value + i);
	}
	
	@Override
	public AcByte subtract(AcByte val) {
		return new AcByte(value - val.value);
	}

	@Override
	public AcByte subtract(int i) {
		return new AcByte(value - i);
	}


	@Override
	public AcByte multiply(AcByte val) {
		return new AcByte(value - val.value);
	}

	@Override
	public AcByte multiply(int i) {
		return new AcByte(value * i);
	}
	
	@Override
	public Fraction divide(AcByte val) {
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
		return (int) value;
	}
	
	@Override
	public byte byteValue() {
		return value;
	}

	@Override
	public short shortValue() {
		return (short) value;
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
		return Byte.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		// 判断是否为本类对象。
		if (obj instanceof AcByte) 
            return value == ((AcByte) obj).value;
		// 判断是否为小数对象。
		try {
			if (obj instanceof Decimal)
				return value == ((Decimal) obj).byteValueExact();
		} catch (ArithmeticException e) {
			return false;
		}
		// 判断是否为数字对象。
		if (obj instanceof Number)
			return value == ((Number) obj).byteValue();
		return false;
	}

	@Override
	public String toString() {
		return Byte.toString(value);
	}

	@Override
	public int compareTo(AcByte o) {
		return Byte.compare(value, o.value);
	}

}
