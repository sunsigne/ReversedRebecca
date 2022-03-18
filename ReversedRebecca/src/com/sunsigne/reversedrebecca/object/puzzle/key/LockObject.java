package com.sunsigne.reversedrebecca.object.puzzle.key;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.MousePos;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class LockObject extends PuzzleObject implements CollisionReactor {

	public LockObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		loadImage();
	}

	////////// TICK ////////////

	protected final int ymin = getPuzzle().getRow(1);
	protected final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {
		int mouseY = new MousePos().get()[1];

		setY(mouseY);

		if (mouseY > ymax)
			setY(ymax);
		if (mouseY < ymin)
			setY(ymin);
	}

	////////// TEXTURE ////////////

	private BufferedImage img;

	private void loadImage() {
		img = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_lock");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
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
