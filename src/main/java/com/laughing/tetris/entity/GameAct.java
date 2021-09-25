package com.laughing.tetris.entity;

import com.laughing.tetris.config.GameConfig;

import java.awt.Point;
import java.util.List;


public class GameAct {

	/**
	 * 方块数组
	 */
	private Point[] actPoints = null;
	
	/**
	 * 方块编号
	 */
	private int typeCode;
	
	private static final int MIN_X = GameConfig.getSystemConfig().getMinX();
	private static final int MAX_X = GameConfig.getSystemConfig().getMaxX();
	private static final int MIN_Y = GameConfig.getSystemConfig().getMinY();
	private static final int MAX_Y = GameConfig.getSystemConfig().getMaxY();
	
	private final static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeConfig();
	private final static List<Boolean> TYPE_ROUND = GameConfig.getSystemConfig().getTypeRound();
	
	public GameAct(int typeCode) {
		this.init(typeCode);
	}
	
	public void init(int typeCode) {
		this.typeCode = typeCode;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}

	public Point[] getActPoints() {
		return actPoints;
	}
	
	/**
	 * 方块移动
	 * @param moveX X轴偏移量
	 * @param moveY Y轴偏移量
	 */
	public boolean move(int moveX, int moveY, boolean[][] gameMap) {
		// 移动处理
		for (Point actPoint : actPoints) {
			int newX = actPoint.x + moveX;
			int newY = actPoint.y + moveY;
			if (this.isOverZone(newX, newY, gameMap)) {
				return false;
			}
		}
		for (Point actPoint : actPoints) {
			actPoint.x += moveX;
			actPoint.y += moveY;
		}
		
		return true;
	}
	
	/**
	 * 方块旋转
	 * 
	 * 顺时针：
	 * A.x = 0.x + 0.y - B.y
	 * A.y = 0.y - 0.x + B.x
	 */
	public void round(boolean[][] gameMap) {
		if (!TYPE_ROUND.get(this.typeCode)) {
			return;
		}
		for (int i = 1; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			if (this.isOverZone(newX, newY, gameMap)) {
				return;
			}
		}
		for (int i = 1; i < actPoints.length; i++) {
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}
	}
	
	/**
	 * 判断是否超出边界
	 * @param x 遊戲區域 x 坐標
	 * @param y 遊戲區域 y 坐標
	 * @return 是否出界
	 */
	private boolean isOverZone(int x, int y, boolean[][] gameMap) {
		return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || gameMap[x][y];
	}

	/**
	 * 获得方块类型编号
	 */
	public int getTypeCode() {
		return typeCode;
	}

}
