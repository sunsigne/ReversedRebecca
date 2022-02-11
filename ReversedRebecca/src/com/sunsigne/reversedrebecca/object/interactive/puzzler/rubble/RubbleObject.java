package com.sunsigne.reversedrebecca.object.interactive.puzzler.rubble;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.interactive.Action;
import com.sunsigne.reversedrebecca.object.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.interactive.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class RubbleObject extends PuzzlerObject {

	public RubbleObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
		loadTripleAction();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "rubble";
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	private void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("RubbleBlocked", file);
		Action explodeAction = new ExplodeRubbleAction(this);
		tripleAction = new TripleAction(noActionText, explodeAction, null, null);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() - getWidth(), getY() - getHeight(), 3 * getWidth(), 3 * getHeight(), null);
	}

}
