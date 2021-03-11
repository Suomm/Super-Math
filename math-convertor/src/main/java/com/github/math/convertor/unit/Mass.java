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
 * <p>质量的有关单位。
 * 
 * @author 王帅
 */
public enum Mass implements Unit {
	
	/**	长吨(long.ton)	*/
	LONG_TON("长吨(long.ton)", 1.0e-3 / 1.01605),
	/**	短吨(sh.ton)	*/
	SHORT_TON("短吨(sh.ton)", 1.0 / 907.0),
	/**	吨(t)	*/
	METRIC_TON("吨(t)", 1e-3),
	/**	千克(kg)	*/
	KILOGRAM("千克(kg)", 1.0),
	/**	磅(ib)	*/
	POUND("磅(ib)", 1.0 / 0.454),
	/**	毫克(mg)	*/
	MILLIGRAM("毫克(mg)", 1e6),
	/**	克(g)	*/
	GRAM("克(g)", 1e3),
	/** 盎司(oz)	*/
	OUNCE("盎司(oz)", 1.0 / 0.02835),
	/**	斤（中国长度单位）	*/
	JIN("斤（中国长度单位）", 2.0),
	/**	两（中国长度单位）	*/
	LIANG("两（中国长度单位）", 20.0),
	/**	铢（中国长度单位）	*/
	ZHU("铢（中国长度单位）", 20.0 * 24.0),
	/**	钱（中国长度单位）	*/
	QIAN("钱（中国长度单位）", 200.0),
	/**	担（中国长度单位）	*/
	DAN("钱（中国长度单位）", 0.02);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个质量单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Mass(String name, double rate) {
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
	 * <p>获取质量单位的国际制主单位。
	 * 
	 * @return 质量的国际制主单位
	 */
	public static Mass getSI() {
		return KILOGRAM;
	}

}
