package com.sunsigne.reversedrebecca.object.debug;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class VisibleHitboxObject extends DebugModeObject {

	public VisibleHitboxObject(DebugMode debugMode) {
		super(debugMode);
	}

	////////// TEXTURE ////////////

	private BufferedImage img = new ImageTask().loadImage("textures/debug_visible_hitbox_mode.png");

	@Override
	public BufferedImage getImage() {
		return img;
	}

}
