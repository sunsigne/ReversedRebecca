package com.sunsigne.reversedrebecca.object.puzzler.computer;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;

public class ComputerObject extends PuzzlerObject {

	public ComputerObject(DEV_LVL devDifficulty, int x, int y) {
		super(devDifficulty, x, y);
	}

	public ComputerObject(LVL difficulty, int x, int y) {
		super(difficulty, x, y);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "computer";
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}
	
	////////// INTERACTION ////////////

	protected TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	protected void loadTripleAction() {
		OpenPuzzleAction hackingAction = new HackingAction(this);

		RequirementBubbleObject requirementHacking = new RequirementBubbleObject(getX(), getY(),
				hackingAction.getToolPlayer(), getDifficulty());

		tripleAction = new TripleAction(requirementHacking, hackingAction, null, null);
	}

}
