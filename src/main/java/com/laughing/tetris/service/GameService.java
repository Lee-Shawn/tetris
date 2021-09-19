package com.laughing.tetris.service;

public interface GameService {

	/**
	 * 方向上
	 */
	boolean keyUp();
	
	/**
	 * 方向下
	 */
	boolean keyDown();
	
	/**
	 * 方向左
	 */
	boolean keyLeft();
	
	/**
	 * 方向右
	 */
	boolean keyRight();
	
	/**
	 * 功能1：作弊
	 */
	boolean keyFunUp();
	
	/**
	 * 功能2：下落
	 */
	boolean keyFunDown();
	
	/**
	 * 功能3：阴影
	 */
	boolean keyFunLeft();
	
	/**
	 * 功能4：暂停
	 */
	boolean keyFunRight();
	
	/**
	 * 启动主线程，开始游戏
	 */
	void startGame();
	
	/**
	 * 游戏主要行为
	 */
	void mainAction();
	
}
