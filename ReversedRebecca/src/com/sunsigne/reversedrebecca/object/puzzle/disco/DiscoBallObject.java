package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;

public class DiscoBallObject extends PuzzleObject {

	public DiscoBallObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, Window.WIDHT, Window.HEIGHT);
		loadAnimations();
	}

	////////// NAME ////////////

	protected String getName() {
		return "BALL";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// TICK ////////////

	private int animation_time = 50;
	private int time = animation_time;

	@Override
	public void tick() {
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
		animation = new Cycloid<>(img0, img1);
	}

	private BufferedImage loadImage(String name) {
		String path = "textures/puzzle/" + getPuzzle().getName() + "_ball_" + name;
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
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
