package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;

public class CookieTitleObject extends PuzzleObject {

	public CookieTitleObject(Puzzle puzzle) {
		super(puzzle, false, (Window.WIDHT - 1100) / 2, 450, 1100, 200);
	}

	////////// NAME ////////////

	protected String getName() {
		return "COOKIE TITLE";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	private float alpha = -0.2f;
	private float alphaSpeed = 0.012f;

	@Override
	public void tick() {
		alpha = alpha + alphaSpeed;

		if (alpha >= 3f) {
			alpha = 1f;
			alphaSpeed = -alphaSpeed;
		}

		if (alpha <= -0.6f)
			removeObject();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + "cookie" + "_title");
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
