package com.sunsigne.reversedrebecca.object.puzzler.rubble.dev;

import com.sunsigne.reversedrebecca.object.characteristics.DevDifficulty;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.rubble.RubbleObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DevRubbleObject extends RubbleObject implements DevDifficulty {

	public DevRubbleObject(DEV_LVL devDifficulty, int x, int y) {
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
		String noActionText = new Translatable().getTranslatedText("RubbleBlocked", getFile());
		Action explodeAction = new DevExplodeRubbleAction(this);
		tripleAction = new TripleAction(noActionText, explodeAction, null, null);
	}

}
