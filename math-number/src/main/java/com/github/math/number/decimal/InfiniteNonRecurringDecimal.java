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

package com.github.math.number.decimal;

import com.github.math.number.IrrationalNumber;

/**
 * 无限不循环小数(英文名：infinite non-repeating decimals )就是小数点后有无数位，
 * 但和无限循环小数不同，它没有周期性的重复，换句话说就是没有规律，所以数学上又称无限不循环小数叫
 * 做无理数（如圆周率π,它就是一个无理数）,把其他一切实数都称为有理数。
 *
 * @author 王帅
 *
 */
public abstract class InfiniteNonRecurringDecimal 
	extends IrrationalNumber<InfiniteNonRecurringDecimal> {
	// // The serialVersionUID of the class InfiniteNonRecurringDecimal.
	private static final long serialVersionUID = -8265450176448718248L;
}
