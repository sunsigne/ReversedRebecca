package com.sunsigne.reversedrebecca.object.other.decoration;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class Candle extends GameObject {

	public Candle(int x, int y) {
		super(x, y);
		loadAnimation();
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
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	protected Cycloid<BufferedImage> animation;

	private void loadAnimation() {
		
		String path = "textures/other/decoration/candle_";
		ImageTask loader = new ImageTask();
		
		BufferedImage i0 = loader.loadImage(path + "00");
		BufferedImage i1 = loader.loadImage(path + "01");
		
		animation = new Cycloid<BufferedImage>(i0, i1);
	}
	
	public BufferedImage getImage() {
		return animation.getState();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
