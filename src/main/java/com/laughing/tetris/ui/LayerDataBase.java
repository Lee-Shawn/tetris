package com.laughing.tetris.ui;

import java.awt.Graphics;

public class LayerDataBase extends LayerData {

	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}

	@Override
	public void paint(Graphics g) {
		this.createWindow(g);
		this.showData(Img.IMG_DB, this.dto.getDbRecode(), g);
	}

}
