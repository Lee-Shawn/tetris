package com.laughing.tetris.ui;

import com.laughing.tetris.config.GameConfig;

import java.awt.Graphics;


public class LayerPoint extends Layer {

	/**
	 * 分数最大位数
	 */
	private static final int POINT_BIT = 5;
	
	/**
	 * 升级行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	
	/**
	 * 分数y坐标
	 */
	private final int pointY;
	
	/**
	 * 消行y坐标
	 */
	private final int relineY;
	
	/**
	 * 消行x坐标
	 */
	private final int comX;
	
	/**
	 * 经验值y坐标
	 */
	private final int expY;
	
	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// 初始化共同的x坐标
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		// 初始化分数显示的y坐标
		this.pointY = PADDING;
		// 初始化消行显示的y坐标
		this.relineY = this.pointY + Img.IMG_POINT.getHeight(null) + PADDING;
		// 初始化经验值y坐标
		this.expY = this.relineY + Img.IMG_REMOVE_LINE.getHeight(null) + PADDING;
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		// 窗口标题(分数)
		g.drawImage(Img.IMG_POINT, this.x + PADDING, this.y + pointY, null);
		// 显示分数
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		// 窗口标题(消行)
		g.drawImage(Img.IMG_REMOVE_LINE, this.x + PADDING, this.y + relineY, null);
		// 显示消行
		this.drawNumberLeftPad(comX, relineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		// 绘制值槽(经验值)
		int rmLine = this.dto.getNowRemoveLine();
		drawRect(expY, "下一级", null, (double)(rmLine % LEVEL_UP)/(double)LEVEL_UP, g);
	}

}
