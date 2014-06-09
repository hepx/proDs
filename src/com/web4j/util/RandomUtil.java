package com.web4j.util;

import java.util.Random;

/**
 * 随机数工具类
 * @author xixi
 * @date 2013-3-20
 *
 */
public class RandomUtil {
	private static Random random;
	static{
		random=new Random();
	}
	
	/**
	 * 返回0（包括）到seed（不包括）之间的随机数
	 * @param seed
	 * @return
	 */
	public static int getRandom4Int(int seed){
		return random.nextInt(seed);
	}
	
	/**
	 * 默认生成一个0到100之间的数
	 * @return
	 */
	public static int getRandom4Int(){
		return random.nextInt(100);
	}
	
}
