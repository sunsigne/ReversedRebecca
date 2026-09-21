package com.sunsigne.reversedrebecca.object.puzzler.console;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class ConsoleObject extends PuzzlerObject implements Facing {

	public ConsoleObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, x, y);
		this.facing = facing;
	}

	public ConsoleObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, x, y);
		this.facing = facing;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "console";
	}

	////////// SIZE ////////////

	@Override
	public int getHeight() {
		return getFacing() == DIRECTION.UP ? 2 * super.getHeight() : super.getHeight();
	}
	////////// FACING ////////////

	private DIRECTION facing;

	public DIRECTION getFacing() {
		return facing;
	}

	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetHeight() {
		return getFacing() == DIRECTION.UP ? 2 * super.getSheetHeight() : super.getSheetHeight();
	}

	@Override
	public int getSheetRowCriterion() {
		return 1 + (getFacing() == DIRECTION.UP ? 1 : 0);
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "console");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler" + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, getSheetRowCriterion(), 4, getSheetWidth() + 2,
					super.getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// INTERACTION ////////////

	protected TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction playAction = new PlayAction(this);
		tripleAction = new TripleAction(null, playAction, null, null);
	}

	////////// RENDER ////////////

	private int gap = 18;

	@Override
	public void drawHighlight(Graphics g, BufferedImage image) {
		if (getFacing() != DIRECTION.UP) {
			super.drawHighlight(g, image);
			return;
		}

		int h = -98;
		drawHighlight(g, image, 0, -h - gap - 2, 0, h);
	}

	////////// COLLISION ////////////

	@Override
	public Rectangle getBounds() {
		if (getFacing() != DIRECTION.UP)
			return super.getBounds();

		int x = getX();
		int y = getY() + super.getHeight() - gap;
		int w = getWidth();
		int h = super.getHeight();
		return new Rectangle(x, y, w, h);
	}

}
