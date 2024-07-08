package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.cycloid.Cycloid;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class VirusObject extends PuzzleObject implements SheetableImage, MouseObject {

	public VirusObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0, Size.M, Size.M);
		loadAnimation();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : VIRUS";
		String critical = isCritical() ? " CRITICAL" : "";
		var pos = getRow(Size.S + getX() / 2) + "-" + getCol(Size.S + getY() / 2);
		return clazz + critical + " : " + pos + " / " + "REVERSED : " + reversed + " / " + "DISGUISED : " + disguised
				+ " / " + "SHRINK : " + shrink;
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
		if (isReversed() != reversed)
			updateMouse();

		this.reversed = reversed;
	}

	protected void updateMouse() {
		int mouseX = new MousePos().get()[0] - getWidth();
		int mouseY = new MousePos().get()[1] - getHeight();
		mouseX = xmax + xmin - mouseX;
		mouseY = ymax + ymin - mouseY;
		new MousePos().setX(mouseX);
		new MousePos().setY(mouseY);
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

		int w = getWidth() / 2;
		int h = getHeight() / 2;
		keepMouseWithinZone(mouseX + w, mouseY + h, xmin + w, xmax + w, ymin + h, ymax + h);

		if (isReversed()) {
			mouseX = xmax + xmin - mouseX;
			mouseY = ymax + ymin - mouseY;
		}

		followMouse(mouseX, mouseY);
		keepWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);

		// if Mouse Peripheral has been destroyed
		if (((HackPuzzle) getPuzzle()).getComputer().hasMouse() == false)
			erraticMovements();

		runAnimation();
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

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return isCritical() ? 2 : 1;
	}

	private void loadAnimation() {
		BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_virus");
		BufferedImage img0 = getSheetSubImage(sheet, 1);
		BufferedImage img1 = getSheetSubImage(sheet, 2);

		animation = new Cycloid<BufferedImage>(img0, img1);
	}

	public BufferedImage getImage() {
		return animation.getState();
	}

	private BufferedImage disguise_image;

	public BufferedImage getDisguiseImage() {
		if (disguise_image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "hack_addon");
			disguise_image = getSheetSubImage(sheet, 2, 1, 48, 48);
		}
		return disguise_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		dragImage(g, getImage());

		if (isDisguised())
			dragImage(g, getDisguiseImage());
	}

	protected void dragImage(Graphics g, BufferedImage image) {
		int x = getX() + shrink;
		int y = getY() + shrink;
		int w = getWidth() - 2 * (shrink);
		int h = getHeight() - 2 * (shrink);

		if (isReversed() == false)
			g.drawImage(image, x, y, w, h, null);
		else
			g.drawImage(image, x, y + h, w, -h, null);
	}

}
