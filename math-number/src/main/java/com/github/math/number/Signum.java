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

package com.github.math.number;

/**
 * 测试数的正负号函数。
 * 
 * @author 王帅
 */
public interface Signum {
	
	/**
	 * 返回此类所表示数的正负号函数。
	 * 
	 * @return 当这个数的值为负数、零或正数时，返回 -1、0 或 1。
	 */
	int signum();

}
