package com.sunsigne.reversedrebecca.object.interactive.puzzler;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.interactive.Action;
import com.sunsigne.reversedrebecca.object.interactive.TripleAction;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DoorObject extends PuzzlerObject {

	public DoorObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
		loadTripleAction();
		noToolText = new Translatable().getTranslatedText("LOCKED", "actions/door.csv", 1);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "door";
	}

	private String noToolText;

	@Override
	protected String getNoToolText() {
		return noToolText;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	private void loadTripleAction() {
		Action unlockAction = new UnlockAction(this);
		tripleAction = new TripleAction(unlockAction, null, null);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawTripleActionText(g);
	}

}
