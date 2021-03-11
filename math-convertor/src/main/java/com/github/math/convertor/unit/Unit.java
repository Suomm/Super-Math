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
 * <p>换算单位
 * 
 * @author 	王帅
 * @since	2.0
 */
public interface Unit {
	
	/**
	 * <p>获得单位的名称。
	 * 
	 * @return 单位名称与符号
	 */
	String getName();
	
	/**
	 * <p>获得此单位与国际制主单位间的进率。
	 * 
	 * @return 进率
	 */
	double getRate();
	
}
