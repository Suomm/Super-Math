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

package com.github.math.equation.format;

import com.github.math.equation.quadratic.QuadraticEquation;
import com.github.math.exception.MathParseException;
import com.github.math.format.AbstractFormat;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 *
 * @author 王帅
 */
public class QuadraticEquationFormat extends AbstractFormat<QuadraticEquation> {

	// The serialVersionUID of the class QuadraticEquationFormat.
	private static final long serialVersionUID = 6537262408165914131L;

	private NumberFormat coefficientFormat;
	
	public QuadraticEquationFormat() {
		this(NumberFormat.getNumberInstance());
	}

	public QuadraticEquationFormat(NumberFormat coefficientFormat) {
		setCoefficientFormat(coefficientFormat);
	}
	
	public NumberFormat getCoefficientFormat() {
		return coefficientFormat;
	}

	/**
	 *  
	 * @param coefficientFormat b
	 */
	public void setCoefficientFormat(NumberFormat coefficientFormat) {
		this.coefficientFormat = coefficientFormat;
	}

	@Override
	public StringBuffer format(QuadraticEquation obj, 
							   StringBuffer toAppendTo, 
							   FieldPosition pos) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	@Override
	public QuadraticEquation parse(String source, ParsePosition pos) {
		double a, b, c;
		int index;
		char v;
		
		char[] value = source.toCharArray();
		
		a = coefficientFormat.parse(source, pos).doubleValue();
		
		index = pos.getIndex();
		v = value[++index];
		
		if (v != '²') 
			throw new MathParseException(source, index);
		
		v = value[++index];
		pos.setIndex(index);
		
		if (v == '=') {
			b = 0.0;
			c = 0.0;
		} else {
			b = coefficientFormat.parse(source, pos).doubleValue(); 
		}
		
		index = pos.getIndex();
		v = value[index++];
		
		if (v == '=') {
			c = b;
			b = 0.0;
		} else {
			v = value[index];
			if (v == '=') {
				c = 0.0;
			} else { 
				pos.setIndex(index);
				c = coefficientFormat.parse(source, pos).doubleValue();
			}
		}
		final int n = value.length;
		index = pos.getIndex();
		
		if ((index + 1) == n) {
			throw new MathParseException(source, index);
		}
		
		pos.setIndex(++index);
		double e = coefficientFormat.parse(source, pos).doubleValue();
		index = pos.getIndex();
		if (index == n) {
			c -= e;
		} else {
			v = value[index++];
			if (v == 'x') {
				b -= e;
			}
			pos.setIndex(index);
			e = coefficientFormat.parse(source, pos).doubleValue();
			index = pos.getIndex();
			if (index == n) {
				c -= e;
			} else {
				b -= e;
			}
		}
		
		return new QuadraticEquation(a, b, c);
	}
	
	

}
