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

package com.github.math.expression;

import com.github.math.exception.MathZeroException;
import com.github.math.factorization.Factorization;

import java.io.Serializable;

/**
 * 
 * @author 王帅
 *
 */
public class Fraction implements Expression<Fraction>, Serializable {
	
	// The serialVersionUID of the class Fraction.
	private static final long serialVersionUID = -4048933378577174465L;
	
	/**
	 * 分式的分子。
	 */
	private final Polynomial numerator;
	
	/**
	 * 分式的分母。
	 */
	private final Polynomial denominator;
	
	/**
	 * 
	 * @param num 9
	 * @param den 6
	 */
	public Fraction(Polynomial num, Polynomial den) {
		
		if (den.equals(Polynomial.ZERO)) {
			throw new MathZeroException("LocalizedFormats.DENOMINATOR");
		}
		
		Factorization facNum = new Factorization(num);
		Factorization facDen = new Factorization(den);
		
		num = facNum.getFactor();
		den = facDen.getFactor();

		Monomial monNum = facNum.getCommonFactor();
		Monomial monDen = facDen.getCommonFactor();
		
		Monomial m = monNum.intersection(monDen);
		
		monNum = monNum.perfDivide(m);
		monDen = monDen.perfDivide(m);
		
		this.numerator 	 = num.multiply(monNum);
		this.denominator = den.multiply(monDen);
		
	}
	
	@Override
	public String toString() {
		if (this.numerator.isEmpty()) {
			return "0";
		} else if (this.denominator.toString().equals("1")) {
			return this.numerator.toString();
		}
		return numerator + " / " + denominator;
	}



	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = 31 * result + ((numerator == null) ? 0 : numerator.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Fraction)) {
			return false;
		}
		final Fraction other = (Fraction) obj;
		if (!denominator.equals(other.denominator)) {
			return false;
		}
		if (!numerator.equals(other.numerator)) {
			return false;
		}
		return true;
	}

	@Override
	public Fraction plus(final Fraction t) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Fraction subtract(final Fraction a) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Fraction multiply(final Fraction a) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Fraction divide(final Fraction a) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Fraction negate() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Fraction reciprocal() {
		// TODO 自动生成的方法存根
		return null;
	}

}
