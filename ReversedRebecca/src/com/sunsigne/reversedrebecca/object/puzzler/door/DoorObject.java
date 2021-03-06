package com.sunsigne.reversedrebecca.object.puzzler.door;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DoorObject extends PuzzlerObject {

	public DoorObject(DEV_LVL devDifficulty, int x, int y) {
		super(devDifficulty, x, y);
	}

	public DoorObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
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

	@Override
	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("DoorLocked", getFile());
		Action unlockAction = new UnlockAction(this);
		tripleAction = new TripleAction(noActionText, unlockAction, null, null);
	}

}
