package com.sunsigne.reversedrebecca.object.puzzler.cowboy;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class CowboyObject extends PuzzlerObject {

	public CowboyObject(DEV_LVL devDifficulty, int x, int y) {
		super(devDifficulty, x, y);
	}

	public CowboyObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "cowboy";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 3;
	}

	@Override
	public int getSheetColCriterion() {
		return 1;
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
			highlightImage = getSheetSubImage(sheet, 1, 1 + getSheetRowCriterion(), getSheetWidth() + 2,
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
		OpenPuzzleAction confrontAction = new ConfrontAction(this);

		tripleAction = new TripleAction(null, confrontAction, null, null);
	}

}
