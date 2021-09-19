package com.laughing.tetris.view;

import com.laughing.tetris.config.FrameConfig;
import com.laughing.tetris.config.GameConfig;
import com.laughing.tetris.util.FrameUtil;

import javax.swing.JFrame;


public class JFrameGame extends JFrame {

	public JFrameGame(JPanelGame panelGame) {
		// 获得游戏配置
		FrameConfig fCfg = GameConfig.getFrameConfig();
		// 设置标题
		this.setTitle(fCfg.getTitle());
		// 设置默认关闭属性（程序结束）
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口大小
		this.setSize(fCfg.getWidth(), fCfg.getHeight());
		// 设置窗口大小不改变
		this.setResizable(false);
		// 设置窗口居中
		FrameUtil.setFrameCenter(this);
		// 设置默认Panel
		this.setContentPane(panelGame);
		// 设置窗口显示
		this.setVisible(true);
	}
	
}
