package com.laughing.tetris.util;

public class GameFunction {

	/**
	 * 计算线程睡眠事件
	 */
	public static long getSleepTimeByLevel(int level) {
		long sleep = (-40L * level + 740);
		sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}
	
}
