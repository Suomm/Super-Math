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

import java.math.BigInteger;

/**
 * 
 * 
 * @author 		王帅
 * @since		1.0
 * @param <T> 	8
 * @see			Arithmetic
 */
public interface BigArithmetic<T> extends Arithmetic<T> {
	
	default T pow(long lng) {
		return pow(BigInteger.valueOf(lng));
	}
	
	T pow(BigInteger val);
	
	default T add(long lng) {
		return add(BigInteger.valueOf(lng));
	}
	
	T add(BigInteger val);
	
	default T subtract(long lng) {
		return subtract(BigInteger.valueOf(lng));
	}
	
	T subtract(BigInteger val);
	
	default T multiply(long lng) {
		return multiply(BigInteger.valueOf(lng));
	}
	
	T multiply(BigInteger val);
	
	default T divide(long lng) {
		return divide(BigInteger.valueOf(lng));
	}
	
	T divide(BigInteger val);
	
}
