package com.laughing.tetris.ui;

import java.awt.Graphics;

public class LayerButton extends Layer {

	public LayerButton(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
	}

}
