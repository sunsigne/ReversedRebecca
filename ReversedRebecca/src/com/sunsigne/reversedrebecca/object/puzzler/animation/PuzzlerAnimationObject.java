package com.sunsigne.reversedrebecca.object.puzzler.animation;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.ressources.images.Animation;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public abstract class PuzzlerAnimationObject extends SuperPuzzlerAnimationObject {

	public PuzzlerAnimationObject(int x, int y) {
		this(x, y, 0, 0);
	}

	public PuzzlerAnimationObject(int x, int y, int w, int h) {
		super(x, y, w, h);
		loadAnimations();
	}

	////////// TICK ////////////

	public abstract FRAME_RATE getFrameRate();

	protected enum FRAME_RATE {
		NORMAL(4), FAST(3);

		FRAME_RATE(int value) {
			this.value = value;
		}

		private int value;

		public int getValue() {
			return value;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (time % getFrameRate().getValue() == 0)
			animation.cycle();
	}

	////////// TEXTURE ////////////

	private LimitedCycloid<BufferedImage> animation;

	protected Animation buildAnimation(BufferedImage image) {
		return new Animation(image);
	}

	private void loadAnimations() {
		BufferedImage image = new ImageTask().loadImage("textures/puzzler/animation/" + getName());
		Animation images = buildAnimation(image);
		animation = new LimitedCycloid<BufferedImage>(images.getImages());
	}

	@Override
	public BufferedImage getImage() {
		return animation.getState();
	}

}
