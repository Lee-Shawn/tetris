package com.laughing.tetris.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControl extends KeyAdapter {

	private final GameControl gameControl;
	
	public PlayerControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	/**
	 * 键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}

}
