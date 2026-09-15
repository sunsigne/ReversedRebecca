package com.sunsigne.reversedrebecca.object.puzzler.console;

public class NullConsoleObject extends ConsoleObject {

	public NullConsoleObject(int x, int y) {
		super(LVL.NULL, x, y);
		setDisabled(true);
	}

	////////// INTERACTION ////////////

	@Override
	protected void loadTripleAction() {

	}
	
}
