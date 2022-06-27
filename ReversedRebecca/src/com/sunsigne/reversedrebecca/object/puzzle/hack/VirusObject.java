package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class VirusObject extends PuzzleObject {

	public VirusObject(Puzzle puzzle) {
		super(puzzle, 0, 0, Size.M, Size.M);
		loadAnimation();
	}

	////////// ANTIVIRUS ////////////

	private int shrink = 0;

	public void shrink() {
		shrink = shrink + 4;
	}

	public void cancelShrink() {
		shrink = 0;
	}

	private boolean disguised;

	public boolean isDisguised() {
		return disguised;
	}

	public void setDisguised(boolean disguised) {
		this.disguised = disguised;
	}

	private boolean reversed;

	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}

	////////// TICK ////////////

	protected final int ymin = getPuzzle().getRow(1) - 4 * (Size.M / Size.XS);
	protected final int ymax = getPuzzle().getRow(6) + 22 * (Size.S / Size.XS);

	protected final int xmin = getPuzzle().getCol(1);
	protected final int xmax = getPuzzle().getCol(12) + 16 * (Size.S / Size.XS);

	private final int ANIMATION_TIME = 15;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0] - getWidth() / 2;
		int mouseY = new MousePos().get()[1] - getHeight() / 2;

		if (isReversed()) {
			mouseX = xmax + xmin - mouseX;
			mouseY = ymax + ymin - mouseY;
		}

		followMouse(mouseX, mouseY);
		keepWithinPuzzleZone(mouseX, mouseY);

		// if Mouse Peripheral has been destroyed
		if (((HackPuzzle) getPuzzle()).getComputer().hasMouse() == false)
			erraticMovements();

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

	private void erraticMovements() {
		int range = 20;
		setX(getX() + new RandomGenerator().getIntBetween(-range, range));
		setY(getY() + new RandomGenerator().getIntBetween(-range, range));
	}

	private void runAnimation() {
		time--;
		if (time < 0) {
			time = ANIMATION_TIME;
			animation.cycle();
		}
	}

	////////// TEXTURE ////////////

	private Cycloid<BufferedImage> animation;

	private void loadAnimation() {

		String path = "textures/puzzle/" + getPuzzle().getName() + "_virus";
		ImageTask loader = new ImageTask();

		BufferedImage img0 = loader.loadImage(path + "_0");
		BufferedImage img1 = loader.loadImage(path + "_1");

		animation = new Cycloid<BufferedImage>(img0, img1);
	}

	public BufferedImage getImage() {
		return animation.getState();
	}

	private BufferedImage disguise_image;

	public BufferedImage getDisguiseImage() {
		if (disguise_image == null)
			disguise_image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_disguise");
		return disguise_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		dragImage(g, getImage());

		if (isDisguised())
			dragImage(g, getDisguiseImage());
	}

	private void dragImage(Graphics g, BufferedImage image) {
		if(isReversed() == false)
			g.drawImage(image, getX() + shrink, getY() + shrink, getWidth() - 2 * shrink, getHeight() - 2 * shrink, null);
		else
			g.drawImage(image, getX() + shrink, getY() + getHeight() - shrink, getWidth() - 2 * shrink, - getHeight() + 2 * shrink, null);
	}

}
