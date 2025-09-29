package com.sunsigne.reversedrebecca.object.puzzler.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class FreeChestObject extends ChestObject {

	public FreeChestObject(int num, int x, int y, boolean little) {
		super(LVL.CYAN, num, x, y, little);
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return super.getSheetRowCriterion() - 1;
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "chest");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler" + "_" + "highlight");
			int col = isLittle() ? 1 : 0;
			highlightImage = getSheetSubImage(sheet, 1 + col, 2, getSheetWidth() + 2,
					getSheetHeight() + 2);
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
		OpenPuzzleAction openAction = new OpenAction(this);

		tripleAction = new TripleAction(null, openAction, null, null);
	}

}