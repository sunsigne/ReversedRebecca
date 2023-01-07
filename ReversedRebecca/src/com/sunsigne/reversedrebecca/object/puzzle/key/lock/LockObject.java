package com.sunsigne.reversedrebecca.object.puzzle.key.lock;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class LockObject extends PuzzleObject implements MouseObject, CollisionReactor {

	public LockObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0);

		if (isCritical())
			xmax = getPuzzle().getCol(11);
	}

	////////// NAME ////////////

	protected String getName() {
		return "LOCK";
	}

	@Override
	public String toString() {
		String critical = isCritical() ? " CRITICAL" : "";
		return "PUZZLE : " + getName() + critical + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// TICK ////////////

	protected final int xmin = getPuzzle().getCol(1);
	protected int xmax = getPuzzle().getCol(1);
	protected final int ymin = getPuzzle().getRow(1);
	protected final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0];
		int mouseY = new MousePos().get()[1];

		followMouse(mouseX, mouseY);
		keepWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_lock");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getPuzzle().closePuzzle(true));
	}

}
