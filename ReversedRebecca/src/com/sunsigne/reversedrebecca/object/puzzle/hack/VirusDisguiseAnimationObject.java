package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class VirusDisguiseAnimationObject extends VirusObject {

	public VirusDisguiseAnimationObject(Puzzle puzzle, int x, int y, boolean critical) {
		super(puzzle, critical);
		setX(x);
		setY(y);
	}

	////////// ANTIVIRUS ////////////

	public boolean isDisguised() {
		return true;
	}
	
	public boolean isReversed() {
		return false;
	}

	////////// TICK ////////////

	private int growing;
	private float alpha = 0.5f;

	@Override
	public void tick() {
		growing = growing + 8;
		alpha = alpha - 0.03f;

		if (alpha <= 0.03)
			removeObject();
	}

	////////// RENDER ////////////

	@Override
	protected void dragImage(Graphics g, BufferedImage image) {
		int x = getX() - growing;
		int y = getY() - growing;
		int w = getWidth() + 2 * (growing);
		int h = getHeight() + 2 * (growing);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		g.drawImage(image, x, y, w, h, null);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
