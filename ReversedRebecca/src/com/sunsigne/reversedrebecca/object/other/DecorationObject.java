package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DecorationObject extends GameObject implements TickFree {

	public DecorationObject(int x, int y, int w, int h, String name) {
		super(x, y, w, h);
		this.name = name;
	}

	////////// NAME ////////////

	private String name;

	public String getName() {
		return name;
	}
	
	protected String getPath() {
		return "textures/other/decoration/";
	}
	
	@Override
	public String toString() {
		var clazz = "DECORACTION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage(getPath() + getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
