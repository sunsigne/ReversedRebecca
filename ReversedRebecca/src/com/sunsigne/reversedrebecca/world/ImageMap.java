package com.sunsigne.reversedrebecca.world;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class ImageMap {

	public ImageMap(LAYER layer, BufferedImage image) {
		this.layer = layer;
		this.image = image;
	}
	
	private LAYER layer;

	public LAYER getLayer() {
		return layer;
	}
	
	private BufferedImage image;
		
	public BufferedImage getImage() {
		return image;
	}
	
}
