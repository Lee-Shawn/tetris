package com.laughing.tetris.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {

	/**
	 * 窗口居中
	 */
	public static void setFrameCenter(JFrame jf) {
		// 设置窗口居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// 获取屏幕大小
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - jf.getWidth()) >> 1;
		int y = (screen.height - jf.getHeight()) >> 1;
		// 设置窗口位置
		jf.setLocation(x, y);
	}
	
}
