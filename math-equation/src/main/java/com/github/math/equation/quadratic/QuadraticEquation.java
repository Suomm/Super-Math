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

import com.github.math.SuperMath;
import com.github.math.equation.Equation;
import com.github.math.exception.MathIllegalArgumentException;
import com.github.math.exception.MathZeroException;
import com.github.math.number.fraction.ComplexFraction;
import com.github.math.utils.MathUtils;

import java.io.Serializable;

/**
 * <p>
 * 等号两边都是整式
 * 
 * @author 	王帅
 * @since	1.0
 */
public final strictfp class QuadraticEquation 
	implements Equation, Serializable {

	// The serialVersionUID of the class QuadraticEquation.
	private static final long serialVersionUID = 8691924880237695894L;
	
	private static SolveMethod defaultMethod = SolveMethod.FORMULA_METHOD;
	
	static final String SECOND	   = "一元二次方程的二次项系数";
	static final String FIRST	   = "一元二次方程的一次项系数";
	static final String CONSTANT   = "一元二次方程的常数项";
	
	/**
	 * 存放一元二次方程的一次项系数、二次项系数以及常数项。
	 */
	private final double[] coefficient;
	
	/** 
	 * 存放一元二次方程的两个根。
	 */
	private final double[] root;
	
	/**
	 * 
	 */
	private final ComplexFraction[] value;
	
	/**
	 * 标识一元二次方程是否计算过根。
	 */
	private transient boolean hasRoot; 
	
	/**
	 * 
	 */
	private transient boolean hasValue; 

	/**
	 * 一元二次方程根的判别式的值。
	 */
	private double delta;

	/**
	 * delta的符号函数。
	 */
	private int signum;

	/**
	 * 一元二次方程的字符串表示形式。
	 */
	private transient String equation;
	
	/**
	 * 
	 * @param method 解方程的方法。
	 */
	public static void setDefaultSolveMethod(SolveMethod method) {
		defaultMethod = method;
	}
	
	/**
	 * 
	 * @return 默认解方程的方法
	 */
	public SolveMethod getDefaultSolveMethod() {
		return defaultMethod;
	}
	
	/**
	 * <p>用给定的系数值初始化一个一元二次方程。
	 * <p>各项系数必须满足一元二次方程的要求，否则会触发异常。
	 * 
	 * @param a 一元二次方程的二次项系数
	 * @param b 一元二次方程的一次项系数
	 * @param c 一元二次方程的常数项
	 * @exception MathZeroException 如果一元二次方程一次项系数为0，抛出此异常。
	 */
	public QuadraticEquation(double a, double b, double c) {
		this.coefficient = new double[3];
		this.root 		 = new double[2];
		this.value 		 = new ComplexFraction[2];
		this.reset(a, b, c);
	}
	
	/**
	 * 重置这个一元二次方程的内容。
	 * 
	 * @param a 一元二次方程的一次项系数
	 * @param b 一元二次方程的二次项系数
	 * @param c 一元二次方程的常数项
	 * @exception MathZeroException a == 0。
	 * @exception MathIllegalArgumentException
	 * 			  a, b or c is infinite or NaN.
	 */
	public void reset(double a, double b, double c) {
		MathUtils.notZero(a, "");
		MathUtils.checkDouble(a, SECOND);
		MathUtils.checkDouble(b, FIRST);
		MathUtils.checkDouble(c, CONSTANT);
		this.coefficient[0] = a;
		this.coefficient[1] = b;
		this.coefficient[2] = c;
		this.delta 			= b * b - (4.0 * a * c);
		this.equation 		= null;
		this.hasRoot 		= false;
		this.hasValue 		= false;
		this.signum 		= SuperMath.signum(delta);
	}
	
	/**
	 * 根据一元二次方程的系数构建方程的字符串形式。
	 * 
	 * @return 方程的字符串表示形式。
	 */
	private String buildEquation() {
		String s = coefficient[0]+"x²+" + coefficient[1]
				   + "x+"+coefficient[2] + "=0";
		s = s.replaceAll("\\.0(?=[^\\d])|\\+(?=-)", "");
		s = s.replaceAll("[+\\-]0x|[+\\-]0", "");
		return s;
	}

	@Override
	public double[] coefficient() {
		return this.coefficient;
	}

	/**
	 * 获取一元二次方程根的判别式的值。
	 * 
	 * @return 一元二次方程根的判别式的值
	 */
	public double delta() {
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
	public double[] root() {
		if (!hasRoot) {
			fillRoot();
		}
		return root;
	}
	
	/**
	 * <p>计算一元二次方程的<u>近似值</u>。
	 * 
	 * @return 一元二次方程的近似值。
	 */
	public ComplexFraction[] value() {
		if (!hasValue) {
			fillValue();
		}
		return value;
	}

	/**
	 * 判断一元二次方程是否有两个相等的实数根。
	 * 
	 * @return 是否只有一个实数根
	 */
	public boolean hasOneRoot() {
		return signum == 0;
	}
	
	/**
	 * 判断一元二次方程是否有两个不相等的实数根。
	 * 
	 * @return 是否有两个实数根
	 */
	public boolean hasTwoRoot() {
		return signum == 1;
	}
	
	/**
	 * 判断一元二次方程是否没有实数根。
	 * 
	 * @return 是否无实数根
	 */
	public boolean notHasRoot() {
		return signum == -1;
	}
	
	/**
	 * 
	 * 计算一元二次方程的两个根。
	 */
	private void fillRoot() {
		if(hasTwoRoot()) {
			final double sqrt = Math.sqrt(delta);
			root[0] = (-coefficient[1] + sqrt) / (2 * coefficient[0]);
			root[1] = (-coefficient[1] - sqrt) / (2 * coefficient[0]);
		} else if(hasOneRoot()) {
			root[0] = -coefficient[1] / (2 * coefficient[0]);
			root[1] = root[0];
		} else {
			root[0] = Double.NaN;
			root[1] = Double.NaN;
		}
		this.hasRoot = true;
	}
	
	private void fillValue() {
		if(hasTwoRoot()) {
			
		} else if(hasOneRoot()) {
			
		} else {
			
		}
		this.hasValue = true;
	}
	
	/**
	 * 获取一元二次方程的两个实数根之和。
	 * 
	 * @return 两根之和
	 */
	public double rootSum() {
		if (notHasRoot()) {
			return Double.NaN;
		}
		return -coefficient[1] / coefficient[0];
	}
	
	/**
	 * 获取一元二次方程的两个实数根之积。
	 * 
	 * @return 两根之积
	 */
	public double rootProduct() {
		if (notHasRoot()) {
			return Double.NaN;
		}
		return coefficient[0] / coefficient[2];
	}

	@Override
	public String toString() {
		if (equation == null) {
			equation = buildEquation();
		}
		return equation;
	}
	
}
