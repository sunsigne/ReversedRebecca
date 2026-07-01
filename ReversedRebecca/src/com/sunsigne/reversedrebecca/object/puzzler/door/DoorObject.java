package com.sunsigne.reversedrebecca.object.puzzler.door;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class DoorObject extends PuzzlerObject {

	public DoorObject(DEV_LVL devDifficulty, COLOR color, int x, int y) {
		super(devDifficulty, x, y);
		this.color = color;
	}

	public DoorObject(LVL difficulty, COLOR color, int x, int y) {
		super(difficulty, x, y);
		this.color = color;
	}

	////////// NAME ////////////

	protected COLOR color;

	@Override
	public String getName() {
		return "door";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		switch (color) {
		case BLUE:
			return 1;
		case GREEN:
			return 2;
		case WHITE:
			return 3;
		case BROWN_SUGAR:
			return 4;
		case GRAY:
			return 5;
		case PURPLE:
			return 6;
		default:
			return 1;
		}
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			String number = isNumberSettings() ? "_number" : "";
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "door" + number);
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
		OpenPuzzleAction unlockAction = new UnlockDoorAction(this);
		OpenPuzzleAction explodeAction = new ExplodeDoorAction(this);

		RequirementBubbleObject requirementUnlock = new RequirementBubbleObject(getX(), getY(),
				unlockAction.getToolPlayer(), getDifficulty());

		tripleAction = new TripleAction(requirementUnlock, unlockAction, explodeAction, null);
	}

}
