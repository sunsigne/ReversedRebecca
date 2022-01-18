package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class DebugModeObject extends GameObject implements Texture {

	public DebugModeObject(DebugMode debugMode) {
		super(Window.WIDHT - Size.L, Window.HEIGHT - Size.L, Size.L, Size.L);
		this.debugMode = debugMode;
		createTexture();
	}

	////////// DEBUG MODE ////////////

	private DebugMode debugMode;

	////////// TICK ////////////

	@Override
	public void tick() {

	}
	
	////////// TEXTURE ////////////
	
	private BufferedImage img;
	
	@Override
	public void createTexture() {
		img = new ImageTask().loadImage("textures/" + debugMode.getName() + ".png");
	}
	
	@Override
	public BufferedImage getImage() {
		return img;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (debugMode.getState())
			g.drawImage(getImage(), getX(), getY() - (debugMode.getIndex()) * getHeight(), getWidth(), getHeight(),
					null);
	}



}
