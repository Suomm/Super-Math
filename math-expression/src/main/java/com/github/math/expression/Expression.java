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

/*
 * Super Math 是一款功能强大的数学软件，可以通过计算机来将生活中复杂的数学运算或问题变得异常轻松，
 * 上手容易，简单便捷。通过在键盘上来敲一些所需要的式子（或是数字），便能灵活的做出一些运算。
 * 
 * 您可以登录以下网址获取所需要的资源，包括测试文件以及说明（或者联系作者QQ:1474983351）：
 * 
 * 				http://www.mixed.com/Super-Math
 * 
 * Super Math 作为学生必备的学习神器，专为广大学生量身定做，它综合了每个学年段的所有数学知识，无需
 * 过多时间，便可得到简洁、准确的答案。是学霸和学渣的一大福音，同样也可是数学老师的教学利器。让学习变得
 * 不再艰难，解题更加轻松，考试高分不再是梦想。
 */
package com.github.math.expression;

import com.github.math.exception.MathArithmeticException;

/**
 * <p>
 * {@code Expression}是所有数学式类都应该实现的标准接口。它规定了一些常用的计算方法，
 * 像加、减、乘、除、取反、倒数等，实现类必须遵循这种规则。{@code Expression}接口面向
 * 所有的数学式，所以接口中定义的方法全部数学式都可以实现，但单项式（{@link Monomial}）
 * 有些特殊，它的方法相对于其他数学式来说较多，像绝对值、乘方、开方等操作他都可以实现。
 * 
 * <p>
 * 在取反与除法的计算中，{@code Expression}接口规定：<strong>所有数学式计算后都应
 * 该作为分式返回结果</strong>。这也是为了照顾大多数特殊情况，以单项式为例参照下列等式：
 * <blockquote><pre>
 * ab / 4abc = 1 / 4c
 * </pre></blockquote>
 * 两个单项式相除结果可能是一个分式，但也可能是一个单项式，为了统一情况结果会作为分式返回，但是
 * 也可以用分式的一些方法转换回来（如果整除）。多项式的除法操作就更为复杂了，当然结果也会返回分
 * 式。对于倒数操作那是一定要作为分式返回，毕竟分子是固定常数，这种特殊情况少。
 * 
 * <p>
 * 加减计算的操作主要为了单项式，两个单项式相加也会出现两种情况，还是为了照顾大多数的情况，加减
 * 计算的返回值为待定（{@code Expression<?>}）类型，下面是单项式加法的两种情况（减法
 * 计算一样）：
 * <blockquote><pre>
 * ab + 4abc = 4a^2b^2c
 * ab + 4def = ab + 4def
 * </pre></blockquote>
 * 两个单项式相加结果可能还是一个单项式，但是还有一种可能是多项式，这就造成了单项式相加的返回值类
 * 型问题。为了统一类型，在标定接口{@code Expression}中，加减法的返回值规定为待定类型。
 * 只在单项式中加减法的返回值为多项式，其他的类中返回值皆为其本身类型。
 * 
 * <p>
 * {@code Expression}接口虽然规定了有限的几种方法，但是为了统一数学式的概念，进行数学式的
 * 相关计算，类应该实现此接口。
 * 
 * @author 		王帅
 * @since		1.0
 * @param <T> 	数学式类型
 * @see			Fraction
 * @see			Monomial
 * @see			Polynomial
 */
public interface Expression<T> {

	/**
	 * 返回其值为{@code (-this)}的式子。
	 * 
	 * @return {@code -this}
	 */
	T negate();
	
	/**
	 * 返回其值为{@code (1 / this)}的分式。
	 * 
	 * @return {@code 1 / this}
	 * @throws MathArithmeticException 如果 {@code this == 0} 抛出异常
	 */
	Fraction reciprocal() throws MathArithmeticException;
	
	/**
	 * 返回其值为{@code (this + val)}的式子。
	 * 
	 * @param val 加数
	 * @return {@code this + val}
	 */
	Expression<?> plus(T val);
	
	/**
	 * 返回其值为{@code (this - val)}的式子。
	 * 
	 * @param val 减数
	 * @return {@code this - val}
	 */
	Expression<?> subtract(T val);

	/**
	 * 返回其值为{@code (this * val)}的式子。
	 * 
	 * @param val 乘数
	 * @return {@code this * val}
	 */
	T multiply(T val);

	/**
	 * 返回其值为{@code (this / val)}的分式。
	 * 
	 * @param val 除数
	 * @return {@code this / val}
	 * @throws MathArithmeticException 如果 {@code val == 0} 抛出异常
	 */
	Fraction divide(T val) throws MathArithmeticException;
	
}
