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
 * 压强
 * @author 王帅
 *
 */
public enum Pressure implements Unit {
	
	/**	巴(bar)	*/
	BAR("巴(bar)", 1e-5),
	/**	标准大气压(atm)	*/
	ATMOSPHERE("标准大气压(atm)", 1.0 / 101325.0),
	/**	毫米汞柱(mmHg)	*/
	MILLIMETER_OF_MERCURY("毫米汞柱(mmHg)", 152.0 / 20265.0),
	/**	毫米水柱(mmH₂O)*/
	MILLIMETER_OF_WATER("毫米水柱(mmH₂O)", 1.0 / (10.34 / 101325.0)),
	/**	托(Torr)	*/
	TORR("托(Torr)", 1.0 / (20265.0 / 152.0)),
	/**	毫巴(mbar)	*/
	MILLIBAR("毫巴(mbar)", 1e-2),
	/**	帕斯卡(Pa)	*/
	PASCAL("帕斯卡(Pa)", 1.0),
	/**	千帕(kPa)	*/
	KILOPASCAL("千帕(kPa)", 1e-3),
	/**	兆帕(MPa)	*/
	MEGAPASCAL("兆帕(MPa)", 1e-6),
	/**	百帕(hPa)	*/
	HECTOPASCAL("百帕(hPa)", 1e-2);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个压强单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Pressure(String name, double rate) {
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
	 * <p>获取压强单位的国际制主单位。
	 * 
	 * @return 压强的国际制主单位
	 */
	public static Pressure getSI() {
		return PASCAL;
	}

}
