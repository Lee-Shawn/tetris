package com.laughing.tetris.ui;

import com.laughing.tetris.config.GameConfig;
import com.laughing.tetris.entity.GameAct;

import java.awt.Graphics;
import java.awt.Point;


public class LayerGame extends Layer {
	
	/**
	 * 左位移
	 */
	private static final int SIZE_ROL = GameConfig.getFrameConfig().getSizeRol();
	
	private static final int LEFT_SIZE = 0;
	
	private static final int RIGHT_SIZE = GameConfig.getSystemConfig().getMaxX();
	
	private static final int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();

	public LayerGame(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// 获得方块数组集合
		GameAct act = this.dto.getGameAct();
		if (act != null) {
			Point[] points = act.getActPoints();
			// 绘制阴影
			this.drawShadow(points, g);
			// 绘制活动方块
			this.drawMainAct(points, g);
		}
		// 绘制 游戏地图
		this.drawMap(g);
		// 暂停
		if (this.dto.isPause()) {
			this.drawImageAtCenter(Img.PAUSE, g);
		}
	}
	
	/**
	 * 绘制活动方块
	 * @param g 画笔
	 */
	private void drawMainAct(Point[] points, Graphics g) {
		// 获得方块类型编号(0-6)
		int typeCode = this.dto.getGameAct().getTypeCode();
		// 绘制方块
		for (Point point : points) {
			this.drawActByPoint(point.x, point.y, typeCode + 1, g);
		}
	}
	
	/**
	 * 绘制游戏地图
	 * @param g 画笔
	 */
	private void drawMap(Graphics g) {
		// 绘制地图
		boolean[][] map = this.dto.getGameMap();
		// 计算当前堆积方块颜色
		int lv = this.dto.getNowLevel();
		int imgIdx = lv == 0 ? 0 : ((lv-1) % 7 + 1);
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y]) {
					this.drawActByPoint(x, y, imgIdx, g);
				}
			}
		}
	}

	/**
	 * 绘制阴影
	 */
	private void drawShadow(Point[] points, Graphics g) {
		if (!this.dto.isShowShadow()) {
			return;
		}
		int leftX = RIGHT_SIZE;
		int rightX = LEFT_SIZE;
		for (Point p : points) {
			leftX = Math.min(p.x, leftX);
			rightX = Math.max(p.x, rightX);
		}
		g.drawImage(Img.IMG_SHADOW,
			this.x + BORDER + (leftX << SIZE_ROL), 
			this.y + BORDER, 
			(rightX - leftX + 1) << SIZE_ROL, 
			this.h - (BORDER << 1), null
		);
	}

	/**
	 * 绘制正方形块
	 */
	private void drawActByPoint(int x, int y, int imgIdx, Graphics g) {
		imgIdx = this.dto.isStart() ? imgIdx : LOSE_IDX;
		g.drawImage(Img.ACT,
				this.x + (x << SIZE_ROL) + BORDER,
				this.y + (y << SIZE_ROL) + BORDER,
				this.x + (x + 1 << SIZE_ROL) + BORDER,
				this.y + (y + 1 << SIZE_ROL) + BORDER,
				imgIdx << SIZE_ROL, 0, (imgIdx + 1) << SIZE_ROL, 1 << SIZE_ROL, null);
	}

}
