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
 * <p>
 * 长度单位。
 * 
 * @author 王帅
 */
public enum Length implements Unit {

	/** 光年(l.y.) */
	LIGHT_YEAR("光年(l.y.)", 1.0 / 946052840487935.88126),
	/** 秒差距(PC) */
	PARSEC("秒差距(PC)", 1.0 / (3.2615637771418798291 * 946052840487935.88126)),
	/** 天文单位(AU) */
	ASTRONOMICAL_UNIT("天文单位(AU)", 1.0 / 1.495978707e11),
	/** 千米(km) */
	KILOMETER("千米(km)", 1e-3),
	/** 兆米(Mm) */
	MEGAMETER(" 兆米(Mm)", 1e-6),
	/** 尧米(Ym)，佑米 */
	YOTTAMETERE("尧米(Ym)，佑米", 1e-24),
	/** 泽米(zm) */
	ZAMIA("泽米(zm)", 1e-21),
	/** 拍米(Pm) */
	PETAMETRE("拍米(Pm)", 1e-15),
	/** 太米(Tm) */
	TERAMETER("太米(Tm)", 1e-12),
	/** 百米(hm) */
	HUNDREDMETER(" 百米(hm)", 1e-2),
	/** 米(m) */
	METER("米(m)", 1.0),
	/** 分米(dm) */
	DECIMETER("分米(dm)", 1e1),
	/** 厘米(cm) */
	CENTIMETER("厘米(cm)", 1e2),
	/** 毫米(mm) */
	MILLIMETER(" 毫米(mm)", 1e3),
	/** 丝米(dmm) */
	DECIMILLIMETER(" 丝米(dmm)", 1e5),
	/** 忽米(cmm)，丝 */
	CENTIMILLIMETER("忽米(cmm)，丝", 1e6),
	/** 英里(mi) */
	MILE("英里(mi)", 1.0 / 1609),
	/** 英尺(ft) */
	FOOT(" 英尺(ft)", 1.0 / 0.3048),
	/** 码(yd) */
	YARD("码(yd)", 1.0 / 0.9144),
	/** 英寸(in) */
	INCH(" 英寸(in)", 1.0 / 0.0254),
	/** 纳米(nm) */
	NANOMETER(" 纳米(nm)", 1e9),
	/** 纳米(nm) */
	MICRON(" 纳米(nm)", 1e6),
	/** 埃米(Å) */
	ANGSTROM(" 埃米(Å)", 10e-10),
	/** 英寻(fm) */
	FATHOM(" 英寻(fm)", 1.0 / 1.829),
	/** 海里(n mile) */
	NAUTICAL_MILE("海里(n mile)", 1.0 / 185.2),
	/** 链(chain) */
	CHAIN("链(chain)", 1.0 / 20.1168),
	/** 密尔(mil) */
	MIL("密尔(mil)", 1.0 / 0.0000254),
	/** 皮米(pm) */
	PICOMETER(" 皮米(pm)", 1e12),
	/** 皆米(ym)，攸米，玄米 */
	YOCTOMETER("皆米(ym)，攸米，玄米", 1e24),
	/** 京米(gm) */
	GIGAMETER("京米(gm)", 1e9),
	/** 阿米(am) */
	ATTOMETER("阿米(am)", 1e18),
	/** 仄米(zepto) */
	ZEPTO("仄米(zepto)", 1e21),
	/** 飞米(fm) */
	FEMTOMETER("飞米(fm)", 1e15),
	/** 普朗克长度(PI) */
	THE_PLANCK_LENGTH("普朗克长度(PI)", 1e35),
	/** 杆(rad) */
	RAD("杆(rad)", 0.25 / (1.0 / 20.1168)),
	/** 化朗(fur)，弗隆，浪 */
	FURLONG("化朗(fur)，弗隆，浪", 1.0 / 20116800),
	/** 里（中国长度单位） */
	LI("里（中国长度单位）", 1e-2 * 2.0),
	/** 丈（中国长度单位） */
	ZHANG("丈（中国长度单位）", 3e-1),
	/** 尺（中国长度单位） */
	CHI("尺（中国长度单位）", 3.0),
	/** 寸（中国长度单位） */
	CUN("寸（中国长度单位）", 3e1),
	/** 分（中国长度单位） */
	FEN("分（中国长度单位）", 3e2),
	/** 厘（中国长度单位） */
	Li(" 厘（中国长度单位）", 3e3),
	/** 毫（中国长度单位） */
	HAO("毫（中国长度单位）", 3e4);

	/** 单位的名称。 */
	private final String name;

	/** 与国际制主单位间进率。 */
	private final double rate;

	/**
	 * <p>
	 * 构造一个长度单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate
	 *            与国际制主单位间进率
	 */
	private Length(String name, double rate) {
		this.name = name;
		this.rate = rate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getRate() {
		return this.rate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * <p>
	 * 获取长度单位的国际制主单位。
	 * 
	 * @return 长度的国际制主单位
	 */
	public static Length getSI() {
		return METER;
	}

}
