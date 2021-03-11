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
 * 速度
 * @author 王帅
 *
 */
public enum Velocity implements Unit {
	
	/**	米每秒(m/s)	*/
	METER_SECOND("米每秒(m/s)", 1.0),
	/**	千米每小时(km/h)	*/
	KILOMETER_HOUR("千米每小时(km/h)", 3.6);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个速度单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Velocity(String name, double rate) {
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
	 * <p>获取速度单位的国际制主单位。
	 * 
	 * @return 速度的国际制主单位
	 */
	public static Velocity getSI() {
		return METER_SECOND;
	}

}
