package com.sunsigne.reversedrebecca.object.puzzler.hole;

public class NullHoleObject extends HoleObject {

	public NullHoleObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
	}

	////////// INTERACTION ////////////

	@Override
	protected void loadTripleAction() {
//		String noActionText = new Translatable().getTranslatedText("DoorLocked", getFile());
//		Action unlockAction = new UnlockAction(this);
//		tripleAction = new TripleAction(noActionText, unlockAction, null, null);
	}

}
