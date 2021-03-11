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

/**
 * <p>
 * {@code Decimal} 接口的实现类都可以用小数来表示。接口规定了将小数转换成整数，并检查丢失的信息的方法，
 * 如果这个实现类表示的是一个整数，则可以转换为相应的整型；但如果实现类表示的小数具有非零小数部分，或者超出了
 * 对应整型结果的可能范围，则会抛出{@code ArithmeticException} 异常。
 * 
 * @author 王帅
 */
public interface Decimal {
	
	/**
	 * 将此 {@code Decimal} 转换为 {@code int}，检查丢失的信息。 
	 * 如果这个 {@code Decimal} 具有非零小数部分，或者超出了{@code int}
	 * 结果的可能范围，则抛出 {@code ArithmeticException} 。 
	 * 
	 * @return 将此 {@code Decimal} 转换成 {@code int}。
	 * @throws ArithmeticException 如果 {@code this} 具有非零小数部分，或不适合 {@code int}。 
	 */
	int intValueExact() throws ArithmeticException;
	
	/**
	 * 将此 {@code Decimal} 转换为 {@code long}，检查丢失的信息。 
	 * 如果这个 {@code Decimal} 具有非零小数部分，或者超出了{@code long}
	 * 结果的可能范围，则抛出 {@code ArithmeticException} 。 
	 * 
	 * @return 将此 {@code Decimal} 转换成 {@code long}。
	 * @throws ArithmeticException 如果 {@code this} 具有非零小数部分，或不适合 {@code long}。 
	 */
	long longValueExact() throws ArithmeticException;
	
	/**
	 * 将此 {@code Decimal} 转换为 {@code short}，检查丢失的信息。 
	 * 如果这个 {@code Decimal} 具有非零小数部分，或者超出了{@code short}
	 * 结果的可能范围，则抛出 {@code ArithmeticException} 。 
	 * 
	 * @return 将此 {@code Decimal} 转换成 {@code short}。
	 * @throws ArithmeticException 如果 {@code this} 具有非零小数部分，或不适合 {@code short}。 
	 */
	default short shortValueExact() throws ArithmeticException {
		return (short) intValueExact();
	}
	
	/**
	 * 将此 {@code Decimal} 转换为 {@code byte}，检查丢失的信息。 
	 * 如果这个 {@code Decimal} 具有非零小数部分，或者超出了{@code byte}
	 * 结果的可能范围，则抛出 {@code ArithmeticException} 。 
	 * 
	 * @return 将此 {@code Decimal} 转换成 {@code byte}。
	 * @throws ArithmeticException 如果 {@code this} 具有非零小数部分，或不适合 {@code byte}。 
	 */
	default byte byteValueExact() throws ArithmeticException {
		return (byte) intValueExact();
	}

}
