package com.sunsigne.reversedrebecca.object.puzzler.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.world.World;

public class ChestObject extends PuzzlerObject {

	public ChestObject(LVL lvl, int num, int x, int y, boolean little) {
		super(lvl, x, y);

		this.little = little;

		if (num < 0)
			return;

		if (World.get() != null)
			lootFile = ("maps/" + World.get().getMapName() + "/" + getName().toUpperCase() + "-0" + num + ".csv");
	}

	private boolean little;

	public boolean isLittle() {
		return little;
	}
	
	////////// NAME ////////////

	@Override
	public String getName() {
		return "chest";
	}

	////////// LOOTFILE ////////////

	private String lootFile;

	public String getLootFile() {
		return lootFile;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return little ? 4 : 2;
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
			highlightImage = getSheetSubImage(sheet, 1, 1 + getSheetRowCriterion(), getSheetWidth() + 2,
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