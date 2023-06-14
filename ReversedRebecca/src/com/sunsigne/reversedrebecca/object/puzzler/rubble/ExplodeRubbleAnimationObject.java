package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.other.BonusText;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerAnimationObject;
import com.sunsigne.reversedrebecca.pattern.cycloid.LimitedCycloid;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;

public class ExplodeRubbleAnimationObject extends PuzzlerAnimationObject {

	public ExplodeRubbleAnimationObject(int x, int y) {
		super(x - Size.M / 2, y - Size.M / 2, Size.M, Size.M);
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

		final int gap = 8;

		if (time % 4 == 0)
			animation.cycle();

		switch (time) {
		case ANIMATION_TIME - 1:
			displayText();
			break;

		case ANIMATION_TIME - 20:
			bonusText.setMotionless();
			break;

		case 24 + gap:
		case 16 + gap:
		case 8 + gap:
			hiddenText = true;
			break;
		case 20 + gap:
		case 12 + gap:
		case 4 + gap:
			hiddenText = false;
			break;
		case 1 + gap:
			LAYER.WORLD_TEXT.getHandler().removeObject(bonusText);
			break;
		}

	}

	private BonusText bonusText;
	private boolean hiddenText;

	private void displayText() {
		String text = new Translatable().getTranslatedText("BOOM", FilePath.BONUS_TEXT);

		bonusText = new BonusText(text, getX() + getWidth() / 4 + Size.XS / 6, getY() + Size.XS / 3, false) {

			@Override
			public void tick() {

			}

			@Override
			public void render(Graphics g) {
				if (hiddenText == false)
					super.render(g);
			}
		};

		bonusText.setVelY(-2);
		LAYER.WORLD_TEXT.addObject(bonusText);
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
