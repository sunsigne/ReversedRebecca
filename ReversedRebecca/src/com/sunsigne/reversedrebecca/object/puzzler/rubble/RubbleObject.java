package com.sunsigne.reversedrebecca.object.puzzler.rubble;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class RubbleObject extends PuzzlerObject {

	public RubbleObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
		loadTripleAction();
		createTextAction();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "rubble";
	}

	////////// INTERACTION ////////////

	protected TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("RubbleBlocked", getFile());
		Action explodeAction = new ExplodeRubbleAction(this);
		tripleAction = new TripleAction(noActionText, explodeAction, null, null);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth(), getY() - getHeight(), 3 * getWidth(), 3 * getHeight(), null);
	}

}
