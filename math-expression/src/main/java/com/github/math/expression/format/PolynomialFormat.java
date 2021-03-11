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

package com.github.math.expression.format;


import com.github.math.expression.Monomial;
import com.github.math.expression.Polynomial;
import com.github.math.format.AbstractFormat;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Iterator;

/**
 * BigFractionFormat
 * 
 * 
 * @author 王帅
 */
public final class PolynomialFormat extends AbstractFormat<Polynomial> {

	// The serialVersionUID of the class PolynomialFormat.
	private static final long serialVersionUID = 4916553076526955939L;
	
	private final MonomialFormat monomialFormat;
	
	public PolynomialFormat() {
		this.monomialFormat = new MonomialFormat();
	}
	
	public PolynomialFormat(NumberFormat coefficientFormat) {
		this.monomialFormat = new MonomialFormat(coefficientFormat);
	}
	
	/**
	 * 
	 * 
	 * @return coefficientFormat
	 */
	public NumberFormat getCoefficientFormat() {
		return this.monomialFormat.coefficientFormat;
	}
	
	public MonomialFormat getMonomialFormat() {
		return this.monomialFormat;
	}
	
	/**
	 * 
	 * @param nf fd
	 */
	public void setCoefficientFormat(NumberFormat nf) {
		this.monomialFormat.setCoefficientFormat(nf);
	}
	
	@Override
	public Object clone() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public StringBuffer format(Polynomial obj, 
							   StringBuffer toAppendTo, 
							   FieldPosition pos) {
		
		Iterator<Monomial> it = obj.iterator();
		
		Monomial m;
		while (it.hasNext()) {
			m = it.next();
			monomialFormat.format(m, toAppendTo, pos);
		}
		
		return toAppendTo;
	}

	@Override
	public Polynomial parse(String source, ParsePosition pos) {
		String[] text = source.replaceAll(" ", "")
				.replaceAll("-", "+-").split("[+]");
		Monomial[] value = new Monomial[text.length];
		
		for (int i = 0; i < value.length; i++) {
			value[i] = monomialFormat.parse(text[i], pos);
			pos.setIndex(0);
		}
		
		pos.setIndex(source.length() - 1);
		return new Polynomial(value);
	}

}
