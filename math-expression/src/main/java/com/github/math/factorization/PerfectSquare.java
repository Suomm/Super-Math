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

package com.github.math.factorization;


import com.github.math.expression.Polynomial;

import java.io.Serializable;


/**
 * 
 * @author 王帅
 *
 */
public final class PerfectSquare implements Serializable {
	
	// The serialVersionUID of the class PerfectSquare.
	private static final long serialVersionUID = 209062094212659606L;
	@SuppressWarnings("unused")
	private final Polynomial factor;

	/**
	 * 
	 * @param factor 9
	 */
	public PerfectSquare(final Polynomial factor) {
//		if (factor.size() < 3) {
//			throw new MathCheckException(factor + "不能分解！");
//		}
		this.factor = factor;
	}

}
