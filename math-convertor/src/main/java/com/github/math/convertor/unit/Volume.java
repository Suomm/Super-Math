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
 * 体积单位
 * @author 王帅
 *
 */
public enum Volume implements Unit {
	
	/**	升(L)	*/
	LITRE("升(L)", 1e3),
	/**	立方米(m³)	*/
	CUBIC_METER("立方米(m³)", pow(Length.METER)),
	/**	立方分米(dm³)	*/
	CUBIC_DECIMETER("立方分米(dm³)", pow(Length.DECIMETER)),
	/**	立方厘米(cm³)	*/
	CUBIC_CENTIMETER("立方厘米(cm³)", pow(Length.CENTIMETER)),
	/**	立方毫米(mm³)	*/
	CUBIC_MILLIMETER("立方毫米(mm³)", pow(Length.MILLIMETER)),
	/**	立方英尺(ft³)	*/
	CUBIC_FOOT("立方英尺(ft³)", pow(Length.FOOT)),
	/**	立方英寸(in³)	*/
	CUBIC_INCH("立方英寸(in³)", pow(Length.INCH));
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个体积单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Volume(String name, double rate) {
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
	 * <p>获取体积单位的国际制主单位。
	 * 
	 * @return 体积的国际制主单位
	 */
	public static Volume getSI() {
		return CUBIC_METER;
	}
	
	private static double pow(final Length length) {
		return Math.pow(length.getRate(), 3.0);
	}

}
