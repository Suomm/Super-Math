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

package com.github.math.number;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 科学计数法
 * @author 王帅
 *
 */
public final class ScientificNotation implements Serializable {

	// The serialVersionUID of the class ScientificNotation.
	private static final long serialVersionUID = 2049852459671776454L;
	
	private final BigDecimal base;
	private final int exponent;
	
	public ScientificNotation(final double value) {
		this(Double.toString(value), false);
	}
	
	public ScientificNotation(final float value) {
		this(Float.toString(value), false);
	}
	
	public ScientificNotation(final long value) {
		this(Long.toString(value), true);
	}
	
	/**
	 * 
	 * @param value 小数
	 */
	public ScientificNotation(final BigDecimal value) {
		this(value.toString(), value.scale() == 0);
	}
	
	/**
	 * 
	 * @param value g
	 */
	public ScientificNotation(final BigInteger value) {
		this(value.toString(), true);
	}
	
	/**
	 * 
	 * @param value c
	 */
	private ScientificNotation(final String value, final boolean isInt) {
		
		if (isInt) {
			this.exponent = value.length() - 1;
			this.base = new BigDecimal(value).movePointLeft(exponent);
			return;
		}
		
		if (value.contains("E")) {
			final String[] val = value.split("E");
			final int exp = Integer.parseInt(val[1]);
			if (ok(val[0])) {
				this.exponent = exp;
				this.base = new BigDecimal(val[0]);
			} else {
				final int index = val[0].indexOf('.');
				this.exponent = exp + index - 1;
				final StringBuilder sb = new StringBuilder(val[0]);
				this.base = new BigDecimal(sb.deleteCharAt(index).insert(1, '.').toString());
			}
			return;
		}
		
		if (value.startsWith("0.")) {
			int exp = 0;
			final StringBuilder sb = new StringBuilder(value);
			sb.deleteCharAt(1);
			for (int i = 0; i < sb.length(); i++) {
				if (sb.charAt(i) != '0') {
					break;
				}
				exp--;
			}
			this.exponent = exp;
			this.base = new BigDecimal(sb.insert(-exp + 1, '.').toString());
			return;
		}
		
		if (ok(value)) {
			this.exponent = 0;
			this.base = new BigDecimal(value);
		} else {
			final int index = value.indexOf('.');
			this.exponent = index - 1;
			final StringBuilder sb = new StringBuilder(value);
			this.base = new BigDecimal(sb.deleteCharAt(index).insert(1, '.').toString());
		}
	}
	
	private boolean ok(final String decimal) {
		return decimal.indexOf('.') == 1;
	}
	
	public BigDecimal getBase() {
		return this.base;
	}

	public int getExponent() {
		return this.exponent;
	}
	
	public BigDecimal getNumber() {
		return this.base.movePointRight(exponent);
	}

	@Override
	public String toString() {
		
		if (this.base.equals(BigDecimal.ZERO)) {
			return "0";
		}
		
		final String result = this.base.stripTrailingZeros().toString();
		
		if (this.exponent == 0) {
			return result;
		}
		
		final String sign = Integer.signum(exponent) == -1 ? "" : "+";
		
		return result + "E" + sign + this.exponent;
	}
	
}
