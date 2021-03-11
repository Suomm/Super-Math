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

package com.github.math.geometry;

import com.github.math.SuperMath;
import com.github.math.exception.MathIllegalArgumentException;
import com.github.math.utils.MathUtils;

import java.io.Serializable;

/**
 * 
 * @author 王帅
 */
public final class Angle implements Serializable {
	
	// The serialVersionUID of the class Angle.
	private static final long serialVersionUID = 675148271156452089L;
	private final double degree;
	private final double minute;
	private final double second;
	private final double value;
	
	private static final String DEG = "度";
	private static final String MIN = "分";
	private static final String SEC = "秒";
	
	/** 直角 */
	public static final Angle RIGHT_ANGLE = new Angle(90.0);

	/** 周角 */
	public static final Angle ROUND_ANGLE = new Angle(360.0);

	/** 平角 */
	public static final Angle STRAIGHT_ANGLE = new Angle(180.0);
	
	/** 30°角 */
	public static final Angle THIRTY_ANGLE = new Angle(30.0);
	
	/** 45°角 */
	public static final Angle FORTY_FIFTH_ANGLE = new Angle(45.0);
	
	/** 60°角 */
	public static final Angle SIXTY_ANGLE = new Angle(60.0);
	
	/** {@code double} 类型的浮点数 {@code 60.0} */
	public static final double SIXTY = 60.0;
	
	/**
	 * 
	 * @param degree 度
	 */
	public Angle(final double degree) {
		checkAngle(degree, DEG);
		this.degree = degree;
		this.minute = 0.0;
		this.second = 0.0;
		this.value = this.degree;
	}
	
	/**
	 * 
	 * @param degree 度
	 * @param minute 分
	 */
	public Angle(final double degree, final double minute) {
		checkAngle(degree, DEG);
		checkAngle(minute, MIN);
		final double realMinute = minute % SIXTY;
		final int up01 = (int) (minute / SIXTY);
		final double realDegree = (degree + up01) % SIXTY;
		this.degree = realDegree;
		this.minute = realMinute;
		this.second = 0.0;
		this.value = this.degree + (this.minute / SIXTY);
	}



	/**
	 * 
	 * @param degree 度
	 * @param minute 分
	 * @param second 秒
	 */
	public Angle(final double degree, final double minute, final double second) {
		checkAngle(degree, DEG);
		checkAngle(minute, MIN);
		checkAngle(second, SEC);
		
		final double realSecond = second % SIXTY;
		final int up01 = (int) (second / SIXTY);
		final double realMinute = (minute + up01) % SIXTY;
		final int up02 = (int) ((minute + up01) / SIXTY);
		final double realDegree = degree + up02;
		
		this.degree = realDegree;
		this.minute = realMinute;
		this.second = realSecond;
		this.value = this.degree + ((this.minute + (this.second / SIXTY)) / SIXTY);
	}
	
	private void checkAngle(final double val, final String type) {
		MathUtils.checkDouble(val, type);
		if (SuperMath.signum(val) == -1) {
			throw new MathIllegalArgumentException("LocalizedFormats.NEGATIVE");
		}
	}
	
	/**
	 * 
	 * @return g
	 */
	public String toString() {
		return null;
	}

	/**
	 * 补角
	 * @return v
	 */
	public Angle getCompiementaryAngle() {
		return STRAIGHT_ANGLE.subtract(this);
	}

	/**
	 * 余角
	 * @return v
	 */
	public Angle getSupplementaryAngle() {
		return RIGHT_ANGLE.subtract(this);
	}
	
	public double getValue() {
		return this.value;
	}
	
	/**
	 * 
	 * @param a 个吧
	 * @return 不怪你给你
	 */
	public Angle add(final Angle a) {
		return new Angle(degree + a.degree, 
						 minute + a.minute, 
						 second + a.second);
	}

	/**
	 * 
	 * @param a 各个部门
	 * @return 功能接触到
	 */
	public Angle subtract(final Angle a) {
		return new Angle(degree - a.degree, 
						 minute - a.minute, 
						 second - a.second);
	}

}
