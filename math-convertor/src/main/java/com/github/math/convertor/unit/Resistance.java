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

package com.github.math.convertor.unit;

/**
 * 电阻
 * @author 王帅
 *
 */
public enum Resistance implements Unit {
	
	/**	欧姆(Ω)	*/
	OHM("欧姆(Ω)", 1.0);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个电阻单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Resistance(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getRate() {
		return this.rate;
	}
	
	/**
	 * <p>获取电阻单位的国际制主单位。
	 * 
	 * @return 电阻的国际制主单位
	 */
	public static Resistance getSI() {
		return OHM;
	}

}
