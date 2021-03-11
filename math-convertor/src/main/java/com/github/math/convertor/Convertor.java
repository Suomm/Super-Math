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

package com.github.math.convertor;

import com.github.math.convertor.unit.Unit;

import java.io.Serializable;

/**
 * <p>简单的单位转换器。
 * 
 * @author 		王帅
 * @param <U> 	单位类型
 */
public final class Convertor<U extends Unit> implements Serializable {

	// The serialVersionUID of the class Convertor.
	private static final long serialVersionUID = 6451129311313465069L;
	
	/**
	 * 原始单位
	 */
	private U from;
	
	/**
	 * 目标单位
	 */
	private U to;
	
	/**
	 * 
	 * @param from 原始单位
	 * @param to   目标单位
	 */
	public Convertor(U from, U to) {
		this.from = from;
		this.to   = to;
	}
	
	/**
	 * 
	 */
	public Convertor() {
	}
	
	public U getFrom() {
		return from;
	}

	public void setFrom(U from) {
		this.from = from;
	}

	public U getTo() {
		return to;
	}

	public void setTo(U to) {
		this.to = to;
	}

	/**
	 * 转换
	 */
	public void reverse() {
		U temp = this.from;
		this.from = this.to;
		this.to = temp;
	}

	/**
	 * 
	 * @return 进率
	 */
	public double convert() {
		return to.getRate() / from.getRate();
	}
	
	/**
	 * 
	 * @param quantity 数量
	 * @return 结果
	 */
	public double convert(double quantity) {
		return convert() * quantity;
	}
	
	public static <U extends Unit> double convert(U from, U to) {
		return to.getRate() / from.getRate();
	}
	
	public static <U extends Unit> double convert(U from, U to, double quantity) {
		return convert(from, to) * quantity;
	}

}
