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

package com.github.math.number.radical;

import com.github.math.exception.MathNegativeException;
import com.github.math.number.fraction.Fraction;

/**
 * <p>
 * 每一个 {@code QuadraticRadial} 类都封装着一些二次根式（Quadratic Radial）。它被应用到二次根式计算器（Quadratic
 * Radical Calculator）中，进行二次根式的相关计算。因此只能通过{@link #sqrt(int)}方法创建对象，并且只可以进行一些二次根
 * 式的简单操作，像：相反数、绝对值、乘法等。 如果想要进行更加复杂的二次根式操作，需要借助<b>二次根式计算器</b>
 * （Quadratic Radical Calculator）进行操作。
 * <blockquote><pre><code>
 * QuadraticRadical x = QuadraticRadical.sqrt(2);
 * System.out.println(x.abs());
 * System.out.println(x.negate());
 * System.out.println(x.multiply(5));
 * ......
 * </code></pre></blockquote>
 * <p>
 * 一般地，本类表示的二次根式形如<b>&plusmn;a&radic;b</b>形式，其中，<i>a</i> 叫做二次根式的系数；<i>b</i> 叫做二次根
 * 式的被开方数。具体的开方方式请参见源代码{@link #sqrt(int)}方法，封装后的二次根式更有利于进行复杂的运算。
 * 
 * @author 	王帅
 * @since	1.0
 * @version 1.3
 * @see		QuadraticRadicalCalculator
 */
public final class QuadraticRadical 
	extends AbstractRadical implements Comparable<QuadraticRadical> {

	// The serialVersionUID of the class QuadraticRadial.
	private static final long serialVersionUID = -4328138710337303086L;

	/** 根号 */
	private static final String SIGN_OF_EVOLUTION = "√"; 
	/** 负号 */ 
	private static final String SIGN_OF_NEGATIVE  = "-"; 
	/** 零 （{@code String}）*/
	private static final String STRING_TYPE_ZERO  = "0";
	
	// 二次根式的一些常用常量

	/** 表示“0”的二次根式 */
	public static final QuadraticRadical ZERO      = new QuadraticRadical(0); 
	/** 表示“1”的二次根式 */
	public static final QuadraticRadical ONE       = new QuadraticRadical(1); 
	/** 表示“2”的二次根式 */
	public static final QuadraticRadical TWO       = new QuadraticRadical(2); 
	/** 表示“3”的二次根式 */
	public static final QuadraticRadical THREE     = new QuadraticRadical(3); 
	/** 表示“-1”的二次根式 */
	public static final QuadraticRadical MINUS_ONE = new QuadraticRadical(-1);
	
	/** 表示“√2”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_TWO   = new QuadraticRadical(1, 2);
	/** 表示“√3”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_THREE = new QuadraticRadical(1, 3);
	/** 表示“√5”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_FIVE  = new QuadraticRadical(1, 5); 
	/** 表示“√6”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_SIX   = new QuadraticRadical(1, 6); 
	/** 表示“√7”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_SEVEN = new QuadraticRadical(1, 7); 
	/** 表示“√10”的二次根式 */
	public static final QuadraticRadical SQUARE_ROOT_OF_TEN   = new QuadraticRadical(1, 10); 
	
	/** 表示“2√2”的二次根式 */
	public static final QuadraticRadical TWO_TIMES_SQUARE_ROOT_OF_TWO   = new QuadraticRadical(2, 2); 
	/** 表示“2√3”的二次根式 */
	public static final QuadraticRadical TWO_TIMES_SQUARE_ROOT_OF_THREE = new QuadraticRadical(2, 3); 
	/** 表示“2√5”的二次根式 */
	public static final QuadraticRadical TWO_TIMES_SQUARE_ROOT_OF_FIVE  = new QuadraticRadical(2, 5); 
	
	// 二次根式构造器
	
	/**
	 * 使用二次根式的系数与被开方数创建二次根式对象。
	 * 
	 * @param coefficient 	系数
	 * @param radicand		被开方数
	 */
	private QuadraticRadical(int coefficient, int radicand) {
		super(Fraction.ONE_HALF, radicand, coefficient);
	}
	
	/**
	 * 使用二次根式的系数直接创建二次根式对象。
	 * 
	 * @param coefficient	系数
	 */
	private QuadraticRadical(int coefficient) {
		this(coefficient, 1);
	}
	
	// 初始化二次根式的静态方法
	
	/**
	 * 直接利用系数初始化一个二次根式，它的系数将为指定的值，被开方数将为 <b>1</b>。
	 * 
	 * @param  coefficient 系数
	 * @since  1.2
	 * @return 开方后的二次根式（a&radic;1 形式）
	 */
	public static QuadraticRadical of(int coefficient) {
		return new QuadraticRadical(coefficient);
	}

	/**
	 * <p>
	 * 根式的化简，开方为形如 <b>a&radic;b</b> 形式的二次根式。通过此方法获得二次
	 * 根式类，以便进行复杂的运算。
	 * 
	 * <p>
	 * 具体采用迭代缩进算法（详细说明参见源代码），改进算法之后，循环次数大幅度减少，化简出
	 * 错率也大幅度降低。比较前几代算法，最大的突出优点就是错误率降低，单次二次根式化简效率
	 * 提升了几十倍左右，在 0&minus;{@code Integer#MAX_VALUE} 范围之内均可
	 * 快速无误地化简二次根式。但唯一不足之处就是，在化简较大的数时，循环次数仍达到几万以上，
	 * 却提高了化简的正确率。
	 * 
	 * @param 	  radicand 被开方数
	 * @return    开方后的二次根式（a&radic;b 形式）
	 * @exception MathNegativeException 当被开方数为负数时抛出此异常
	 */
	public static QuadraticRadical sqrt(int radicand) {
		// 被开方数不能小于零
		if (radicand < 0) {
			throw new MathNegativeException(RADICAND);
		}
		// 被开方数为零
		if (radicand == 0) {
			return ZERO;
		}
		// 被开方数小于等于三时
		if (radicand <= 3) {
			return new QuadraticRadical(1, radicand);
		}
		
		int pow;
		// 通过遍历提取系数
		for (int i = (int) Math.sqrt(radicand); i >= 2; i--) {
			pow = i * i;
			if (radicand % pow == 0) {
				return new QuadraticRadical(i, radicand / pow);
			}
		}
		// 系数为一被开方数为质数
		return new QuadraticRadical(1, radicand);
		
	}
	
	// 简单的二次根式的计算
	
	/**
	 * 相同被开方数的两个二次根式的加法运算。系数相加，被开方数不变。
	 * 
	 * @param a 加数
	 * @param b 加数
	 * @return  两个二次根式的和
	 */
	static QuadraticRadical add(QuadraticRadical a, QuadraticRadical b) {
		return new QuadraticRadical(a.coefficient + b.coefficient, a.radicand);
	}
	
	/**
	 * 返回该二次根式的绝对值。
	 * 
	 * @return 二次根式的绝对值
	 */
	@Override
	public QuadraticRadical abs() {
		return new QuadraticRadical(Math.abs(coefficient), radicand);
	}
	
	/**
	 * 返回该二次根式的倒数。
	 * 
	 * @return 二次根式的倒数
	 * @deprecated 没有实现的方法
	 */
	@Override
	@Deprecated
	public QuadraticRadical reciprocal() {
		return null;
	}
	
	/**
	 * 返回该二次根式的相反数。
	 * 
	 * @return 二次根式的相反数
	 */
	@Override
	public QuadraticRadical negate() {
		return new QuadraticRadical(-coefficient, radicand);
	}
	
	
	/**
	 * 返回该二次根式乘以整数后的值。计算方法为，被开方数不变，系数乘以指定的整数。
	 * 
	 * @param  x 与二次根式相乘的数
	 * @return 运算之后的二次根式
	 */
	@Override
	public QuadraticRadical multiply(int x) {
		return new QuadraticRadical(coefficient * x, radicand);
	}
	
	/**
	 * <p>计算两个二次根式相乘的值。
	 * 
	 * <p>
	 * 计算法则如下：
	 * <ul>
	 * <li> 被开方数相同:系数相乘，再乘以相同的被开方数
	 * <li> 被开方数不同：系数相乘，被开方数相乘
	 * </ul>
	 * 
	 * @param x 相乘的二次根式
	 * @return  相乘之后的值
	 */
	public QuadraticRadical multiply(QuadraticRadical x) {
		// 被开方数相同
		if (radicand == x.radicand) {
			return new QuadraticRadical(coefficient * x.coefficient * radicand);
		}
		// 被开方数不同
		return new QuadraticRadical(coefficient * x.coefficient, radicand * x.radicand);
	}
	
	/**
	 * <p>
	 * 根据本类的表示规范，返回 &plusmn;a&radic;b 形式的二次根式。对于一些特殊情况，会做一些特殊的处理。
	 * 
	 * <p>
	 * 具体输出列表如下：
	 * <ul> 
	 * <li> 系数等于 0：返回 0
	 * <li> 被开方数等于 1: 返回系数
	 * <li> 系数等于 1，被开方数不为 1：返回 &radic;b 形式的二次根式
	 * <li> 系数等于 &minus;1，被开方数不为 1：返回 &minus;&radic;b 形式的二次根式
	 * <li> 没有其他特殊情况：返回 &plusmn;a&radic;b 形式的二次根式
	 * </ul>
	 * 
	 * @return &plusmn;a&radic;b 形式的二次根式
	 */
	@Override
	public String toString() {
		String str = null;
		if (radicand == 1) {
			// 被开方数等于1
			str = Integer.toString(coefficient);
		} else if (coefficient == 0) {
			// 系数等于0
			str = STRING_TYPE_ZERO;
		} else if (coefficient == 1) {
			// 系数等于1，被开方数不为1
			str = SIGN_OF_EVOLUTION + radicand;
		} else if (coefficient == -1) {
			// 系数等于-1，被开方数不为1
			str = SIGN_OF_NEGATIVE + SIGN_OF_EVOLUTION + radicand;
		} else {
			// 没有其他特殊情况
			str = coefficient + SIGN_OF_EVOLUTION + radicand;
		}
		return str;
	}
	
	/**
	 * <p>
	 * 按规定比较两个二次根式。仅用于对二次根式进行排序，具体规定如下：
	 * <ul>
	 * <li> 先比较被开方数：按被开方数升序排列
	 * <li> 被开方数相同时：在比较二次根式的系数，按系数升序排列
	 * </ul>
	 * 因此，<strong>该方法只用于二次根式的排序，并不是真实大小的比较。</strong>
	 * 
	 * <p>
	 * 如果想要比较两个二次根式的真实大小，请使用
	 * {@link QuadraticRadical#compare(QuadraticRadical, QuadraticRadical)}
	 * 进行比较大小。
	 * 
	 * @param o 相比较的二次根式
	 * @return  两者排序的相对值
	 */
	@Override
	public int compareTo(QuadraticRadical o) {
		// 先比较被开方数
		int cpr = radicand - o.radicand;
		if (cpr == 0) {
			// 被开方数相同时，在比较根式的系数
			return coefficient - o.coefficient;
		}
		return cpr;
	}
	
	/**
	 * 比较两个二次根式的真实大小。
	 * 
	 * @param a 第一个二次根式
	 * @param b 第二个二次根式
	 * @return  0：a=b；1：a&gt;b；-1：a&lt;b
	 */
	public static int compare(QuadraticRadical a, QuadraticRadical b) {
		return Double.compare(a.doubleValue, b.doubleValue);
	}

}
