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
 * 力
 * @author 王帅
 * @author 90
 *
 */
public enum Force implements Unit {
	/**	千克力(kgf)	*/
	KILOGRAM_FORCE("千克力(kgf)", 1.0 / 9.80665),//- -
	/**	磅力(lbf)		*/
	POUND_FORCE("磅力(lbf)", 1.0 / 4.4482216152605),
	/**	千磅力(kip)	*/
	KILO_POUND_FORCE("千磅力(kip)", 0.00022481),
	/**	达因(dyn)	*/
	DYNE("达因(dyn)", 1e-5),
	/**	克力(gf)	*/
	GRAM_FORCE("克力(gf)", 1.0 / 9.80665 * 1e3),
	/**	千牛(kN)	*/
	KILO_NEWTON("千牛(kN)", 1e-3),
	/**	磅达(?)	*/
	POUNDAL("磅达(?)", 7.23301385),
	/**	英吨力(?)	*/
	TON_FORCE("英吨力(?)", 0.00010036),
	/**	公吨力(tf)	*/
	TONNE_FORCE("公吨力(tf)", 0.00010197),
	/**	美吨力(?)*/
	US_TON_FORCE("美吨力(?)", 0.0001124),
	/**	牛(N)	*/
	NEWTON("牛(N)", 1.0);
	
	/** 单位的名称。*/
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个力单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Force(String name, double rate) {
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
	 * <p>获取力单位的国际制主单位。
	 * 
	 * @return 力的国际制主单位
	 */
	public static Force getSI() {
		return NEWTON;
	}

}
