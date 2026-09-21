package com.sunsigne.reversedrebecca.object.puzzler.console;

public class NullConsoleObject extends ConsoleObject {

	public NullConsoleObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
		setDisabled(true);
	}

	////////// INTERACTION ////////////

	@Override
	protected void loadTripleAction() {

	}
	
}
