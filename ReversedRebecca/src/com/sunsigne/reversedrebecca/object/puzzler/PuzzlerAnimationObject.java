package com.sunsigne.reversedrebecca.object.puzzler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class PuzzlerAnimationObject extends GameObject {

	public PuzzlerAnimationObject(int x, int y) {
		super(x + Size.XS / 8, y + Size.XS / 8, Size.XL / 2, Size.XL / 2);
	}

	////////// NAME ////////////

	public abstract String getName();

	@Override
	public String toString() {
		var clazz = "PUZZLER ANIMATION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// TICK ////////////

	protected final int ANIMATION_TIME = 60;
	protected int time = ANIMATION_TIME;

	@Override
	public void tick() {
		time--;

		if (time <= 0)
			removeObject();
	}

	////////// TEXTURE ////////////

	public abstract BufferedImage getImage();

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
