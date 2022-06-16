package com.sunsigne.reversedrebecca.object.puzzler.hole;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class HoleObject extends PuzzlerObject implements Facing {

	public HoleObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, x, y);
		this.facing = facing;
	}

	public HoleObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, x, y);
		this.facing = facing;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return getFacing().getName() + "_" + "hole";
	}

	////////// FACING ////////////

	private DIRECTION facing;

	public DIRECTION getFacing() {
		return facing;
	}

	public void setFacing(DIRECTION facing) {
		this.facing = facing;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		String noActionText = new Translatable().getTranslatedText("HoleLoose", getFile());
		Action digAction = new DigAction(this, getFacing());
		tripleAction = new TripleAction(noActionText, digAction, null, null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

}
