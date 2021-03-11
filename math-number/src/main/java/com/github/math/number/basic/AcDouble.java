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
import com.github.math.number.fraction.Fraction;

/**
 * 
 * 
 * @author 王帅
 */
public final class AcDouble extends RationalNumber<AcDouble> {

	// The serialVersionUID of the class AcDouble.
	private static final long serialVersionUID = 4683260456523485667L;
	
	/**
	 * 关键字{@code double}的字符串形式。
	 */
	public static final String DOUBLE = "double";

    private double value;

    public AcDouble(double value) {
        this.value = value;
    }

    public AcDouble(Number n) {
    	this.value = n.doubleValue();
    }
    
    public AcDouble(String s) throws NumberFormatException {
        this.value = Integer.parseInt(s, 10);
    }
    
	@Override
	public AcDouble abs() {
		return (value < 0) ? new AcDouble(-value) : this;
	}

	@Override
	public AcDouble pow(int e) {
		return new AcDouble((int) Math.pow(value, e));
	}

	@Override
	public AcDouble min(AcDouble val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcDouble max(AcDouble val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcDouble negate() {
		return new AcDouble(-value);
	}

	@Override
	@Deprecated
	public Fraction reciprocal() {
		return null;
	}

	@Override
	public AcDouble add(AcDouble val) {
		return new AcDouble(value + val.value);
	}

	@Override
	public AcDouble add(int i) {
		return new AcDouble(value + i);
	}

	@Override
	public AcDouble subtract(AcDouble val) {
		return new AcDouble(value - val.value);
	}

	@Override
	public AcDouble subtract(int i) {
		return new AcDouble(value - i);
	}

	@Override
	public AcDouble multiply(AcDouble val) {
		return new AcDouble(value - val.value);
	}
	
	@Override
	public AcDouble multiply(int i) {
		return new AcDouble(value * i);
	}

	@Override
	@Deprecated
	public Fraction divide(AcDouble val) {
		return null;
	}

	@Override
	@Deprecated
	public Fraction divide(int i) {
		return null;
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
		return (long) value;
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		double v;
		if (obj instanceof Number) {
			v = ((Number) obj).doubleValue();
            return Double.compare(v, value) == 0;
        }
		return false;
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}

	@Override
	public int compareTo(AcDouble o) {
		return Double.compare(value, o.value);
	}

}
