package com.sunsigne.reversedrebecca.object.puzzler.chest;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;

public class NullChestObject extends ChestObject {

	public NullChestObject(int x, int y) {
		super(LVL.NULL, 0, x, y);
	}

	////////// INTERACTION ////////////

	@Override
	public TripleAction getTripleAction() {
		return null;
	}

}