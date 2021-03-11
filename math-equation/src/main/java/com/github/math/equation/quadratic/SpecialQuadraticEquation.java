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

import com.github.math.exception.MathZeroException;
import com.github.math.number.Arithmetic;
import com.github.math.number.Signum;

/**
 * 
 * 
 * @author 		王帅
 * @since		2.0
 * @version		1.0
 * @param <T> 	lie
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class SpecialQuadraticEquation<T extends Arithmetic> 
	extends AbstractQuadraticEquation<T> {
	
	// The serialVersionUID of the class SpecialQuadraticEquation.
	private static final long serialVersionUID = -4497076330816686706L;

	public SpecialQuadraticEquation(final T a,
									final T b,
									final T c) {
		super((T[]) new Arithmetic[3], (T[]) new Arithmetic[2], a, b, c);
	}
	
	@Override
	public void reset(final T a, 
					  final T b, 
					  final T c) {
		if (((Signum) a).signum() == 0) {
			throw new MathZeroException(null);
		}
		super.reset(a, b, c);
		super.delta = (T) b.pow(2);
	}

	@Override
	public boolean hasOneRoot() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean hasTwoRoot() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean notHasRoot() {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public T rootSum() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public T rootProduct() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	String buildEquation() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	void fillRoot() {
		// TODO 自动生成的方法存根
	}

}
