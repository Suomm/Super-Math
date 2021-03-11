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

import com.github.math.exception.MathParseException;
import com.github.math.expression.Monomial;
import com.github.math.format.AbstractFormat;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * 
 * 
 * @author 王帅
 */
public final class MonomialFormat extends AbstractFormat<Monomial> {

	// The serialVersionUID of the class MonomialFormat.
	private static final long serialVersionUID = -2011848992342884343L;
	
	NumberFormat coefficientFormat;
	
	public MonomialFormat() {
		this(NumberFormat.getNumberInstance());
	}

	public MonomialFormat(NumberFormat coefficientFormat) {
		setCoefficientFormat(coefficientFormat);
	}
	
	/**
	 * 
	 * 
	 * @return coefficientFormat
	 */
	public NumberFormat getCoefficientFormat() {
		return coefficientFormat;
	}
	
	/**
	 * 
	 * @param coefficientFormat coefficientFormat
	 */
	public void setCoefficientFormat(NumberFormat coefficientFormat) {
		this.coefficientFormat = coefficientFormat;
	}

	@Override
	public StringBuffer format(Monomial monomial, 
							   StringBuffer toAppendTo, 
							   FieldPosition pos) {
		
		pos.setBeginIndex(0);
		pos.setEndIndex(0);
		
		coefficientFormat.format(monomial.coefficient(), toAppendTo, pos);
		
		String s = toAppendTo.toString();
		
		switch (s) {
		case "1":
			toAppendTo.deleteCharAt(0);
			break;
		case "-1":
			toAppendTo.deleteCharAt(1);
			break;
		}
		
		
		Iterator<Map.Entry<Character, Integer>> it = monomial.iterator();
		Map.Entry<Character, Integer> e;
		
		while (it.hasNext()) {
			e = it.next();
			toAppendTo.append(e.getKey().charValue());
			if (e.getValue() != 1) {
				toAppendTo.append('^').append(e.getValue().intValue());
			}
		}
			
		return toAppendTo;
	}

	@Override
	public Monomial parse(String source, ParsePosition pos) {
		parseAndIgnoreWhitespace(source, pos);
		final int coefficient;
		Number num = coefficientFormat.parse(source, pos);
		
		if (num == null) {
			
			int start = pos.getIndex();
			switch (source.charAt(0)) {
			case '-':
				// -abc
				coefficient = -1;
				pos.setIndex(start + 1);
				break;
			case '+':
				// +abc
				coefficient = 1;
				pos.setIndex(start + 1);
				break;
			default:
				// abc
				coefficient = 1;
				break;
			}
			
		} else {
			coefficient = num.intValue();
		}
		
		// 0abc
		if (coefficient == 0) {
			return Monomial.ZERO;
		}
		
		HashMap<Character, Integer> letter = new HashMap<>();
		putLetter(source, pos, letter);
		
		if (pos.getIndex() == 0) {
			throw new MathParseException(source, pos.getErrorIndex());
		}
		
		pos.setIndex(source.length() - 1);
		return new Monomial(coefficient, letter);
	}
	
	private void putLetter(String source, 
						   ParsePosition pos, 
						   HashMap<Character, Integer> letter) {
		
		int index = pos.getIndex();
		
        final int n = source.length();
        
        char c;
        char next;
        while (index < n) {
			
        	c = source.charAt(index++);
        	
        	// 1.3^abc, *bf, 10+bc
			if (!Character.isLetter(c)) {
				error(pos, index);
				break;
			}
        	
			if (index < n) {
				
				next = source.charAt(index);
				
				// 1.2abc, abc
				if (next != '^') {
					put(c, 1, letter);
					continue;
				}
				
				pos.setIndex(index + 1);
				int i = initIndex(source, pos);
				
				// exception
				if (pos.getIndex() == 0) {
					break;
				}
				
				// a^0bc
				if (i == 0) {
					index += 2;
					continue;
				}
				
				// 1.2a^23bc, a^2bc
				put(c, i, letter);
				index = pos.getIndex();
				continue;
			}
			
			put(c, 1, letter);
			pos.setIndex(index);
			break;
		}
	}
	
	private void put(char c, int add, HashMap<Character, Integer> letter) {
		if (letter.containsKey(c)) {
			letter.put(c, letter.get(c) + add);
		} else {
			letter.put(c, add);
		}
	}
	
	private void error(ParsePosition pos, int index) {
		pos.setIndex(0);
		pos.setErrorIndex(index - 1);
	}
	
	private int initIndex(String source, ParsePosition pos) {
		int index = pos.getIndex();
		final int n = source.length();
		
		char c;
		
		do {
			if (n == index) {
				index++;
				break;
			}
			c = source.charAt(index++);
		} while (Character.isDigit(c));
		
		--index;
		
		// abc^
		if (pos.getIndex() == index) {
			error(pos, index);
			return -1;
		}
		
		final String num = source.substring(pos.getIndex(), index);
		
		pos.setIndex(index);
		return Integer.parseInt(num);
	}

}
