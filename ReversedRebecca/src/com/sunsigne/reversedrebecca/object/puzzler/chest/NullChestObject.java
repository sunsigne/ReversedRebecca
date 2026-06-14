package com.sunsigne.reversedrebecca.object.puzzler.chest;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;

public class NullChestObject extends ChestObject {

	public NullChestObject(int x, int y, boolean little) {
		super(LVL.NULL, 0, x, y, little);
	}

	////////// INTERACTION ////////////

	@Override
	public TripleAction getTripleAction() {
		return null;
	}

	@Override
	protected void loadTripleAction() {

	}

}