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
 * <p>数据存储
 * @author 王帅
 *
 */
public enum Storage implements Unit {
	
	/**	千字节(KB)	*/
	KBYTES("千字节(KB)", 1.0 / pow(1)),
	/**	兆字节(MB)	*/
	MEGABYTES("兆字节(MB)", 1.0 / pow(2)),
	/**	千兆字节(GB)	*/
	GIGABYTE("千兆字节(GB)", 1.0 / pow(3)),
	/**	太字节(TB)	*/
	TERABYTE("太字节(TB)", 1.0 / pow(4)),
	/**	拍字节(PB)	*/
	PETABYTES("拍字节(PB)", 1.0 / pow(5)),
	/**	艾字节(EB)	*/
	EXABYTES("艾字节(EB)", 1.0 / pow(6)),
	/**	泽字节(ZB)	*/
	ZETTABYTE("泽字节(ZB)", 1.0 / pow(7)),
	/**	尧字节(YB)	*/
	YOTTABYTE("尧字节(YB)", 1.0 / pow(8)),
	/**	千亿亿亿字节(BB)	*/
	BRONTBYTE("千亿亿亿字节(BB)", 1.0 / pow(9)),
	/**	字节(B)	*/
	BYTE("字节(B)", 1.0),
	/**	比特(bit)	*/
	BINARY_SYSTEM("比特(bit)", 8.0);
	
	/** 单位的名称。 */
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个数据存储单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private Storage(String name, double rate) {
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
	 * <p>获取数据存储单位的国际制主单位。
	 * 
	 * @return 数据存储的国际制主单位
	 */
	public static Storage getSI() {
		return BYTE;
	}
	
	/**
	 * <p>计算每{@code pow}个相邻的容量单位的进率。
	 * 
	 * @param pow 与Byte的间隔
	 * @return 1024的{@code pow}次方
	 */
	private static double pow(int pow) {
		return Math.pow(1024.0, pow);
	}

}
