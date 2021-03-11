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
public final class AcFloat extends RationalNumber<AcFloat> {

	// The serialVersionUID of the class AcFloat.
	private static final long serialVersionUID = 4683260456523485667L;
	
	/**
	 * 关键字{@code float}的字符串形式。
	 */
	public static final String FLOAT = "float";
	
    private float value;

    public AcFloat(float value) {
        this.value = value;
    }
    
    public AcFloat(Number n) {
    	this.value = n.floatValue();
    }

    public AcFloat(String s) throws NumberFormatException {
        this.value = Float.parseFloat(s);
    }
	@Override
	public AcFloat abs() {
		return (value < 0) ? new AcFloat(-value) : this;
	}

	@Override
	public AcFloat pow(int e) {
		return new AcFloat((int) Math.pow(value, e));
	}

	@Override
	public AcFloat min(AcFloat val) {
		return (value <= val.value) ? this : val;
	}

	@Override
	public AcFloat max(AcFloat val) {
		return (value >= val.value) ? this : val;
	}

	@Override
	public AcFloat negate() {
		return new AcFloat(-value);
	}

	@Override
	@Deprecated
	public Fraction reciprocal() {
		return null;
	}

	@Override
	public AcFloat add(AcFloat val) {
		return new AcFloat(value + val.value);
	}

	@Override
	public AcFloat add(int i) {
		return new AcFloat(value + i);
	}

	@Override
	public AcFloat subtract(AcFloat val) {
		return new AcFloat(value +- val.value);
	}

	@Override
	public AcFloat subtract(int i) {
		return new AcFloat(value - i);
	}

	@Override
	public AcFloat multiply(AcFloat val) {
		return new AcFloat(value - val.value);
	}
	
	@Override
	public AcFloat multiply(int i) {
		return new AcFloat(value * i);
	}

	@Override
	@Deprecated
	public Fraction divide(AcFloat val) {
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
		return value;
	}

	@Override
	public double doubleValue() {
		return (double) value;
	}

	@Override
	public int hashCode() {
		return Float.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		float v;
		if (obj instanceof Number) {
			v = ((Float) obj).floatValue();
            return Float.compare(v, value) == 0;
        }
		return false;
	}

	@Override
	public String toString() {
		return Float.toString(value);
	}

	@Override
	public int compareTo(AcFloat o) {
		return Float.compare(value, o.value);
	}

}
