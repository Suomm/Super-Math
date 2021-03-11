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

package com.github.math.number.radical;

import com.github.math.SuperMath;
import com.github.math.exception.MathNegativeException;
import com.github.math.number.fraction.Fraction;

/**
 * 大型根式的运算。
 * 
 * @author 王帅
 */
public class Radical extends AbstractRadical implements Comparable<Radical> {
	
	// The serialVersionUID of the class BigRadical.
	private static final long serialVersionUID = -1557709294527980413L;
	
	Radical(Fraction exponent, int coefficient, int radicand) {
		super(exponent, radicand, coefficient);
	}
	
	public static Radical root(int radicand, int root) {
		
		if (radicand == 0) {
			return null;
		}
		
		if (root < 0) {
			throw new MathNegativeException("根指数不能为负！");
		}
		
		if ((root&1) == 0 && radicand < 0) {
			throw new MathNegativeException(RADICAND);
		}
		
		return null;
	}
	
	static long simplePOW(long x, int e) {
		long cx = x;
		for (; e > 1; e--) {
			cx *= x;
		}
		return cx;
	}

	@Override
	public int compareTo(Radical o) {
		int cpr = this.exponent.compareTo(o.exponent);
		if (cpr == 0) {
			cpr = radicand - o.radicand;
			if (cpr == 0) {
				return coefficient - o.coefficient;
			}
			return cpr;
		}
		return cpr;
	}

	@Override
	public Radical abs() {
		return new Radical(exponent, SuperMath.abs(coefficient), radicand);
	}

	@Override
	public Radical negate() {
		return new Radical(exponent, -coefficient, radicand);
	}

	@Override
	public AbstractRadical reciprocal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical multiply(int m) {
		return new Radical(exponent, m * coefficient, radicand);
	}
}
