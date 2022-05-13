package com.sunsigne.reversedrebecca.object.other.decoration;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class Bath extends GameObject {

	public Bath(int x, int y) {
		super(x, y, Size.M, 2 * Size.M);
		loadImage();
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 90;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		runAnimation();
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			image.cycle();
		}
	}

	////////// TEXTURE ////////////

	protected LimitedCycloid<BufferedImage> image;

	private void loadImage() {

		String path = "textures/other/decoration/bath_";
		ImageTask loader = new ImageTask();

		BufferedImage i0 = loader.loadImage(path + "00");
		BufferedImage i1 = loader.loadImage(path + "01");
		BufferedImage i2 = loader.loadImage(path + "02");

		image = new LimitedCycloid<BufferedImage>(i0, i1, i2);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(image.getState(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
