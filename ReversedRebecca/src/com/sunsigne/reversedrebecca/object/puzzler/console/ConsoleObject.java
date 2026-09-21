package com.sunsigne.reversedrebecca.object.puzzler.console;

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
	public int getSheetRowCriterion() {
		return 1 + (getFacing() == DIRECTION.UP ? 0 : 1);
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
					getSheetHeight() + 2);
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

}
