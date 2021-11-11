package com.sunsigne.reversedrebecca.object.debug;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class WallPassObject extends DebugModeObject {

	public WallPassObject(DebugMode debugMode) {
		super(debugMode);
	}

	////////// TEXTURE ////////////

	private BufferedImage img = new ImageTask().loadImage("textures/debug_wall_pass_mode.png");

	@Override
	public BufferedImage getImage() {
		return img;
	}

}
