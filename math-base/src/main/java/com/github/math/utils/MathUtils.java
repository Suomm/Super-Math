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

package com.github.math.utils;

import com.github.math.SuperMath;
import com.github.math.exception.MathArithmeticException;
import com.github.math.exception.MathIllegalArgumentException;
import com.github.math.exception.MathZeroException;

/**
 * 
 * @author 王帅
 *
 */
public final class MathUtils {
	
	private MathUtils() {}
	
	public static int mulAndCheck(int x, int y) {
		long m = ((long)x) * ((long)y);
        if (m < Integer.MIN_VALUE || m > Integer.MAX_VALUE) {
            throw new MathArithmeticException();
        }
        return (int) m;
	}
	
	
	public static void notNaN(float x) {
		if (Float.isNaN(x)) {
			throw new MathIllegalArgumentException();
		}
	}
		
	
	public static void notNaN(double x) {
		if (Double.isNaN(x)) {
			throw new MathIllegalArgumentException();
		}
	}
	
	public static <T> void notEmpty(T[] array) {
		if (array.length == 0) {
			throw new MathIllegalArgumentException("");
		}
	}
	
	public static void notZero(int x, String message) {
		if (x == 0) {
			throw new MathZeroException(message);
		}
	}
	
	public static void notZero(double num, String message) {
		if (SuperMath.signum(num) == 0) {
			throw new MathZeroException(message);
		}
	}
	
	public static void checkDouble(double x, String type) {
		if (Double.isNaN(x)) {
			throw new MathIllegalArgumentException(type);
		}
		
		if (Double.isInfinite(x)) {
			throw new MathIllegalArgumentException(type);
		}
	}

}
