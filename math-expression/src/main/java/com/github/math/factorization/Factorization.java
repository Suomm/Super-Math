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

package com.github.math.factorization;

import com.github.math.expression.Monomial;
import com.github.math.expression.Polynomial;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 
 * @author 王帅
 *
 */
public final class Factorization implements Serializable {
	
	// The serialVersionUID of the class Factorization.
	private static final long serialVersionUID = 7515127036184706484L;
	
	private final Monomial common;
	private final Polynomial factor;
	
	/**
	 * 
	 * @param factor f
	 */
	public Factorization(Polynomial factor) {
		this.common = factor.commonFactor();
		this.factor = new Polynomial();
		Iterator<Monomial> it = factor.iterator();
		while (it.hasNext()) {
			Monomial m = it.next();
			this.factor.add(m.perfDivide(common));
		}
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + ((common == null) ? 0 : common.hashCode());
		result = 31 * result + ((factor == null) ? 0 : factor.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) 
			return true;
		if (obj == null) 
			return false;
		if (!(obj instanceof Factorization)) 
			return false;
		final Factorization other = (Factorization) obj;
		if (!common.equals(other.common)) 
			return false;
		if (!factor.equals(other.factor)) 
			return false;
		return true;
	}
	
	public Monomial getCommonFactor() {
		return this.common;
	}
	
	public Polynomial getFactor() {
		return this.factor;
	}

	@Override
	public String toString() {
		return common + "(" + factor + ")";
	}

}
