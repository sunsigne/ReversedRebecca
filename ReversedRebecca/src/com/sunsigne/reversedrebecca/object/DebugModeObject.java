package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class DebugModeObject extends GameObject {

	public DebugModeObject(DebugMode debugMode) {
		super(Window.WIDHT - Size.L, Window.HEIGHT - Size.L, Size.L, Size.L);
		this.debugMode = debugMode;
		loadImage();
	}

	////////// DEBUG MODE ////////////

	private DebugMode debugMode;

	////////// TICK ////////////

	@Override
	public void tick() {

	}
	
	////////// TEXTURE ////////////
	
	private BufferedImage img;
	
	private void loadImage() {
		img = new ImageTask().loadImage("textures/" + debugMode.getName());
	}
	
	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (debugMode.getState())
			g.drawImage(img, getX(), getY() - (debugMode.getIndex()) * getHeight(), getWidth(), getHeight(),
					null);
	}

}
