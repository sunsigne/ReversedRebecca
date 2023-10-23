package com.sunsigne.reversedrebecca.object.puzzler.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class ExplodeRubbleAnimationObject extends PuzzlerAnimationObject {

	public ExplodeRubbleAnimationObject(int x, int y) {
		this(x, y, Size.M, Size.M);
		loadAnimation();
	}
	
	public ExplodeRubbleAnimationObject(int x, int y, int w, int h) {
		super(x - w / 2, y - h / 2, w, h);
		loadAnimation();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "rubble_explode";
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		if (time % 4 == 0)
			animation.cycle();
	}

	////////// TEXTURE ////////////

	private LimitedCycloid<BufferedImage> animation;

	private void loadAnimation() {

		String path = "textures/puzzler/" + getName() + "_";
		ImageTask loader = new ImageTask();

		BufferedImage i0 = loader.loadImage(path + "0");
		BufferedImage i1 = loader.loadImage(path + "1");
		BufferedImage i2 = loader.loadImage(path + "2");
		BufferedImage i3 = loader.loadImage(path + "3");
		BufferedImage i4 = loader.loadImage(path + "4");
		BufferedImage i5 = loader.loadImage(path + "5");
		BufferedImage i6 = loader.loadImage(path + "6");

		animation = new LimitedCycloid<BufferedImage>(i0, i1, i2, i3, i4, i5, i6);
	}

	@Override
	public BufferedImage getImage() {
		return animation.getState();
	}

}
