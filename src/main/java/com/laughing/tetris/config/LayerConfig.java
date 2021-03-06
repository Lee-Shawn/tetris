package com.laughing.tetris.config;

import java.io.Serializable;

public class LayerConfig implements Serializable {

	private final String className;
	
	private final int x;
	
	private final int y;
	
	private final int w;
	
	private final int h;
	
	
	
	public LayerConfig(String className, int x, int y, int w, int h) {
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public String getClassName() {
		return className;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
}
