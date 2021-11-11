package com.sunsigne.reversedrebecca.object.debug;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class FastPlayerObject extends DebugModeObject {

	public FastPlayerObject(DebugMode debugMode) {
		super(debugMode);
	}

	////////// TEXTURE ////////////

	private BufferedImage img = new ImageTask().loadImage("textures/debug_fast_player_mode.png");

	@Override
	public BufferedImage getImage() {
		return img;
	}

}
