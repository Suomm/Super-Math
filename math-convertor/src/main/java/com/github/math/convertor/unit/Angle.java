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
 * 
 * @author 王帅
 *
 */
public enum Angle implements Unit {
	
	/**	角度(degree)	*/
	DEGREE("角度(degree)", 1.0),
	/**	弧度(rad)	*/
	RADIAN("弧度(rad)", Math.PI / (180.0 / 1.0)),
	/**	梯度(grad)	*/
	GRADIENT("梯度(grad)", 1e2 / 90);
	
	/** 单位的名称。*/
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个质量单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Angle(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public double getRate() {
		return this.rate;
	}
	
}
