package com.sunsigne.reversedrebecca.object.puzzler.dancefloor;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class DanceFloorObject extends PuzzlerObject {

	public DanceFloorObject(DEV_LVL devDifficulty, int x, int y) {
		super(devDifficulty, x, y);
	}

	public DanceFloorObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "dancefloor";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler" + "_" + "highlight");
			highlightImage = getSheetSubImage(sheet, 1, 1, getSheetWidth() + 2, getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction danceAction = new DanceAction(this);

		tripleAction = new TripleAction(null, danceAction, null, null);
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

}
