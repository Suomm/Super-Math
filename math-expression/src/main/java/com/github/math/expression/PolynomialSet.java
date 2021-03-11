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

import com.github.math.SuperMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

/**
 * 
 * 
 * 
 * @author 王帅
 */
class PolynomialSet extends ArrayList<Monomial> {

	// The serialVersionUID of the class null.
	private static final long serialVersionUID = 704784437016891818L;
	
	public PolynomialSet() {
		super(30);
	}
	
	public PolynomialSet(int size) {
		super(size);
	}
	
	/**
	 * 添加根式元素（忽略检查是否可以相加与排序）。
	 * 
	 * @param e 需要添加的元素
	 */
	public void addIgnoreCheck(Monomial e) {
		super.add(e);
	}
	
	@Override
	public boolean add(Monomial e) {
		if (e.signum == 0) {
			return false;
		}
		if (isEmpty()) {
			return super.add(e);
		}
		
		final ListIterator<Monomial> it = listIterator();
		Monomial m;
		int compare = 0;
		int index = 0;
		while (it.hasNext()) {
			m = it.next();
			
			if (m.letter.isEmpty() && e.letter.isEmpty()) {
				return add(m, e, it);
			}
			
			if (m.letter.equals(e.letter)) {
				return add(m, e, it);
			}
			
			compare = e.compareTo(m);
			
			if (compare > 0) {
				index++;
			}
			
		}
		
		if (index == size()) {
			return super.add(e);
		}
		
		super.add(index, e);
		return true;
	}
	
	public boolean add(Monomial m, Monomial e, ListIterator<Monomial> it) {
		int c = e.coefficient + m.coefficient;
		if (SuperMath.signum(c) != 0) {
			it.set(new Monomial(c, e.letter));
			return true;
		} 
		it.remove();
		return true;
	}
	
	public boolean addAll(Monomial... c) {
		for (Monomial m : c) {
			add(m);
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Monomial> c) {
		for (Monomial m : c) {
			add(m);
		}
		return true;
	}
	
	public boolean addAllIgnoreCheck(Collection<? extends Monomial> c) {
		return super.addAll(c);
	}
	
}
