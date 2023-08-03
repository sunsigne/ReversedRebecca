package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class DiscoFireObject extends PuzzleObject {

	public DiscoFireObject(Puzzle puzzle, int delayInTicks) {
		super(puzzle, false, 0, 0, 4 * Size.XL, 4 * Size.XL);
		this.delay = delayInTicks;
		loadAnimations();
	}

	////////// NAME ////////////

	protected String getName() {
		return "FIRE";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// TICK ////////////

	private int animation_time = 10;
	private int time = animation_time;
	private int delay;

	@Override
	public void tick() {
		delay--;
		time--;
		if (time < 0) {
			time = animation_time;
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> animation;

	private void loadAnimations() {
		BufferedImage img0 = loadImage("0");
		BufferedImage img1 = loadImage("1");
		BufferedImage img2 = loadImage("2");
		animation = new Cycloid<>(img0, img1, img2);
	}

	private BufferedImage loadImage(String name) {
		String path = "textures/puzzle/" + getPuzzle().getName() + "_fire_" + name;
		BufferedImage image = new ImageTask().loadImage(path);
		return image;
	}

	public BufferedImage getImage() {
		if (animation != null)
			return animation.getState();
		else
			return null;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (delay < 0)
			g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
