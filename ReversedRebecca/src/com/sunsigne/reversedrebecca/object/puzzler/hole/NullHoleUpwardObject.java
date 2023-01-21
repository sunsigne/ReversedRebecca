package com.sunsigne.reversedrebecca.object.puzzler.hole;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.piranha.living.player.GoUpAction;

public class NullHoleUpwardObject extends HoleUpwardObject {

	public NullHoleUpwardObject(DIRECTION facing, int x, int y) {
		super(LVL.NULL, facing, x, y);
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		Action goUpAction = new GoUpAction(this, "enter_hole");
		tripleAction = new TripleAction(null, goUpAction, null, null);
	}

}
