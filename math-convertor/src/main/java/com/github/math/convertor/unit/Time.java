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
 * 时间单位
 * @author 王帅
 *
 */
public enum Time implements Unit {
	
	/**	年(y)	*/
	YEAR("年(y)", 1.0 / (8.64e4 * 365)),
	/**	月(m)	*/
	MONTH("月(m)", 1.0 / (8.64e4 * 30)),
	/**	星期(w)	*/
	WEEK("星期(w)", 1.0 / 6.048e5),
	/**	天(d)	*/
	DAY("天(d)", 1.0 / 8.64e4),
	/**	小时(h)	*/
	HUOR("小时(h)", 1.0 / 3600.0),
	/**	分钟(min)	*/
	MINUTE("分钟(min)", 1.0 / 60.0),
	/**	秒(s)	*/
	SECOND("秒(s)", 1.0),
	/**	毫秒(ms)	*/
	MILLISECOND("毫秒(ms)", 1e3),
	/**	微秒(μs)	*/
	MICROSECOND("微秒(μs)", 1e6),
	/**	纳秒(ns)	*/
	NANOSECOND("纳秒(ns)", 1e9);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个时间单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Time(String name, double rate) {
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
	public static Time getSI() {
		return SECOND;
	}
	
}
