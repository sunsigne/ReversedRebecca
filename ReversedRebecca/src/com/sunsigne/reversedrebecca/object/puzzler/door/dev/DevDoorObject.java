package com.sunsigne.reversedrebecca.object.puzzler.door.dev;

import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DevDoorObject extends DoorObject implements DevDifficulty {

	public DevDoorObject(DEV_LVL devDifficulty, int x, int y) {
		super(devDifficulty.isEasy() ? LVL.CYAN : LVL.RED, x, y);
		this.devDifficulty = devDifficulty;
		loadTripleAction();
		createTextAction();
	}

	////////// DEV DIFFICULTY ////////////

	private DEV_LVL devDifficulty;

	@Override
	public DEV_LVL getDevDifficulty() {
		return devDifficulty;
	}

	////////// INTERACTION ////////////

	@Override
	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("DoorLocked", getFile());
		Action unlockAction = new DevUnlockAction(this);
		tripleAction = new TripleAction(noActionText, unlockAction, null, null);
	}

}
