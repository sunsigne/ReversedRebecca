package com.sunsigne.reversedrebecca.object.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class SuperAnimationObject extends GameObject implements SheetableImage {

	public SuperAnimationObject(int x, int y) {
		super(x + Size.XS / 8, y + Size.XS / 8, Size.XL / 2, Size.XL / 2);
	}

	public SuperAnimationObject(int x, int y, int w, int h) {
		super(x + Size.XS / 8, y + Size.XS / 8, w + Size.XL / 2, h + Size.XL / 2);
	}

	////////// NAME ////////////

	public abstract String getName();

	@Override
	public String toString() {
		var clazz = "ANIMATION";
		return clazz + " : " + getName().toUpperCase();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.ANIMATION;
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

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public abstract BufferedImage getImage();

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
