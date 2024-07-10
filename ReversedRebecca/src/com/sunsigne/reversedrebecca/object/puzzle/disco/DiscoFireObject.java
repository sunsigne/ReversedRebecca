package com.sunsigne.reversedrebecca.object.puzzle.disco;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.Animation;
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

	private int ANIMATION_TIME = 10;
	private int time = ANIMATION_TIME;
	private int delay;

	@Override
	public void tick() {
		delay--;
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> animation;

	private void loadAnimations() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "disco_fire");
		Animation images = new Animation(sheet);
		animation = new Cycloid<BufferedImage>(images.getImages());
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
