package com.sunsigne.reversedrebecca.object.other;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DestinyBackgroundObject extends GameObject implements TickFree {

	public DestinyBackgroundObject() {
		super(0, 0);
	}

	public String getName() {
		return "hall_of_destiny";
	}

	@Override
	public String toString() {
		return "DESTINY BACKGROUND";
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.HUD;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/other/" + getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int pixel = 16;
		int ratio = Size.M / pixel;
		int width = getImage().getWidth() * ratio;
		int height = getImage().getHeight() * ratio;

		g.drawImage(getImage(), getX(), getY(), width, height, null);
	}

}
