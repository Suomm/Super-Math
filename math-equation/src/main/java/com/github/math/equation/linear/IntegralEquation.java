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

package com.github.math.equation.linear;

import com.github.math.equation.Equation;
import com.github.math.utils.MathUtils;

import java.io.Serializable;

/**
 * 整式方程
 * @author 王帅
 *
 */
public final class IntegralEquation implements Equation, Serializable {

	// The serialVersionUID of the class IntegralEquation.
	private static final long serialVersionUID = 8453870775994266777L;
	
	private final double[] coefficient;
	private transient String equation;

	/**
	 * 
	 * @param a b
	 * @param b b
	 */
	public IntegralEquation(double a, double b) {
		this.coefficient = new double[2];
		this.reset(a, b);
	}
	
	static final String A = "一元一次方程一次项系数";
	static final String B = "一元一次方程常数项";
	
	/**
	 * 
	 * @param a n
	 * @param b n
	 */
	public void reset(double a, double b) {
		MathUtils.notZero(a, "LocalizedFormats.SECOND_COEFFICIENT");
		this.coefficient[0] = a;
		this.coefficient[1] = b;
	}
	
	public double root() {
		return -coefficient[1] / coefficient[0];
	}
	
	private String buildEquation() {
		return null;
	}
	
	@Override
	public String toString() {
		if (equation == null) {
			equation = buildEquation();
		}
		return equation;
	}

	@Override
	public double[] coefficient() {
		return this.coefficient;
	}

}
