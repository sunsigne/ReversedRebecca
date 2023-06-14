package com.sunsigne.reversedrebecca.object.puzzler.computer;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerAnimationObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class HackingAnimationObject extends PuzzlerAnimationObject {

	public HackingAnimationObject(int x, int y) {
		super(x - Size.XS / 4, y - Size.M / 4, Size.XS / 2, Size.XS / 2);
		loadAnimations();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer_crash";
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		if (time % 3 == 0)
			animation.cycle();
	}

	////////// TEXTURE ////////////

	private LimitedCycloid<BufferedImage> animation;

	private void loadAnimations() {

		String path = "textures/puzzler/" + getName() + "_";
		ImageTask loader = new ImageTask();

		BufferedImage i0 = loader.loadImage(path + "0");
		BufferedImage i1 = loader.loadImage(path + "1");
		BufferedImage i2 = loader.loadImage(path + "2");
		BufferedImage i3 = loader.loadImage(path + "3");
		BufferedImage i4 = loader.loadImage(path + "4");
		BufferedImage i5 = loader.loadImage(path + "5");
		BufferedImage i6 = loader.loadImage(path + "6");
		BufferedImage i7 = loader.loadImage(path + "7");

		animation = new LimitedCycloid<BufferedImage>(i0, i1, i2, i3, i4, i5, i6, i7);
	}

	@Override
	public BufferedImage getImage() {
		return animation.getState();
	}

}
