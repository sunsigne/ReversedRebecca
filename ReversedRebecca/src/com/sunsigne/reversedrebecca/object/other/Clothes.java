package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class Clothes extends GameObject {

	public Clothes(int x, int y) {
		super(x, y, 2 * Size.M, 2 * Size.M);
		loadImage("clothes_0");
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage(String name) {
		image = new ImageTask().loadImage("textures/other/" + name);
	}

	public BufferedImage getImage() {
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
