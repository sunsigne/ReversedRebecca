package com.sunsigne.reversedrebecca.object.puzzler.door;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DoorObject extends PuzzlerObject {

	public DoorObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
		loadTripleAction();
		createTextAction();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "door";
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	private void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("DoorLocked", getFile());
		Action unlockAction = new UnlockAction(this);
		tripleAction = new TripleAction(noActionText, unlockAction, null, null);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

}
