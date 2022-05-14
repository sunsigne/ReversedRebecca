package com.sunsigne.reversedrebecca.object.other.decoration;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DecorationObject extends GameObject implements TickFree {

	public DecorationObject(int x, int y, String name) {
		this(x, y, Size.M, Size.M, name);
	}
	
	protected DecorationObject(int x, int y, int w, int h, String name) {
		super(x, y, w, h);
		loadImage(name);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage(String name) {
		image = new ImageTask().loadImage("textures/other/decoration/" + name);
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
