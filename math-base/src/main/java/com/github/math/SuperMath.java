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

package com.github.math;

import com.github.math.exception.MathIllegalArgumentException;
import com.github.math.node.AddNode;
import com.github.math.node.Node;
import com.github.math.node.ValNode;
import com.github.math.utils.MathUtils;

import java.math.BigDecimal;

/**
 * <p>
 * {@code SuperMath} 类包含用于执行基本数学运算的方法。
 * 
 * @author 王帅
 * @since  1.0
 */
public final class SuperMath {

	private SuperMath() {}
	
	/**
	 * 返回 {@code float} 类型的正符号函数。
	 * 
	 * @param x   一个 {@code float} 类型的小数
	 * @return	  &minus;1， 0， 1分别表示负数，零和正数
	 * @exception MathIllegalArgumentException 如果{@code float}类型的值为{@code NaN}
	 */
	public static int signum(float x) {
		MathUtils.notNaN(x);
		int value = Float.compare(x, 0.0f);
		if (value > 0) {
			return 1;
		} else if (value < 0) {
			return -1;
		}
		return 0;
	}

	/**
	 * 返回 {@code double} 类型的正符号函数。
	 * 
	 * @param x   一个 {@code double} 类型的小数
	 * @return	  &minus;1， 0， 1分别表示负数，零和正数
	 * @exception MathIllegalArgumentException 如果{@code double}类型的值为{@code NaN}
	 */
	public static int signum(double x) {
		MathUtils.notNaN(x);
		return Double.compare(x, 0.0D);
	}

	/**
	 * <p>
	 * 返回 {@code float} 参数的字符串表示形式。
	 * 
	 * <ul>
	 * <li>如果参数为 {@code NaN}，那么结果为字符串 "{@code NaN}"。
	 * <li>否则，结果是表示参数符号和数值（绝对值）的字符串。
	 * 
	 * <ul>
	 * <li>如果符号为负，那么结果的第一个字符是'&minus;'；
	 * <li>如果符号为正，那么结果中不显示符号字符。
	 * </ul>
	 * </ul>
	 * 
	 * <p>
	 * 对于数值 <i>m</i>：
	 * 
	 * <ul>
	 * <li>如果 <i>m</i> 为无穷大，则用字符"{@code Infinity}"表示；因此，正无穷大生成结果
	 * "{@code Infinity}"，负无穷大生成结果 "&minus;{@code Infinity}。
	 * 
	 * <li>如果 <i>m</i> 为{@code 0}，则用字符"{@code 0}"表示；
	 * 因此，负{@code 0}生成结果"{@code -0}"，正{@code 0}生成结果"{@code 0}"。
	 * 
	 * <li>如果 <i>m</i> 大于或者等于 10<sup>&minus;3</sup>，但小于 10<sup>7</sup>，
	 * 则采用不带前导{@code 0}的十进制形式。
	 * 
	 * <li>如果 <i>m</i> 小于 10<sup>&minus;3</sup> 或大于等于 10<sup>7</sup>，
	 * 则使用所谓的"科学记数法"表示；
	 * 
	 * </ul>
	 * 
	 * <p>
	 * 如果参数为整数值，那么输出后的结果将相当于调用{@link Integer#toString()}
	 * 方法，把参数作为整数值进行输出，更加符合实际也易于表达。
	 * 
	 * @param x 要转换的 {@code float} 值。
	 * @return	参数的字符串表示形式。
	 */
	public static String toString(float x) {
		String value = Float.toString(x);
		value = value.replaceFirst("\\.0$|\\.0(?=E)", "");
		return value;
	}

	/**
	 * <p>
	 * 返回 {@code double} 参数的字符串表示形式。
	 * 
	 * <ul>
	 * <li>如果参数为 {@code NaN}，那么结果为字符串 "{@code NaN}"。
	 * <li>否则，结果是表示参数符号和数值（绝对值）的字符串。
	 * 
	 * <ul>
	 * <li>如果符号为负，那么结果的第一个字符是'&minus;'；
	 * <li>如果符号为正，那么结果中不显示符号字符。
	 * </ul>
	 * </ul>
	 * 
	 * <p>
	 * 对于数值 <i>m</i>：
	 * 
	 * <ul>
	 * <li>如果 <i>m</i> 为无穷大，则用字符"{@code Infinity}"表示；因此，正无穷大生成结果
	 * "{@code Infinity}"，负无穷大生成结果 "&minus;{@code Infinity}。
	 * 
	 * <li>如果 <i>m</i> 为{@code 0}，则用字符"{@code 0}"表示；
	 * 因此，负{@code 0}生成结果"{@code -0}"，正{@code 0}生成结果"{@code 0}"。
	 * 
	 * <li>如果 <i>m</i> 大于或者等于 10<sup>&minus;3</sup>，但小于 10<sup>7</sup>，
	 * 则采用不带前导{@code 0}的十进制形式。
	 * 
	 * <li>如果 <i>m</i> 小于 10<sup>&minus;3</sup> 或大于等于 10<sup>7</sup>，
	 * 则使用所谓的"科学记数法"表示；
	 * 
	 * </ul>
	 * 
	 * <p>
	 * 如果参数为整数值，那么输出后的结果将相当于调用{@link Integer#toString()}
	 * 方法，把参数作为整数值进行输出，更加符合实际也易于表达。
	 * 
	 * @param x 要转换的 {@code double} 值。
	 * @return	参数的字符串表示形式。
	 */
	public static String toString(double x) {
		String value = Double.toString(x);
		value = value.replaceFirst("\\.0$|\\.0(?=E)", "");
		return value;
	}

	/**
	 * <p>
	 * 采用 Stein 算法对两个 {@code int} 类型的数值计算最大公约数。
	 * 
	 * <h2>算法简介</h2>
	 * 
	 * <p>
	 * Stein 算法是一种计算两个数最大公约数的算法，是针对欧几里德算法在对大整数进行运算时， 
	 * 需要试商导致增加运算时间的缺陷而提出的改进算法。
	 * 
	 * <h2>算法思想</h2>
	 * 
	 * <p>
	 * 由 J.Stein 1961年提出的 Stein 算法很好的解决了欧几里德算法中的这个缺陷，Stein 算法 
	 * 只有整数的移位和加减法，为了说明 Stein 算法的正确性，首先必须注意到以下结论：
	 * 
	 * <ol>
	 * 
	 * <li>gcd(a, a) = a，也就是一个数和其自身的公约数仍是其自身。<br>
	 * 
	 * <li>gcd(ka, kb) = k &lowast; gcd(a, b)，也就是最大公约数运算和倍乘运算可以交换。 
	 * 特殊地，当k = 2时，说明两个偶数的最大公约数必然能被2整除。<br>
	 * 
	 * <li>当 k 与 b 互为质数，gcd(ka, b) = gcd(a, b)，也就是约掉两个数中只有其中一个含有的因子
	 * 不影响最大公约数。特殊地，当k = 2时，说明计算一个偶数和一个奇数的最大公约数时，可以先将偶数除以2。
	 * </ol>
	 * 
	 * <h2>算法步骤</h2>
	 * <p>
	 * 有了上述规律就可以给出Stein 算法如下：<br>
	 * 
	 * <ol>
	 * 
	 * <li>如果A=0，B是最大公约数，算法结束；
	 * <li>如果b=0，A是最大公约数，算法结束；
	 * <li>设置A<sub>1</sub> = A，B<sub>1</sub> = B 和 C<sub>1</sub> = 1；
	 * 
	 * <li>如果A<sub>n</sub>和b<sub>n</sub>都是偶数，则A<sub>n+1</sub> = A 
	 * <sub>n</sub> / 2，B<sub>n+1</sub> = B<sub>n</sub> / 2，
	 * C<sub>n+1</sub> = C<sub>n</sub> &lowast; 2；
	 *  
	 * <br>(注意，乘二只要把整数左移一位即可，除二只要把整数右移一位即可)
	 *  
	 * <li>如果A<sub>n</sub>是偶数，B<sub>n</sub>不是偶数，则A<sub>n+1</sub> = 
	 * A<sub>n</sub> / 2，B<sub>n+1</sub> = B<sub>n</sub>，C<sub>n+1</sub>
	 * = C<sub>n</sub>；
	 * 
	 * <li>如果b<sub>n</sub>是偶数，A<sub>n</sub>不是偶数，则B<sub>n+1</sub> = B
	 * <sub>n</sub> / 2，A<sub>n+1</sub> = A<sub>n</sub>，C<sub>n+1</sub> =
	 * C<sub>n</sub>；
	 * 
	 * <br>(很显然，二不是奇数的约数)
	 * 
	 * <li>如果A<sub>n</sub>和B<sub>n</sub>都不是偶数，则A<sub>n+1</sub> = 
	 * |A<sub>n</sub> &minus; B<sub>n</sub>|，B<sub>n+1</sub> = 
	 * min(A<sub>n</sub>，B<sub>n</sub>)，C<sub>n+1</sub> =C<sub>n</sub>；
	 * 
	 * <li>n++，转到步骤四。
	 * 
	 * </ol>
	 * 
	 * <h2>算法优点</h2>
	 * 
	 * <p>
	 * 考虑欧几里德算法，最恶劣的情况是，每次迭代a = 2b &minus; 1,这样，迭代后，
	 * r = b &minus; 1。如果a小于2<sup>n</sup>，这样大约需要4n次迭代。而考虑 Stein
	 * 算法，每次迭代后，显然a<sub>n+1</sub>b<sub>n+1</sub> &le; a<sub>n</sub>b
	 * <sub>n</sub> / 2，最大迭代次数也不超过4n次。也就是说，迭代次数 几乎是相等的。但是，
	 * 需要注意的是，对于大素数，试商法将使每次迭代都更复杂，因此对于大素数 Stein 将更有优势。
	 * 
	 * <p>
	 * 此方法是对 Stein 算法的简单实现。
	 * 
	 * @param a 第一个参数
	 * @param b 第二个参数
	 * @return  两个参数的最大公约数
	 */
	public static int gcd(int a, int b) {
		// 确保两个值都为正
		a = Math.abs(a);
		b = Math.abs(b);
		
		// 如果两个值相等其中一个即为最大公约数
		if (a == b) {
			return a;
		}

		/*
		 * ============== 
		 * 我们约定： 
		 * 第一个参数为较大的数； 
		 * 第二个参数为较小的数。
		 *  ==============
		 */

		int t;
		// 使参数符合约定
		if (a < b) {
			t = a;
			a = b;
			b = t;
		}

		// 如果较小数为零则返回较大数
		if (b == 0) {
			return a;
		}

		// 较大参数是否为偶数
		boolean m = (a % 2) == 0;
		// 较小参数是否为偶数
		boolean n = (b % 2) == 0;

		// Stein 算法
		if (m && n) {
			a >>= 1;
			b >>= 1;
			t = gcd(a, b);
			t <<= 1;
			return t;
		} else if (m) {
			a >>= 1;
			return gcd(a, b);
		} else if (n) {
			b >>= 1;
			return gcd(a, b);
		} else {
			return gcd(a - b, b);
		}
	}

	/**
	 * <p>计算两个数的最小公倍数。
	 *
	 * @param a 第一个参数
	 * @param b 第二个参数
	 * @return 最大公倍数
	 */
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	/**
	 * <p>n阶行列式的计算。
	 *
	 * @param x 行列式
	 * @return 结算结果
	 */
	public static int determinant(int[][] x) {
		// 行列式阶数
		final int order = x.length;
		// 阶数不能为零
		if (order == 0)
			throw new MathIllegalArgumentException("行列式的阶数不能零");
		// 判断是否为行列式
		for (int[] ints : x)
			if (ints.length != order)
				throw new MathIllegalArgumentException("这不是一个行列式");
		// 计算n阶行列式
		return calc(x);
	}

	/**
	 * <p>计算n阶行列式
	 *
	 * @param x 行列式
	 * @return 结果
	 */
	private static int calc(int[][] x) {
		// 二阶行列式计算结果
		if (x.length == 1)
			return x[0][0];
		// 定义计算结果
		int result = 0;
		// 获取第一行的所有元素
		int[] firstRow = x[0];
		for (int i = 0; i < firstRow.length; i++) {
			// 计算元素与余子式的乘积
			int temp = firstRow[i] * calc(getMinor(i, x));
			// 根据公式合并相乘结果
			if (i % 2 == 0)
				result += temp;
			else
				result -= temp;
		}
		return result;
	}

	/**
	 * <p>获取余子式。
	 *
	 * @param c 排除的列
	 * @param x 行列式
	 * @return 余子式
	 */
	private static int[][] getMinor(int c, int[][] x) {
		// 余子式的阶数
		final int order = x.length - 1;
		// 余子式为一阶
		if (order == 0)
			return x;
		// 定义余子式
		int[][] minor = new int[order][order];
		// 排除某一列的元素，并给余子式赋值
		for (int i = 1; i < x.length; i++)
			for (int j = 0, k = 0, n = i - 1; j < x.length; j++)
				if (j != c)
					minor[n][k++] = x[i][j];
		return minor;
	}

	public static BigDecimal eval(String exp) {
		int i = exp.indexOf('+');
		Node prev = new ValNode(exp.substring(0, i));
		Node next = new ValNode(exp.substring(i + 1));
		return new AddNode(prev, next).eval();
	}
	
}
