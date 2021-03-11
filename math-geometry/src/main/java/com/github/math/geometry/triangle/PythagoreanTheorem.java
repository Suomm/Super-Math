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

package com.github.math.geometry.triangle;

import java.io.Serializable;

/**
 * 勾股定理
 * @author 王帅
 *
 */
public final class PythagoreanTheorem implements Serializable {
	
	// The serialVersionUID of the class PythagoreanTheorem.
	private static final long serialVersionUID = -4367448000281437264L;
	private int side01;
	private int side02;
	private int hypotenuse;
	
	/**
	 * 
	 * @param side01 个
	 * @param side02 不错
	 */
	public PythagoreanTheorem(final int side01, final int side02) {
		this.side01 = side01;
		this.side02 = side02;
	}
	
	private void check(final int i, final int j) {
	}

}
