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

package com.github.math.expression;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author 王帅
 *
 */
public final class Polynomial 
	extends AbstractSet<Monomial> 
	implements Expression<Polynomial>, Serializable {
	
	// The serialVersionUID of the class Polynomial.
	private static final long serialVersionUID = -644623544210781320L;
	
	private final PolynomialSet elements;
	
	/***/
	public static final Polynomial ZERO = new Polynomial(Monomial.ZERO);
	/***/
	public static final Polynomial ONE  = new Polynomial(Monomial.ONE);
	
	/**
	 * 
	 * @param monomials 单项式
	 */
	public Polynomial(Monomial... monomials) {
		this();
		this.elements.addAll(monomials);
	}
	
	/**
	 * 
	 * @param c g
	 */
	public Polynomial(Collection<Monomial> c) {
		this();
		this.elements.addAll(c);
	}
	
	private Polynomial(PolynomialSet elements) {
		this.elements = elements;
	}
	
	public Polynomial() {
		this.elements = new PolynomialSet();
	}
	
	public int size() {
		return this.elements.size();
	}
	
	public void clear() {
		this.elements.clear();
	}
	
	@Override
	public Polynomial negate() {
		PolynomialSet set = new PolynomialSet(size());
		for (Monomial m : elements) {
			set.addIgnoreCheck(m.negate());
		}
		return new Polynomial(set);
	}

	@Override
	public boolean add(Monomial e) {
		return this.elements.add(e);
	}
	
	public boolean add(Monomial... element) {
		return this.elements.addAll(element);
	}

	@Override
	public Polynomial plus(Polynomial val) {
		PolynomialSet set = new PolynomialSet(size() + val.size());
		set.addAllIgnoreCheck(elements);
		set.addAll(val.elements);
		return new Polynomial(set);
	}

	@Override
	public Iterator<Monomial> iterator() {
		return this.elements.iterator();
	}

	/**
	 * 
	 * @return 单项式
	 */
	public Monomial commonFactor() {
		final int n = size() - 1;
		int i = 0;
		Monomial m = elements.get(i);
		
		if (n == 0) {
			return m;
		}
		
		do {
			m = m.intersection(elements.get(++i));
		} while (i < n);
		
		return m;
	}
	
	
	@Override
	public int hashCode() {
		return 31 + ((elements == null) ? 0 : elements.hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Polynomial)) {
			return false;
		}
		final Polynomial other = (Polynomial) obj;
		if (!elements.equals(other.elements)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		
		if (isEmpty()) {
			return "0";
		}
		
		final StringBuilder buf = new StringBuilder();
		
		Iterator<Monomial> it = iterator();
		
		while (it.hasNext()) {
			Monomial m = it.next();
			if (m.signum == 1) {
				buf.append('+').append(m.toString());
				continue;
			}
			buf.append(m.toString());
		}
		
		if (buf.charAt(0) == '+') {
			buf.deleteCharAt(0);
		}
		
		return buf.toString();
	}
	
	@Override
	public Polynomial subtract(Polynomial val) {
		return plus(val.negate());
	}

	@Override
	public Polynomial multiply(Polynomial val) {
		PolynomialSet set = new PolynomialSet(size() * val.size());
		
		Iterator<Monomial> i0 = iterator();
		Iterator<Monomial> i1 = null;
		
		Monomial m0 = null;
		Monomial m1 = null;
		
		while (i0.hasNext()) {
			m0 = i0.next();
			i1 = val.iterator();
			while (i1.hasNext()) {
				m1 = i1.next();
				set.add(m0.multiply(m1));
			}
		}
		
		return new Polynomial(set);
	}
	
	/**
	 * 
	 * @param m n
	 * @return nn
	 */
	public Polynomial multiply(Monomial m) {
		PolynomialSet set = new PolynomialSet(size());
		Iterator<Monomial> it = iterator();
		while (it.hasNext()) {
			set.add(it.next().multiply(m));
		}
		return new Polynomial(set);
	}
	
	@Override
	public Fraction divide(Polynomial val) {
		return null;
	}
	
	@Override
	public Fraction reciprocal() {
		// TODO 自动生成的方法存根
		return null;
	}

}
