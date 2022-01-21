package com.sunsigne.reversedrebecca.world;

import java.awt.image.BufferedImage;

public class GameMap {

	public GameMap(LAYER layer, BufferedImage image) {
		this.layer = layer;
		this.image = image;
	}
	
	private LAYER layer;
	private BufferedImage image;
	
	public LAYER getLayer() {
		return layer;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
