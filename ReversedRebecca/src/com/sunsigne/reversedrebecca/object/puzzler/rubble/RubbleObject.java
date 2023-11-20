package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;

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
		return "rubble" + "_" + color.getName() + "_" + Facing.getAxisName(horizontal);
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

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth(), getY() - getHeight(), 3 * getWidth(), 3 * getHeight(), null);
		drawHighlight(g, getHighlightImage());
	}

	@Override
	public void drawHighlight(Graphics g, BufferedImage image) {
		if (getHighlightCondition() == false)
			return;

		int pixel = getHighlightSize();
		g.drawImage(image, getX() - getWidth() - pixel, getY() - getHeight() - pixel, 3 * getWidth() + 2 * pixel,
				3 * getHeight() + 2 * pixel, null);
	}

}
