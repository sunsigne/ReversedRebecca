package com.sunsigne.reversedrebecca.object.puzzler.door;

import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

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
		return "door" + "_" + color;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("DoorLocked", FilePath.ACTION);
		Action unlockAction = new UnlockAction(this);
		tripleAction = new TripleAction(noActionText, unlockAction, null, null);
	}

}
