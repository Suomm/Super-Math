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

import com.github.math.exception.MathArithmeticException;
import com.github.math.number.fraction.Fractional;

/**
 * <p>
 * {@code Arithmetic} 接口规定了一些常用的计算方法。例如：四则运算（加、减、乘、除），绝对值，
 * 相反数，倒数等等。
 * 
 * @author 		王帅
 * @since		1.0
 * @param <T> 	类型
 */
public interface Arithmetic<T> {
	
	/**
	 * 返回当前对象的绝对值。如果该对象表示的值为正，则返回原对象；
	 * 如果该对象表示的值为负，则返回原对象的相反数。
	 * 
	 * @return 当前对象的绝对值
	 */
	T abs();
	
	/**
	 * 返回当前对象的{@code e}次幂。
	 * 
	 * @param e 指数
	 * @return  {@code this}<sup>e</sup>
	 */
	T pow(int e);

	/**
	 * 比较 {@code this} 与 {@code val} 的值，并返回其中较小的一个。
	 * 
	 * @param val 将要比较的值
	 * @return    两值中较小的一个
	 */
	T min(T val);

	/**
	 * 比较 {@code this} 与 {@code val} 的值，并返回其中较大的一个。
	 * 
	 * @param val 将要比较的值
	 * @return    两值中较大的一个
	 */
	T max(T val);

	/**
	 * 返回当前对象的相反数。
	 * 
	 * @return {@code -this}
	 */
	T negate();

	/**
	 * 返回当前对象的倒数。
	 * 
	 * @return {@code 1 / this}
	 * @throws MathArithmeticException 如果 {@code this == 0} 抛出异常
	 */
	Fractional reciprocal() throws MathArithmeticException;
	
	/**
	 * 向当前对象中添加数值。
	 * 
	 * @param val 要添加的值
	 * @return	  {@code this + val}
	 */
	T add(T val);
	
	/**
	 * 向当前对象中添加 {@code int} 类型数值。
	 * 
	 * @param i 要添加的值
	 * @return  {@code this + i}
	 */
	T add(int i);
	
	/**
	 * 从当期对象中减去一部分数值。
	 * 
	 * @param val 要减去的值
	 * @return    {@code this - val}
	 */
	T subtract(T val);
	
	/**
	 * 从当期对象中减去一部分 {@code int} 类型数值。
	 * 
	 * @param i 要减去的值
	 * @return  {@code this - i}
	 */
	T subtract(int i);
	
	/**
	 * 返回该对象乘以 {@code val} 后的值。
	 * 
	 * @param val 要相乘的值
	 * @return	  {@code this * val}
	 */
	T multiply(T val);
	
	/**
	 * 返回该对象乘以 {@code i} 后的值。
	 * 
	 * @param i 要相乘的值
	 * @return  {@code this * i}
	 */
	T multiply(int i);
	
	/**
	 * 返回该对象除以 {@code val} 后的值。
	 * 
	 * @param val 要除以的值
	 * @return    {@code this / value}
	 * @throws MathArithmeticException 如果 {@code val == 0} 抛出异常
	 */
	Fractional divide(T val) throws MathArithmeticException;
	
	/**
	 * 返回该对象除以 {@code i} 后的值。
	 * 
	 * @param i 要除以的值
	 * @return  {@code this / i}
	 * @throws MathArithmeticException 如果 {@code i == 0} 抛出异常
	 */
	Fractional divide(int i) throws MathArithmeticException;
	
}
