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

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 
 * @author 	王帅
 * @since	1.0
 * @see		QuadraticEquation
 */
public final class BigQuadraticEquation extends AbstractQuadraticEquation<BigDecimal> {
	
	// The serialVersionUID of the class BigQuadraticEquation.
	private static final long serialVersionUID = -8966227252033193073L;
	
	private static MathContext defaultMathContext = MathContext.DECIMAL64;

	public BigQuadraticEquation(final BigDecimal a, 
			  					final BigDecimal b, 
			  					final BigDecimal c) {
		super(new BigDecimal[3], new BigDecimal[2], a, b, c);
	}

	static final BigDecimal TWO = new BigDecimal("2");
	static final BigDecimal FOUR = new BigDecimal("4");

	/** 
	 * {@inheritDoc}
	 * 
	 * @param a {@inheritDoc}
	 * @param b {@inheritDoc}
	 * @param c {@inheritDoc}
	 */
	@Override
	public void reset(final BigDecimal a, 
					  final BigDecimal b, 
					  final BigDecimal c) {
//		MathUtils.checkNotZero(a, LocalizedFormat.SECOND_COEFFICIENT);
		super.reset(a, b, c);
		this.delta = b.pow(2).subtract(FOUR.multiply(a).multiply(c));
	}
	
	/**
	 * 
	 * 
	 * @param mc 要设置的省略上下文。
	 */
	public static void setDefaultMathContext(final MathContext mc) {
		defaultMathContext = mc;
	}
	
	/**
	 * 
	 * @return 默认舍入值
	 */
	public MathContext getDefaultMathContext() {
		return defaultMathContext;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	void fillRoot() {
		if (hasTwoRoot()) {
			final BigDecimal sqrt = null;
			root[0] = coefficient[1].negate().add(sqrt)
					.divide(TWO.multiply(coefficient[0]), defaultMathContext);
			root[1] = coefficient[1].negate().subtract(sqrt)
					.divide(TWO.multiply(coefficient[0]), defaultMathContext);
		} else if (hasOneRoot()) {
			root[0] = coefficient[1].negate()
					.divide(TWO.multiply(coefficient[0]), defaultMathContext);
			root[1] = root[0];
		} else {
			root[0] = null;
			root[1] = null;
		}
		this.hasRoot = true;
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean hasOneRoot() {
		return delta.signum() == 0;
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean hasTwoRoot() {
		return delta.signum() == 1;
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean notHasRoot() {
		return delta.signum() == -1;
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public BigDecimal rootSum() {
		if (notHasRoot()) {
			return null;
		}
		return coefficient[1].negate()
				.divide(coefficient[0], defaultMathContext);
	}

	/** 
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public BigDecimal rootProduct() {
		if (notHasRoot()) {
			return null;
		}
		return coefficient[2].divide(coefficient[0], defaultMathContext);
	}

	@Override
	String buildEquation() {
		String s = coefficient[0]+"x²+" + coefficient[1]
				   + "x+"+coefficient[2] + "=0";
		s = s.replaceAll("\\.0(?=[^\\d])|\\+(?=-)", "");
		s = s.replaceAll("[+\\-]0x|[+\\-]0", "");
		return s;
	}
	
}
