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
 * <p>电流的有关单位。
 * 
 * <p><strong>
 * 此类为一个标准模板，以下展示了所写类的一些规则，请务必仔细阅读并遵守规则来写其他的类。
 * </strong><br>
 * 
 * <blockquote>
 * 1. 所写类的单位<b>一定一定一定要找全</b>，最好百度一下有关的内容，争取做到无一疏漏。<br>
 * 2. 这个单位的实体类是Java的枚举，可能有些陌生甚至没用过，不过这不用担心，
 *    在下面我会教你们如何使用它。<br>
 * 3. 在写的过程中如果有不明白的地方，<b>比如：单位进率不明确</b>，那就先把那个单位注释掉，
 *    到后来我审核的时候一看到注释掉的单位我会自己填好的，省的拉掉单位造成不全。<br>
 * 4. 你可以在这个类的注释上添加作者，并把你的名字骄傲的加进去。
 * </blockquote>
 * 
 * <p>下面我就以这个电流实体类阐述一下创作步骤与注意事项，
 * 	    切记切记：<b><u>obey the rules</u></b>
 * 
 * @author 王帅
 * @author 在此骄傲的填上你的大名！
 */
public enum ElectricCurrent implements Unit {
	
	//======|此部分为电流单位常量，你的任务：定义这些常量|=======
	
	/*
	 * 定义常量首先需满足以下几个条件（必不可少）：
	 * 
	 * 1. 标准的 注释
	 * 2. 标准的 名称
	 * 3. 标准的 进率
	 * 
	 * 注意：必须做到标准！！！
	 */
	
	//下面我以安培单位的定义阐述定义规则。
	
	/*
	 * 1. 打开百度搜索“电流单位”，确保安培是电流单位。
	 * 
	 * 2. 用翻译工具把“安培”译成英文（ampere）。
	 * 
	 * 3. 把英文转为大写(ctrl  + shift + x)，并写在类中，注意后面加括号与逗号。
	 * 
	 * 		AMPERE(), 
	 * 
	 * 注：枚举中常量的定义格式为：常量1+逗号+常量2+逗号+...+常量n+分号。
	 * 	      简单来说每个常量之间用逗号隔开，最好一个常量后用分号结束。
	 * 
	 * 4. 在括号中写出进率，就是	1主单位 = 多少此单位（就是正写的单位）。
	 *    还是以此为例，先要确定主单位，电流的主单位是安培，
	 *    正巧我们写的单位也是安培，根据上面公式：1安培 = 1安培，
	 *    所以括号里应填的数值为1.0。
	 *    
	 * 5. 写注释（别小瞧他，学问可大了），其中应遵循以下规则（忽略//中的括号）：
	 * 		1). 注视方向总是为横向，即：/(***)/ 形式；
	 * 		2). 在写之前都要在第二个 * 后按一下Tab键；即：/(**	*)/
	 * 		3). 完事后写单位的中文名称，即：/(**	安培*)/
	 * 		4). 然后是英文的括号，括号中写单位的字母，即：/(**	安培(A)*)/
	 * 		5). 最后在 ) 后按一下Tab键；即：/(**	安培(A)	*)/
	 * 
	  * 6. 对于中国制单位，常量名称可以使用汉语拼音，但在注释中要注明（括号为中文状态下）。
	 * 
	 *    例如：长度单位中有一种中国制单位：毫，其中：1 米 = 30000 毫。
	 *    
	 *    /(**	 毫（中国长度单位）	*)/
	 *	  HAO(3e4);
	 * 
	 * 7. 在下面我写了几个例子，照着样子写，尤其是进率那别写错了，多读两遍总会明白的。
	 * 
	 *    		最后在唠叨一句：单位一定要写全全的！进率一定要算的准准的！
	 */
	/**	国际安培(A int)	*/	//实在不会列一个比例式 1 A int ：0.99985 A = x A int : 1 A
	INTERNATIONAL_AMPERE("国际安培(A int)", 1.0 / 0.99985),//必要时为了准确，这也可以是一个式子。
	/**	安培(A)	*/
	AMPERE("安培(A)", 1.0),//安培是电流的主单位。
	/**	毫安(mA)	*/
	MILLIAMPERE("毫安(mA)", 1e3);//1主单位 = 多少此单位，其中主单位为安培，此单位为毫安。
	//1安培 = 1000毫安 所以括号中填1000，也就是1*10三次方，也就是1e3。
	
	//====|以下为电流实体类的一些方法，这不用你管|=====
	
	/** 单位的名称。*/
	private final String name;
	
	/** 与国际制主单位间进率。*/
	private final double rate;
	
	/**
	 * <p>构造一个电流单位并确定它与国际制主单位间的进率。
	 * 
	 * @param rate 与国际制主单位间进率
	 */
	private ElectricCurrent(String name, double rate) {
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
	 * <p>获取电流单位的国际制主单位。
	 * 
	 * @return 电流的国际制主单位
	 */
	public static ElectricCurrent getSI() {
		return AMPERE;
	}

}
