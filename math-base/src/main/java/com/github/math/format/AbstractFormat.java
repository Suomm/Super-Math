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

package com.github.math.format;

import com.github.math.exception.MathParseException;

import java.io.Serializable;
import java.text.FieldPosition;
import java.text.ParsePosition;

/**
 * 
 * @author 王帅
 * 
 * @param <T> 123
 *
 */
public abstract class AbstractFormat<T> implements Serializable {

	// The serialVersionUID of the class AbstractFormat.
	private static final long serialVersionUID = -2614111276299166321L;

	protected AbstractFormat() {
	}
	
	public final String format(T obj) {
        return format(obj, new StringBuffer(), new FieldPosition(0)).toString();
    }
	
	public abstract StringBuffer format(T obj, 
					StringBuffer toAppendTo, 
					FieldPosition pos);

	public final T parse(String source) throws MathParseException {
		return parse(source, new ParsePosition(0));
	}

	public abstract T parse(String source, ParsePosition pos) throws MathParseException;

	public final Object parseObject(String source, ParsePosition pos) throws MathParseException {
		return parse(source, pos);
	}

	public final Object parseObject(String source) {
		return parseObject(source, new ParsePosition(0));
	}
	
	protected static void parseNextCharacter(String source, ParsePosition pos) {
		int index = pos.getIndex();
		final int n = source.length();

		if (index < n) {
			char c;
			do {
				c = source.charAt(index++);
			} while (Character.isWhitespace(c) && index < n);
			pos.setIndex(index);
		}

	}
	
	protected static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
		parseNextCharacter(source, pos);
		pos.setIndex(pos.getIndex() - 1);
	}
	
}
