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
 * 密度
 * @author 王帅
 * @author v
 */
public enum Density implements Unit {
	
	/**	千克每立方米(kg/m³)	*/
	KILOGRAM_CUBIC_METER("千克每立方米(kg/m³)", 1.0),
	/**	千克每立方分米(kg/dm³)	*/
	KILOGRAM_CUBIC_DECIMETER("千克每立方分米(kg/dm³)", 1.0 / 0.001),
	/**	千克每立方厘米(kg/cm³)	*/
	KILOMETER_CUBIC_CENTIMETER("千克每立方厘米(kg/cm³)", 1e6),
	/**	克/立方分米(g/dm³)	*/
	GRAM_CUBIC_DECIMETER("克/立方分米(g/dm³)", 1.0),
	/**	克/立方米(g/m³)	*/
	GRAM_CUBIC_METER("克/立方米(g/m³)", 1.0 / 1000.0),
	/**	克每立方厘米(g/cm³)	*/
	GRAM_CUBIC_CENTIMETER("克每立方厘米(g/cm³)", 1.0 / 0.001);
	
	/** 单位的名称。*/
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个密度单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Density(String name, double rate) {
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
	 * <p>获取密度单位的国际制主单位。
	 * 
	 * @return 密度的国际制主单位
	 */
	public static Density getSI() {
		return KILOGRAM_CUBIC_METER;
	}

}
