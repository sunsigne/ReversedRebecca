package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class ExclamationObject extends GameObject {

	public ExclamationObject(ExtraBehaviorsObject object) {
		super(object.getX(), object.getY() - Size.M);
		loadImage();
	}

	////////// TICK ////////////

	private int time;
	private final int MAX_TIME = 50;

	@Override
	public void tick() {
		if (time < MAX_TIME)
			time++;

		if (time >= MAX_TIME)
			getHandler().removeObject(this);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImage() {
		image = new ImageTask().loadImage("textures/other/" + "exclamation");
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
