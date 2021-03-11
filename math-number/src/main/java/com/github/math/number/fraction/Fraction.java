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

package com.github.math.number.fraction;

import com.github.math.SuperMath;
import com.github.math.exception.MathArithmeticException;
import com.github.math.number.RationalNumber;
import com.github.math.utils.MathUtils;

import java.util.Objects;

/**
 * 
 * 
 * 
 * @author 王帅
 */
public final class Fraction 
	extends RationalNumber<Fraction> implements Fractional {
	// The serialVersionUID of the class Fraction.
	private static final long serialVersionUID = -4809698195552307070L;
	
	// 一些预定义的分数常量。
	
	/** 分数： "2" */
    public static final Fraction TWO = new Fraction(2, 1, 1);

    /** 分数： "1" */
    public static final Fraction ONE = new Fraction(1, 1, 1);

    /** 分数： "0" */
    public static final Fraction ZERO = new Fraction(0, 1, 1);

    /** 分数： "4/5" */
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5, 1);

    /** 分数： "1/5" */
    public static final Fraction ONE_FIFTH = new Fraction(1, 5, 1);

    /** 分数： "1/2" */
    public static final Fraction ONE_HALF = new Fraction(1, 2, 1);

    /** 分数： "1/4" */
    public static final Fraction ONE_QUARTER = new Fraction(1, 4, 1);

    /** 分数： "1/3" */
    public static final Fraction ONE_THIRD = new Fraction(1, 3, 1);

    /** 分数： "3/5" */
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5, 1);

    /** 分数： "3/4" */
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4, 1);

    /** 分数： "2/5" */
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5, 1);

    /** 分数： "2/4" */
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4, 1);

    /** 分数： "2/3" */
    public static final Fraction TWO_THIRDS = new Fraction(2, 3, 1);

    /** 分数： "&minus;1" */
    public static final Fraction MINUS_ONE = new Fraction(-1, 1, -1);

	/** 分数的分子 */
	private final int numerator;
	/** 分数的分母 */
	private final int denominator;
	/** 分数的正符号函数 */
	private final int signum;
	
	/**
	 * <p>
	 * 由指定的一个整数创建一个分数。
	 * 
	 * @param num 分数的分子
	 */
	public Fraction(int num) {
		this.numerator   = num;
		this.denominator = 1;
		this.signum 	 = Integer.signum(numerator);
	}

	/**
	 * <p>
	 * 由指定的分子和分母创建一个分数。
	 * 
	 * @param num 分数的分子
	 * @param den 分数的分母
	 */
	public Fraction(int num, int den) {
		// 分母不应为零
		MathUtils.notZero(den, "LocalizedFormats.DENOMINATOR");
		
		// 符号问题
		if (den < 0) {
			if (den == Integer.MIN_VALUE ||
	            num == Integer.MIN_VALUE) {
				// 分子分母不能为 Integer#MIN_VALUE
	            throw new MathArithmeticException();
	        }
			den = -den;
			num = -num;
		}
		
		// 最大公因数
		int gcd = SuperMath.gcd(num, den);

		// 约分
		num /= gcd;
		den /= gcd;
		
		// 赋值
		this.numerator   = num;
		this.denominator = den;
		this.signum 	 = Integer.signum(num);
		
	}
	
	private Fraction(int numerator, int denominator, int signum) {
		this.numerator   = numerator;
		this.denominator = denominator;
		this.signum      = signum;
	}

	/**
	 * 返回当前分数的分子。
	 * 
	 * @return 当前分数的分子
	 */
	public int numerator() {
		return this.numerator;
	}

	/**
	 * 返回当前分数的分母。
	 * 
	 * @return 当前分数的分母
	 */
	public int denominator() {
		return this.denominator;
	}

	@Override
	public Fraction abs() {
		Fraction ret;
        if (numerator >= 0) {
            ret = this;
        } else {
            ret = negate();
        }
        return ret;
	}

	@Override
	@Deprecated
	public Fraction pow(int e) {
		return new Fraction((int) Math.pow(numerator, e), 
							(int) Math.pow(denominator, e));
	}

	@Override
	public Fraction min(Fraction val) {
		Objects.requireNonNull(val, "LocalizedFormats.FRACTION");
		return this.compareTo(val) < 0 ? this : val;
	}

	@Override
	public Fraction max(Fraction val) {
		Objects.requireNonNull(val, "LocalizedFormats.FRACTION");
		return this.compareTo(val) > 0 ? this : val;
	}

	@Override
	public Fraction negate() {
		// 分子不能为 Integer#MIN_VALUE 
		if (numerator == Integer.MIN_VALUE) {
            throw new MathArithmeticException();
        }
		
		return new Fraction(-numerator, denominator);
	}

	@Override
	public Fraction reciprocal() {
		return new Fraction(denominator, numerator);
	}
	
	public static Fraction reciprocal(int x) {
		return new Fraction(1, x, Integer.signum(x));
	}
	
	private Fraction addOrSub(Fraction x, boolean isAdd) {

		Objects.requireNonNull(x, "LocalizedFormats.FRACTION");
		
		if (x.signum == 0) {
			return this;
		}
		
		if (this.signum == 0) {
            return isAdd ? x : x.negate();
        }
		
		int gcd = SuperMath.gcd(denominator, x.denominator);
		int den = MathUtils.mulAndCheck(denominator, x.denominator) / gcd;
		
		int a = den / denominator;
		int b = den / x.denominator;
		
		int m = MathUtils.mulAndCheck(a, numerator);
		int n = MathUtils.mulAndCheck(b, x.numerator);
		
		int num = isAdd ? (m + n) : (m - n);
		
		return new Fraction(num, den);
		
	}

	@Override
	public Fraction add(Fraction val) {
		// 计算加法
		return addOrSub(val, true);
	}

	@Override
	public Fraction add(int i) {
		return new Fraction(numerator + denominator * i, denominator);
	}

	@Override
	public Fraction subtract(Fraction val) {
		// 计算减法
		return addOrSub(val, false);
	}

	@Override
	public Fraction subtract(int i) {
		return new Fraction(numerator - denominator * i, denominator);
	}

	@Override
	public Fraction multiply(int i) {
		return new Fraction(numerator * i, denominator);
	}

	@Override
	public Fraction multiply(Fraction val) {
		Objects.requireNonNull(val, "LocalizedFormats.FRACTION");
		
		if (val.signum == 0) {
			return ZERO;
		}
		
		int m = SuperMath.gcd(numerator, val.denominator);
		int n = SuperMath.gcd(denominator, val.numerator);
		
		return new Fraction(MathUtils.mulAndCheck(numerator / m, val.numerator / m),
							MathUtils.mulAndCheck(denominator / n, val.denominator / n));
	}

	@Override
	public Fraction divide(Fraction val) {
		Objects.requireNonNull(val, "LocalizedFormats.FRACTION");
		
		if (val.signum == 0) {
			throw new MathArithmeticException("LocalizedFormats.ZERO_TO_DIVIDE_BY");
		}
		
		return multiply(val.reciprocal());
	}

	@Override
	public Fraction divide(int i) {
		return multiply(new Fraction(1, i));
	}

	@Override
	public int signum() {
		return signum;
	}

	@Override
	public int intValue() {
		return (int) doubleValue();
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}

	@Override
	public double doubleValue() {
		return (double) numerator / (double) denominator;
	}

	@Override
	public int hashCode() {
		return 31 * (31 * denominator) + numerator;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof Fraction) {
			Fraction rhs = (Fraction) other;
			return (numerator == rhs.numerator) &&
		           (denominator == rhs.denominator);
		}
		return false;
	}

	@Override
	public String toString() {
		String str;
        if (denominator == 1) {
            str = String.valueOf(numerator);
        } else if (numerator == 0) {
            str = "0";
        } else {
            str = numerator + " / " + denominator;
        }
        return str;
	}
	
	public String toMixedString() {
		// 判断是否为带分数
		if (numerator > denominator) {
			int tmp = numerator / denominator;
			String ret = tmp + " + ";
			ret += (numerator - tmp * denominator) + " / " + denominator;
			return ret;
		}
		
		return toString();
	}
	
	@Override
	public int compareTo(Fraction o) {
		// 交叉相乘
		long m = ((long) this.numerator  ) * o.denominator;
		long n = ((long) this.denominator) * o.numerator;
		// 比较大小
		return Long.compare(m, n);
	}
	
}
