package com.laughing.tetris.ui;

import com.laughing.tetris.config.GameConfig;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class Img {
	
	private Img() {}
	
	/**
	 * 图片路径
	 */
	public static final String GRAPHICS_PATH = "src/main/resources/image/";
	
	public static final String DEFAULT_PATH = "default";
	
	public static void setSkin(String path) {
		String skinPath = GRAPHICS_PATH + path;
		// 方框图片
		WINDOW_IMG = new ImageIcon(skinPath + "/window/Window.png").getImage();
		 // 数字图片
		IMG_NUMBER = new ImageIcon(skinPath + "/string/num.png").getImage();
		// 矩形值槽
		IMG_RECT = new ImageIcon(skinPath + "/window/rect.png").getImage();
		// 窗口标题(分数)
		IMG_POINT = new ImageIcon(skinPath + "/string/point.png").getImage();
		// 窗口标题(消行)
		IMG_REMOVE_LINE = new ImageIcon(skinPath + "/string/removeLine.png").getImage();
		// 个人签名
		IMG_SIGN = new ImageIcon(skinPath + "/string/sign.png").getImage();
		// 数据库
		IMG_DB = new ImageIcon(skinPath + "/string/db.png").getImage();
		// 本地记录
		IMG_DISK = new ImageIcon(skinPath + "/string/disk.png").getImage();
		// 值槽
		ACT = new ImageIcon(skinPath + "/game/rect.png").getImage();
		// 标题图片
		IMG_LV = new ImageIcon(skinPath + "/string/level.png").getImage();
		// 阴影
		IMG_SHADOW = new ImageIcon(skinPath + "/game/shadow.png").getImage();
		// 开始按钮
		BTN_START = new ImageIcon(skinPath + "/string/start.png");
		// 设置按钮
		BTN_CONFIG = new ImageIcon(skinPath + "/string/config.png");
		// 暂停
		PAUSE = new ImageIcon(skinPath + "/string/pause.png").getImage();
		// 下一个方块图片
		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon(skinPath + "/game/" + i + ".png").getImage();
		}
		// 背景图片
		File dir = new File(skinPath + "/background");
		File[] files = dir.listFiles();
		if (files == null) {
			throw new RuntimeException("文件不存在！");
		}
		BG_LIST = new ArrayList<>();
		for (File file : files) {
			if (!file.isDirectory()) {
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			}
		}
	}
	
	/**
	 * 方框图片
	 */
	public static Image WINDOW_IMG = null;
	
	/**
	 * 数字图片
	 */
	public static Image IMG_NUMBER = null;
	
	/**
	 * 矩形值槽
	 */
	public static Image IMG_RECT = null;
	
	/**
	 * 窗口标题(分数)
	 */
	public static Image IMG_POINT = null;
	
	/**
	 * 窗口标题(消行)
	 */
	public static Image IMG_REMOVE_LINE = null;
	
	/**
	 * 个人签名
	 */
	public static Image IMG_SIGN = null;
	
	/**
	 * 数据库
	 */
	public static Image IMG_DB = null;
	
	/**
	 * 本地记录
	 */
	public static Image IMG_DISK = null;
	
	/**
	 * 值槽
	 */
	public static Image ACT = null;
	
	/**
	 * 标题图片
	 */
	public static Image IMG_LV = null;
	
	/**
	 * 阴影
	 */
	public static Image IMG_SHADOW = null;
	
	/**
	 * 开始按钮
	 */
	public static ImageIcon BTN_START = null;
	
	/**
	 * 设置按钮
	 */
	public static ImageIcon BTN_CONFIG = null;
	
	/**
	 * 暂停
	 */
	public static Image PAUSE = null;
	
	/**
	 * 下一个图片数组
	 */
	public static Image[] NEXT_ACT = null;
	
	/**
	 * 背景
	 */
	public static List<Image> BG_LIST = null;

	// 初始化图片
	static {
		setSkin(DEFAULT_PATH);
	}

}
