package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.Cycloid;
import com.sunsigne.reversedrebecca.pattern.MousePos;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class VirusObject extends PuzzleObject implements CollisionReactor {

	public VirusObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		loadImage();
	}

	////////// TICK ////////////

	protected final int ymin = getPuzzle().getRow(1) - 4 * (Size.L / Size.XS);
	protected final int ymax = getPuzzle().getRow(6) + 4 * (Size.L / Size.XS);

	protected final int xmin = getPuzzle().getCol(1);
	protected final int xmax = getPuzzle().getCol(12);

	private final int ANIMATION_TIME = 15;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0];
		int mouseY = new MousePos().get()[1];

		followMouse(mouseX, mouseY);
		keepWithinPuzzleZone(mouseX, mouseY);

		runAnimation();
	}

	private void followMouse(int mouseX, int mouseY) {
		setX(mouseX);
		setY(mouseY);
	}

	private void keepWithinPuzzleZone(int mouseX, int mouseY) {
		if (mouseX > xmax)
			setX(xmax);
		if (mouseX < xmin)
			setX(xmin);

		if (mouseY > ymax)
			setY(ymax);
		if (mouseY < ymin)
			setY(ymin);
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			img.cycle();
		}
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> img;

	private void loadImage() {
		String path = "textures/puzzle/" + getPuzzle().getName() + "_virus";
		BufferedImage img0 = new ImageTask().loadImage(path + "_0");
		BufferedImage img1 = new ImageTask().loadImage(path + "_1");
		img = new Cycloid<BufferedImage>(img0, img1);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(img.getState(), getX(), getY(), getWidth(), getHeight(), null);
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
