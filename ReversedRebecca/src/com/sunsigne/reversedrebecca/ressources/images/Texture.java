package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Position;

public interface Texture extends Position {
	
	////////// TEXTURE ////////////
	
	void createTexture();
	
	BufferedImage getImage();
}
