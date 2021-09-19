package com.laughing.tetris.main;


import com.laughing.tetris.controller.GameControl;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// 修改主题
			try {
				BeautyEyeLNFHelper.launchBeautyEyeLNF();
				UIManager.put("RootPane.setupButtonVisible", false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			new GameControl();
		});
	}
	
}
