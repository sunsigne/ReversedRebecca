package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class LittleCookieObject extends PuzzleObject implements SheetableImage {

	public LittleCookieObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, Size.S, Size.S);
	}

	////////// NAME ////////////

	protected String getName() {
		return "LITTLE COOKIE";
	}

	@Override
	public String toString() {
		String pos = getX() + "-" + getY();		
		return "PUZZLE : " + getName() + " : " + pos;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	private float alpha = 1f;
	private float alphaSpeed = 0.03f;
	private boolean flag;

	@Override
	public void tick() {
		if (flag == false) {
			flag = true;
			randomize();
		}

		alpha = alpha - alphaSpeed;
		if (alpha <= 0.0f)
			removeObject();
	}

	private void randomize() {
		MousePos mousePos = new MousePos();
		setX(mousePos.getX() - getWidth() / 2);
		setY(mousePos.getY() - getHeight() / 2);
		int velX;
		int velY;
		do {
			velX = new RandomGenerator().getIntBetween(-1, 1);
			velY = new RandomGenerator().getIntBetween(-1, 1);
		} while (velX == 0 && velY == 0);

		setVelX(velX);
		setVelY(velY);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetSize() {
		return 48;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		float alpha = Math.min(1, Math.max(0, this.alpha));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
