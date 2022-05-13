package com.sunsigne.reversedrebecca.object.other.decoration;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Candle extends GameObject {

	public Candle(int x, int y) {
		super(x, y);
		loadImage();
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 16;
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

	protected Cycloid<BufferedImage> image;

	private void loadImage() {

		String path = "textures/other/decoration/candle_";
		ImageTask loader = new ImageTask();

		BufferedImage i0 = loader.loadImage(path + "00");
		BufferedImage i1 = loader.loadImage(path + "01");

		image = new Cycloid<BufferedImage>(i0, i1);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(image.getState(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
