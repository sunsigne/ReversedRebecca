package com.sunsigne.reversedrebecca.object.puzzler.chest;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.world.World;

public class ChestObject extends PuzzlerObject {

	public ChestObject(int num, int x, int y) {
		this(LVL.CYAN, num, x, y);
	}

	protected ChestObject(LVL lvl, int num, int x, int y) {
		super(lvl, x, y);

		if (World.get() != null)
			lootFile = ("maps/" + World.get().getMapName() + "/" + getName().toUpperCase() + "-0" + num + ".csv");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "chest";
	}

	////////// LOOTFILE ////////////

	private String lootFile;

	public String getLootFile() {
		return lootFile;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}
	
	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction openAction = new OpenAction(this);

		tripleAction = new TripleAction(null, openAction, null, null);
	}

}