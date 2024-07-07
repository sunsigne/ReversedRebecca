package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class RubbleObject extends PuzzlerObject {

	public RubbleObject(DEV_LVL devDifficulty, COLOR color, boolean horizontal, int x, int y) {
		super(devDifficulty, x, y);
		this.horizontal = horizontal;
		this.color = color;
	}

	public RubbleObject(LVL difficulty, COLOR color, boolean horizontal, int x, int y) {
		super(difficulty, x, y);
		this.horizontal = horizontal;
		this.color = color;
	}

	////////// NAME ////////////

	private boolean horizontal;
	private COLOR color;

	@Override
	public String getName() {
		return "rubble";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		int col = horizontal ? 0 : 7;
		return col + super.getSheetColCriterion();
	}

	@Override
	public int getSheetRowCriterion() {
		switch (color) {
		case BLUE:
			return 1;
		case GREEN:
			return 2;
		case WHITE:
			return 3;
		case BROWN:
			return 4;
		case BROWN_SUGAR:
			return 5;
		default:
			return 1;
		}
	}

	@Override
	public int getSheetSize() {
		return 3 * 16;
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "rubble");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "rubble" + "_" + "highlight");
			int col = horizontal ? 0 : 1;
			highlightImage = getSheetSubImage(sheet, 1 + col, getSheetRowCriterion(), getSheetWidth() + 2,
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

	protected void loadTripleAction() {
		OpenPuzzleAction explodeAction = new ExplodeRubbleAction(this);

		RequirementBubbleObject requirementExplode = new RequirementBubbleObject(getX(), getY(),
				explodeAction.getToolPlayer(), getDifficulty());

		tripleAction = new TripleAction(requirementExplode, explodeAction, null, null);
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean isHighlightAbovePlayer() {
		return false;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth(), getY() - getHeight(), 3 * getWidth(), 3 * getHeight(), null);
		drawHighlight(g, getHighlightImage(), -getWidth(), -getHeight(), 2 * getWidth(), 2 * getHeight());
	}

}
