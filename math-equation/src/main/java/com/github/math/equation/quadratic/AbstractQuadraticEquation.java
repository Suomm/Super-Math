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

package com.github.math.equation.quadratic;

import com.github.math.equation.Equation;

import java.io.Serializable;

/**
 * 
 * @author 		王帅
 * @param <T> 	系数类型
 */
abstract class AbstractQuadraticEquation<T> 
	implements Equation, Serializable {
	// The serialVersionUID of the class null.
	private static final long serialVersionUID = 8556093620277091126L;

	/**
	 * 存放一元二次方程的各项系数。
	 */
	final T[] coefficient;
	
	/**
	 * 一元二次方程根的判别式的值。
	 */
	T delta;
	
	/**
	 * 一元二次方程的两个实数根。
	 */
	final T[] root;
	
	/**
	 * 标识一元二次方程是否计算过根。
	 */
	transient boolean hasRoot; 

	/**
	 * 一元二次方程的字符串表示形式。
	 */
	transient String equation;
	
	/**
	 * 
	 * @param coefficient g
	 * @param root b
	 * @param a vb
	 * @param b vb
	 * @param c bv
	 */
	AbstractQuadraticEquation(final T[] coefficient, 
							  final T[] root, 
							  final T a, final T b, final T c) {
		this.root = root;
		this.coefficient = coefficient;
		this.reset(a, b, c);
	}

	@Override
	public final T[] coefficient() {
		return this.coefficient;
	}
	
	/**
	 * 重置这个一元二次方程的内容。
	 * 
	 * @param a 一元二次方程的一次项系数
	 * @param b 一元二次方程的二次项系数
	 * @param c 一元二次方程的常数项
	 */
	protected void reset(final T a, final T b, final T c) {
		this.coefficient[0] = a;
		this.coefficient[1] = b;
		this.coefficient[2] = c;
		this.hasRoot 		= false;
		this.equation 		= null;
	}
	
	/**
	 * 获取一元二次方程根的判别式的值。
	 * 
	 * @return 一元二次方程根的判别式的值
	 */
	public final T delta() {
		return this.delta;
	}
	
	/**
	 * <p>根据一元二次方程的求根公式计算出方程的两个实数根。
	 * 
	 * <p>一元二次方程的两个实数根会存到数组中返回，这就意味着此数组的长度一定为2。
	 * 这个数组中的两个元素会有以下几种情况（根据一元二次方程根的判别式的值确定）：
	 * <ul>
	 * <li>在<code>delta</code>大于0时，一元二次方程有两个不相等的实数根，
	 * 数组包含的两个元素的值各不相同；
	 * <li>在<code>delta</code>等于0时，一元二次方程有两个相等的实数根，
	 * 数组包含的两个元素的值相同；
	 * <li>在<code>delta</code>小于0时，一元二次方程有无实数根，
	 * 数组包含的两个元素的值全部为{@link Double#NaN}。
	 * </ul>
	 * 
	 * @return 存有一元二次方程两个实数根的数组
	 */
	public final T[] root() {
		if (!hasRoot) {
			fillRoot();
		}
		return root;
	}
	
	abstract void fillRoot();
	
	/**
	 * 判断一元二次方程是否有两个相等的实数根。
	 * 
	 * @return 是否只有一个实数根
	 */
	public abstract boolean hasOneRoot();
	
	/**
	 * 判断一元二次方程是否有两个不相等的实数根。
	 * 
	 * @return 是否有两个实数根
	 */
	public abstract boolean hasTwoRoot();
	
	/**
	 * 判断一元二次方程是否没有实数根。
	 * 
	 * @return 是否无实数根
	 */
	public abstract boolean notHasRoot();
	
	/**
	 * 获取一元二次方程的两个实数根之和。
	 * 
	 * @return 两根之和
	 */
	public abstract T rootSum();
	
	/**
	 * 获取一元二次方程的两个实数根之积。
	 * 
	 * @return 两根之积
	 */
	public abstract T rootProduct();
	
	/**
	 * 根据一元二次方程的系数构建方程的字符串形式。
	 * 
	 * @return 方程的字符串表示形式。
	 */
	abstract String buildEquation();

	@Override
	public final String toString() {
		if (equation == null) {
			equation = buildEquation();
		}
		return equation;
	}

}
