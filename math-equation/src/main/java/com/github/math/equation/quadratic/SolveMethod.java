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

/*
 * Super Math 是一款功能强大的数学软件，可以通过计算机来将生活中复杂的数学运算或问题变得异常轻松，
 * 上手容易，简单便捷。通过在键盘上来敲一些所需要的式子（或是数字），便能灵活的做出一些运算。
 * 
 * 您可以登录以下网址获取所需要的资源，包括测试文件以及说明（或者联系作者QQ:1474983351）：
 * 
 * 				http://www.mixed.com/Super-Math
 * 
 * Super Math 作为学生必备的学习神器，专为广大学生量身定做，它综合了每个学年段的所有数学知识，无需
 * 过多时间，便可得到简洁、准确的答案。是学霸和学渣的一大福音，同样也可是数学老师的教学利器。让学习变得
 * 不再艰难，解题更加轻松，考试高分不再是梦想。
 */
package com.github.math.equation.quadratic;

import java.io.Serializable;

/**
 * 
 * @author 王帅
 */
public final class SolveMethod implements Serializable {

	// The serialVersionUID of the class SolveMethod.
	private static final long serialVersionUID = 5786080174112237013L;
	
	private final int method;
	
	/**
	 * 公式法代码
	 */
	static final int FORMULA_CODE = 1;
	
	/**
	 * 因式分解法代码
	 */
	static final int FACTORIZATION_CODE = 2;
	
	/**
	 * 配方法代码
	 */
	static final int MATCHING_CODE = 3;
	
	/**
	 * 公式法
	 */
	public static final SolveMethod FORMULA_METHOD = 
			new SolveMethod(FORMULA_CODE);
	
	/**
	 * 配方法
	 */
	public static final SolveMethod MATCHING_METHOD = 
			new SolveMethod(MATCHING_CODE);
	
	/**
	 * 因式分解法
	 */
	public static final SolveMethod FACTORIZATION_METHOD = 
			new SolveMethod(FACTORIZATION_CODE);
	
	/**
	 * 
	 * @param method 解方程的方法
	 */
	private SolveMethod(final int method) {
		this.method = method;
	}
	
	/**
	 * 
	 * @return 方法值
	 */
	public int getMethod() {
		return this.method;
	}

}
