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
 * 面积单位
 * @author 王帅
 *
 */
public enum Area implements Unit {
	
	/**	平方千米(km²)	*/
	SQUARE_KILOMETER("平方千米(km²)", pow(Length.KILOMETER)),
	/**	公顷(ha, hm²)	*/
	SQUARE_HECTOMETER("公顷(ha, hm²)", pow(Length.HUNDREDMETER)),
	/**	平方分米(dm²)	*/
	SQUARE_DECIMETER("平方分米(dm²)", pow(Length.DECIMETER)),
	/**	平方厘米(cm²)	*/
	SQUARE_CENTIMETER("平方厘米(cm²)", pow(Length.CENTIMETER)),
	/**	平方毫米(mm²)	*/
	SQUARE_MILLIMETER("平方毫米(mm²)", pow(Length.MILLIMETER)),
	/**	平方米(m²)	*/
	SQUARE_METER("平方米(m²)", pow(Length.METER)),
	/**	平方英里(mi²)	*/
	SQUARE_MILE("平方英里(mi²)", pow(Length.MILE)),
	/**	平方英尺(ft²)	*/
	SQUARE_FOOT("平方英尺(ft²)", pow(Length.FOOT)),
	/**	平方英寸(in²)	*/
	SQUARE_INCH("平方英寸(in²)", pow(Length.INCH)),
	/**	平方码(yd²)	*/
	SQUARE_YARD("平方码(yd²)", pow(Length.YARD)),
	/**	 平方杆(rad²)		*/
	SQUARE_RAD("平方杆(rad²)", pow(Length.RAD)),
	/**	公亩(are)	*/
	ARE("公亩(are)", 1e-2),
	/**	英亩(acre)	*/
	ACRE("英亩(acre)", 1.0 / 4046.864798),
	/**	亩（中国长度单位）	*/
	MU("亩（中国长度单位）", 15e-4),
	/**	平方里（中国长度单位）	*/
	SQUARE_LILI("平方里（中国长度单位）", pow(Length.LI)),
	/**	平方丈（中国长度单位）	*/
	SQUARE_ZHANG("平方丈（中国长度单位）", pow(Length.ZHANG)),
	/**	平方尺（中国长度单位）	*/
	SQUARE_CHI("平方尺（中国长度单位）", pow(Length.CHI)),
	/**	平方寸（中国长度单位）	*/
	SQUARE_CUN("平方寸（中国长度单位）", pow(Length.CUN)),
	/**	平方分（中国长度单位）	*/
	SQUARE_FEN("平方分（中国长度单位）", pow(Length.FEN)),
	/**	平方厘（中国长度单位）	*/
	SQUARE_LI("平方厘（中国长度单位）", pow(Length.Li)),
	/**	平方毫（中国长度单位）	*/
	SQUARE_HAO("平方毫（中国长度单位）", pow(Length.HAO));
	
	/** 单位的名称。*/
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个面积单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Area(String name, double rate) {
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
	 * <p>获取面积单位的国际制主单位。
	 * 
	 * @return 面积的国际制主单位
	 */
	public static Area getSI() {
		return SQUARE_METER;
	}
	
	/**
	 * 平方
	 * 
	 * @param length 单位
	 * @return	平方
	 */
	private static double pow(final Length length) {
		return length.getRate() * length.getRate();
	}

}
