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

import com.github.math.SuperMath;
import com.github.math.exception.MathArithmeticException;
import com.github.math.number.IrrationalNumber;
import com.github.math.number.fraction.Fractional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.math.utils.MathUtils.notEmpty;

/**
 * <p>
 * 
 * 
 * 
 * @author 	王帅
 * @see		QuadraticRadical
 */
public final class QuadraticRadicalCalculator 
	extends IrrationalNumber<QuadraticRadicalCalculator> {

	// The serialVersionUID of the class QuadraticRadicalCalculator.
	private static final long serialVersionUID = -8294383240167007579L;

	public static final QuadraticRadicalCalculator ZERO = new QuadraticRadicalCalculator(new ArrayList<>());
	
	/**
	 * 存放有二次根式的集合。
	 */
	private final ArrayList<QuadraticRadical> elements;
	
	/**
	 * 所有二次根式表示的近似值。
	 */
	private final double doubleValue;
	
	
	/**
	 * 使用流对象生成一个二次根式计算器。
	 * 
	 * @param stream 包含二次根式的流
	 */
	private QuadraticRadicalCalculator(Stream<QuadraticRadical> stream) {
		this.elements    = stream.collect(Collectors.toCollection(ArrayList::new));
		this.doubleValue = initDoubleValue();
	}
	
	/**
	 * 使用集合对象生成一个二次根式计算器。
	 * 
	 * @param elements 包含二次根式的集合
	 */
	private QuadraticRadicalCalculator(ArrayList<QuadraticRadical> elements) {
		this.elements    = elements;
		this.doubleValue = initDoubleValue();
	}
	
	/**
	 * 初始化计算后的近似值。
	 * 
	 * @return 表示式子的近似值
	 */
	private double initDoubleValue() {
		double init = 0.0;
		for (QuadraticRadical radical : elements) {
			init += radical.doubleValue;
		}
		return init;
	}
	
	/**
	 * <p>
	 * 通过化简后的二次根式来构建二次根式计算器。这些指定的二次根式将进行加法操作，生成二次根式计算器，
	 * 用于进行复杂的二次根式计算。
	 * 
	 * @param elements 	二次根式
	 * @return			二次根式计算器
	 */
	public static QuadraticRadicalCalculator of(QuadraticRadical... elements) {
		// 元素个数不能为零
		notEmpty(elements);
		// 创建一个二次根式计算器
		return reduce(Stream.of(elements));
	}
	
	/**
	 * 分组计算二次根式的和
	 * 
	 * @param  stream 包含二次根式的流
	 * @return 整合后的二次根式计算器
	 */
	private static QuadraticRadicalCalculator reduce(Stream<QuadraticRadical> stream) {
		// 最终整合的元素
		ArrayList<QuadraticRadical> value = new ArrayList<>();
		// 按照被开方数进行分组
		var group = stream.collect(Collectors.groupingByConcurrent(QuadraticRadical::radicand));
		// 整合添加元素
		for (List<QuadraticRadical> simple : group.values()) {
			QuadraticRadical ret;
			if (simple.size() == 1) {
				// 只有一个元素的集合直接添加
				ret = simple.get(0);
			} else {
				// 同被开方数的二次根式进行相加
				ret = simple.stream().reduce(QuadraticRadical::add).orElseThrow();
			}
			// 二次根式不为零添加到集合
			if (ret.signum != 0) {
				value.add(ret);
			}
		}
		return new QuadraticRadicalCalculator(value);
	}

	@Override
	public int intValueExact() {
		QuadraticRadical x = elements.get(0);
		// 集合中只有一个元素并且这个元素的被开方数必须为一
		if (elements.size() == 1 && x.radicand == 1) {
			return x.coefficient;
		} else {
			throw new ArithmeticException();
		} 
	}

	@Override
	public long longValueExact() {
		return intValueExact();
	}

	@Override
	public QuadraticRadicalCalculator abs() {
		if (doubleValue < 0.0) {
			return negate();
		}
		return this;
	}

	@Override
	public QuadraticRadicalCalculator pow(int e) {
		QuadraticRadicalCalculator result = this;
		for (; e > 1; e--) {
			result = result.multiply(this);
		}
		return result;
	}

	@Override
	public QuadraticRadicalCalculator min(QuadraticRadicalCalculator val) {
		return this.compareTo(val) > 0 ? this : val;
	}

	@Override
	public QuadraticRadicalCalculator max(QuadraticRadicalCalculator val) {
		return this.compareTo(val) < 0 ? this : val;
	}

	@Override
	public QuadraticRadicalCalculator negate() {
		return new QuadraticRadicalCalculator(elements.stream().map(QuadraticRadical::negate));
	}

	@Override
	@Deprecated
	public Fractional reciprocal() {
		return null;
	}

	@Override
	public QuadraticRadicalCalculator add(QuadraticRadicalCalculator val) {
		return reduce(Stream.concat(elements.stream(), val.elements.stream()));
	}

	@Override
	public QuadraticRadicalCalculator add(int i) {
		var ret = elements; // 含有二次根式的集合
		boolean isAdd = true; // 是否替换被开方数为一的二次根式
		var it = ret.listIterator(); // 获取迭代器
		
		// 迭代判断是否含有被开方数为一的二次根式
		while (it.hasNext()) {
			QuadraticRadical value = it.next();
			if (value.radicand == 1) {
				i += value.coefficient;
				if (i == 0) {
					it.remove();
				} else {
					it.set(QuadraticRadical.of(i));
				}
				// 集合中含有被开方数为一的二次根式，不用添加
				isAdd = false;
				break;
			}
		}
		
		// 添加没有的元素
		if (isAdd) {
			ret.add(QuadraticRadical.of(i));
		}
		
		return new QuadraticRadicalCalculator(ret);
	}

	@Override
	public QuadraticRadicalCalculator subtract(QuadraticRadicalCalculator val) {
		return add(val.negate());
	}

	@Override
	public QuadraticRadicalCalculator subtract(int i) {
		return add(-i);
	}

	@Override
	public QuadraticRadicalCalculator multiply(QuadraticRadicalCalculator val) {
		var ret = elements.stream().flatMap((e) -> {
			// 用 val 中的每个值去乘以 this 中的每个值
			return val.elements.stream().map((x) -> x.multiply(e));
		});
		return reduce(ret);
	}

	@Override
	public QuadraticRadicalCalculator multiply(int m) {
		switch (m) {
		case 0:
			return ZERO;
		case 1:
			return this;
		case -1:
			return negate();
		default:
			return new QuadraticRadicalCalculator(elements.stream().map((e) -> e.multiply(m)));
		}
	}
	
	/**
	 * 返回该对象乘以 {@code m} 后的值。
	 * 
	 * @param m 相乘的二次根式
	 * @return  {@code this * m}
	 */
	public QuadraticRadicalCalculator multiply(QuadraticRadical m) {
		return new QuadraticRadicalCalculator(elements.stream().map((e) -> e.multiply(m)));
	}

	@Override
	@Deprecated
	public Fractional divide(QuadraticRadicalCalculator val) 
			throws MathArithmeticException {
		return null;
	}

	@Override
	@Deprecated
	public Fractional divide(int i) {
		return null;
	}

	@Override
	public int signum() {
		return SuperMath.signum(doubleValue);
	}

	@Override
	public int intValue() {
		return (int) doubleValue;
	}

	@Override
	public long longValue() {
		return (long) doubleValue;
	}

	@Override
	public float floatValue() {
		return (float) doubleValue;
	}

	@Override
	public double doubleValue() {
		return this.doubleValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(doubleValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof QuadraticRadicalCalculator))
			return false;
		QuadraticRadicalCalculator other = (QuadraticRadicalCalculator) obj;
		return Double.doubleToLongBits(doubleValue) == Double.doubleToLongBits(other.doubleValue);
	}

	@Override
	public String toString() {
		// 空集合表示零
		if (elements.isEmpty()) {
			return "0";
		}
		
		// 集合不为空添加元素
		StringBuilder bur = new StringBuilder();
		elements.stream().sorted().forEach((e) -> {
			if (e.signum == -1) {
				bur.append(e);
			} else {
				bur.append('+').append(e);
			}
		});
		
		// 以加号开头去除加号
		if (bur.charAt(0) == '+') {
			bur.deleteCharAt(0);
		}
		
		return bur.toString();
	}

	@Override
	public int compareTo(QuadraticRadicalCalculator o) {
		return Double.compare(doubleValue, o.doubleValue);
	}

}
