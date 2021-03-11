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

import com.github.math.number.Signum;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 * <p>
 * {@code Monomial}类提供单项式的相关操作。
 * <strong>数或字母的积，像这样式子叫做单项式（monomial）</strong>。
 * 
 * @author 	王帅
 * @see		Expression
 * @see		Polynomial
 */
public final class Monomial implements 	Signum, 
										Expression<Monomial>, 
										Comparable<Monomial>,
		Serializable {
	
	// The serialVersionUID of the class Monomial.
	private static final long serialVersionUID = 5062871850762579006L;
	
	/**
	 * 单项式的系数（{@code int}类型）。
	 */
	final int coefficient;
	
	/**
	 * 单项式的次数（degree of a monomial）。
	 */
	private final int degree;

	/**
	 * 单项式的符号函数（也可提说是{@link #coefficient}的符号函数）。
	 */
	final int signum;
	
	/**
	 * 单项式的字母以及字母的指数。
	 */
	final TreeMap<Character, Integer> letter;
	
	/**
	 * {@code Monomial}的字符串形式。
	 */
	private final String value;

	/**
	 * 全局变量，由于遍历{@link #letter}的内容。
	 */
	private Iterator<Entry<Character, Integer>> into;

	/**
	 * {@code Monomial}的常量0。
	 */
	public static final Monomial ZERO = new Monomial(0);
	
	/**
	 * {@code Monomial}的常量1。
	 */
	public static final Monomial ONE  = new Monomial(1);
	
	private static final HashMap<Character, Integer> EMPTY_MAP = new HashMap<>();
	
	/**
	 * 
	 * @param coefficient 单项式系数
	 * @param letter 	      字母
	 */
	public Monomial(int coefficient, Map<Character, Integer> letter) {
		this.coefficient = coefficient;
		this.signum 	 = Integer.signum(coefficient);
		this.letter 	 = new TreeMap<>();
		int deg = 0;
		// 过滤
		this.value  = initString();
		this.degree = deg;
	}
	
	public Monomial(Map<Character, Integer> letter) {
		this(1, letter);
	}
	
	/**
	 * @param coefficient xishu1
	 */
	public Monomial(int coefficient) {
		this(coefficient, EMPTY_MAP);
	}
	
	Monomial(int coefficient, TreeMap<Character, Integer> letter) {
		this.coefficient = coefficient;
		this.letter		 = letter;
		this.into		 = letter.entrySet().iterator();
		int deg = 0;
		while (into.hasNext()) {
			deg += into.next().getValue().intValue();
		}
		this.degree		 = deg;
		this.signum 	 = Integer.signum(coefficient);
		this.value		 = initString();
	}
	
	/**
	 * 
	 * @return letter
	 */
	public Iterator<Entry<Character, Integer>> iterator() {
		initInto();
		return this.into;
	}

	@Override
	public int signum() {
		return signum;
	}
	
	/**
	 * 
	 * 
	 * @return 单项式的次数
	 */
	public int degree() {
		return degree;
	}

	@Override
	public Monomial negate() {
		return new Monomial(-coefficient, letter);
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	/**
	 * @return coefficient
	 */
	public int coefficient() {
		return coefficient;
	}
	
	/**
	 * 
	 * @param m b
	 * @return b
	 */
	public Monomial intersection(Monomial m) {
		initInto(m);
		HashMap<Character, Integer> value = new HashMap<>();
		
		Entry<Character, Integer> e;
		Entry<Character, Integer> n;
		
		try {
			while (into.hasNext()) {
				e = into.next();
				n = m.into.next();
				if (e.getKey().equals(n.getKey())) {
					int min = Math.min(e.getValue(), n.getValue());
					value.put(e.getKey(), min);
				}
			}
		} catch (NoSuchElementException nse) {
			// do nothing here
		}
		
		int gcd = 0;
		return new Monomial(gcd, value); 
	}
	
	private String initString() {
		if (signum == 0) {
			return "0";
		}
		
		String s = Integer.toString(coefficient);
		
		if (!letter.isEmpty()) {
			
			final StringBuilder bur = new StringBuilder();
			
			switch (s) {
			case "1":
				break;
			case "-1":
				bur.append('-');
				break;
			default:
				bur.append(s);
				break;
			}
			
			initInto();
			Entry<Character, Integer> e = null;
			while (into.hasNext()) {
				e = into.next();
				bur.append(e.getKey().charValue());
				if (e.getValue().intValue() != 1) {
					bur.append('^').append(e.getValue().intValue());
				}
			}
			return bur.toString();
		}
		return s;
	}

	/**
	 * 初始化两个{@code Monomial}类的迭代器（{@link #into}）。
	 *  
	 * @param m 另一个{@code Monomial}类
	 */
	private void initInto(Monomial m) {
		initInto();
		m.initInto();
	}

	/**
	 * 初始化本类的迭代器（{@link #into}）。
	 */
	private void initInto() {
		this.into = this.letter.entrySet().iterator();
	}

	@Override
	public int hashCode() {
		int result = 1;
		final long temp = Double.doubleToLongBits(coefficient);
		result = 31 * result  +  (int) (temp ^ (temp >>> 32));
		result = 31 * result  +  ((letter == null) ? 0 : letter.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		if (obj == null) 
			return false;
		if (!(obj instanceof Monomial)) 
			return false;
		final Monomial other = (Monomial) obj;
		if (coefficient != other.coefficient) 
			return false;
		if (!letter.equals(other.letter)) 
			return false;
		return true;
	}

	/**
	 * <p>
	 * 按<b>降幂</b>的顺序为两个单项式（{@code Monomial}）排序。具体是按照两个单项式的第一个
	 * 字母的指数进行降序排列，在比较之前会有以下几种待处理的情况：
	 * <br><ul>
	 * <li>如果两个单项式都不含有字母，直接按系数的自然顺序排序；
	 * <li>如果两个单项式中一个含有字母，而另一个不含有字母，那么含有字母的单项式会在前面。
	 * <li>如果两个单项式的第一个字母不相同，则按照两个单项式第一个字母的自然顺数进行排序；
	 * </ul>
	 * 特殊情况判断完成后，就会比较第一个字母的指数，如果第一个字母的指数相同，则会比较第二个字母的指数，
	 * 以此类推。如果到最后时，比较的结果显示两个单项式的字母及其指数全部相同时，则会比较两个单项式的系数。
	 * 
	 * <p>
	 * 下面是一组比较排序后的单项式：
	 * <blockquote><pre>
	 * 	a^9,
	 *	7a^9b^6c^4,
	 *	6a^6do^2,
	 *	9a^6do^2,
	 *	5a^2b^3,
	 *	2a^2c^5e^8,
	 *	4b^6p^3q^9,
	 *	c^6,
	 *	3,
	 *	8,
	 *	1.
	 * </pre></blockquote>
	 * 
	 * <p>
	 * 总之，单项式的排序符合降序的顺序。这也是为了方便在多项式中整理每个单项式而实现
	 * {@code Comparable}接口，使其可以进行<i>自然排序</i>。
	 * 
	 * @param o	与本类作比较的对象
	 * @return	正数：当前对象在比较对象的前面；
	 * 			负数：当前对象在比较对象的后；
	 * 			零：当前对象与比较对象相等
	 */
	@Override
	public int compareTo(Monomial o) {
		
		if (this == o) {
			return 0;
		}
		
		if (o == null) {
			return -1;
		}
		
		boolean thisEmpty = this.letter.isEmpty();
		boolean thatEmpty = o.letter.isEmpty();
		
		if (thisEmpty && thatEmpty) {
			return Integer.compare(this.coefficient, o.coefficient);
		}
		
		int thisSize = this.letter.size();
		int thatSize = o.letter.size();
		
		if (thisEmpty | thatEmpty) {
			return -(thisSize + thatSize);
		}
		
		initInto(o);
		return sort(this.into, o.into, o.coefficient);
	}

	/**
	 * 遍历比较两个对象。
	 * 
	 * @param big	本类对象的迭代器
	 * @param small	比较类对象的迭代器
	 * @param c		比较类对象的系数
	 * @return		比较后的值
	 */
	private int sort(Iterator<Entry<Character, Integer>> big,
					 Iterator<Entry<Character, Integer>> small,
					 int c) {
		
		Entry<Character, Integer> first = null;
		Entry<Character, Integer> last  = null;
		
		int compare = 0;
		
		while (big.hasNext() && small.hasNext()) {
			
			last  = small.next();
			first = big.next();
			
			// 字母相等，比较字母的指数
			if (first.getKey().equals(last.getKey())) {
				
				compare = first.getValue().compareTo(last.getValue());
				if (compare != 0) {
					return -compare;
				}
				// 指数也相等，继续比较
				continue;
			}
			
			break;
			
		}
		
		compare = first.getKey().compareTo(last.getKey());
		
		if (compare != 0) {
			return compare;
		}
		
		return Integer.compare(this.coefficient, c);
		
	}

	@Override
	public Polynomial plus(Monomial val) {
		return new Polynomial(val);
	}

	@Override
	public Polynomial subtract(Monomial val) {
		return new Polynomial(val.negate());
	}
	
	@Override
	public Monomial multiply(Monomial val) {
		
		TreeMap<Character, Integer> map;
		int mul = coefficient * val.coefficient;
		
		if (letter.isEmpty() | val.letter.isEmpty()) {
			map = new TreeMap<>(letter);
			map.putAll(val.letter);
			return new Monomial(mul, map);
		}
		
		initInto(val);
		if (letter.size() > val.letter.size()) {
			map = multiplyUtil(into, val.into);
		} else {
			map = multiplyUtil(val.into, into);
		}
		return new Monomial(mul, map);
	}
	
	private TreeMap<Character,Integer> multiplyUtil(
			Iterator<Entry<Character,Integer>> big,
			Iterator<Entry<Character, Integer>> small) {
		
		final TreeMap<Character, Integer> map = new TreeMap<>();
		
		Entry<Character, Integer> e = null;
		Entry<Character, Integer> n = null;
		
		Top: while (big.hasNext()) {
			 	e = big.next();
				while (small.hasNext()) {
					n = small.next();
					if (e.getKey().equals(n.getKey())) {
						map.put(e.getKey(), e.getValue() + n.getValue());
					} else {
						map.put(e.getKey(), e.getValue());
						map.put(n.getKey(), n.getValue());
					}
					continue Top;
				}
				map.put(e.getKey(), e.getValue());
			}
		
		return map;
	}
	
	/**
	 * 
	 * @param m b
	 * @return b
	 */
	public Monomial perfDivide(Monomial m) {
		HashMap<Character, Integer> value = new HashMap<>();
		initInto(m);
		Entry<Character, Integer> e;
		Entry<Character, Integer> n;
		
		while (m.into.hasNext()) {
			e = into.next();
			n = m.into.next();
			if (e.getKey().equals(n.getKey())) {
				value.put(e.getKey(), e.getValue() - n.getValue());
			}
		}
		
		while (into.hasNext()) {
			e = into.next();
			value.put(e.getKey(), e.getValue());
		}
		return new Monomial(coefficient / m.coefficient, value); 
	}

	@Override
	public Fraction divide(Monomial val) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	@Override
	public Fraction reciprocal() {
		// TODO 自动生成的方法存根
		return null;
	}
	
	class Letter implements Comparable<Letter> {
		
		Character key;
		int value;
		
		public Letter(Character key, int value) {
			this.key   = key;
			this.value = value;
		}

		@Override
		public int compareTo(Letter o) {
			return this.key - o.key;
		}
	}
	
}
