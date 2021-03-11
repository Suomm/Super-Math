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

import com.github.math.exception.MathArithmeticException;
import com.github.math.number.IrrationalNumber;
import com.github.math.number.fraction.Fractional;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>{@code RadicalList}通过继承{@link ArrayList}来存放根式{@code Radical}。
 * 
 * <p>由于根式加减会造成几个组成一个式子的情况，所以{@code RadicalList}就像一个大式子，
 * 其中包含着一些小的根式{@code Radical}。看下面的式子（以二次根式为例）：
 * <pre>
 * 4√2+2√3+2√2+8√6 = 6√2+2√3+8√6
 * </pre>
 * 几个单项式相加的结果是一个式子。如果一个二次根式类只能表示一个普通的根式（如：4√2），但不能表示为一个式子，
 * 那么最后的结果（6√2+2√3+8√6）该怎么办呢？所以每一个根式类中都会包含一个{@code RadicalList}对
 * 象，把普通的根式（如：4√2）存放在其中，方便进行计算操作。
 * 
 * <p>子容器{@code RadicalList}重写了父类的{@link #add(Radical)}，
 * {@link #addAll(Collection)}方法，进行去重、排序等操作。此外提供了
 * {@link #addIgnoreCheck(Radical)}方法直接添加到容器。
 * 
 * @author 	王帅
 * @see 	Radical
 * @see		AbstractRadical
 */
public final 
class RadicalCalculator extends IrrationalNumber<Radical> {

	// The serialVersionUID of the class RadicalCalculator.
	private static final long serialVersionUID = 4481196923114672704L;
	
	private final ArrayList<Radical> elements;
	
	private RadicalCalculator(Stream<Radical> stream) {
		elements = stream.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public static RadicalCalculator of(AbstractRadical... elements) {
		return null;
	}

	@Override
	public int intValueExact() throws ArithmeticException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValueExact() throws ArithmeticException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Radical abs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical pow(int e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical min(Radical val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical max(Radical val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical negate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fractional reciprocal() throws MathArithmeticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical add(Radical val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical add(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical subtract(Radical val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical subtract(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical multiply(Radical val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Radical multiply(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fractional divide(Radical val) throws MathArithmeticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fractional divide(int i) throws MathArithmeticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int signum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public int compareTo(Radical o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
