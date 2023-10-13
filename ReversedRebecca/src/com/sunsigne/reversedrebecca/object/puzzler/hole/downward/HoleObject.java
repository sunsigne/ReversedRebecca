package com.sunsigne.reversedrebecca.object.puzzler.hole.downward;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.object.puzzler.hole.DigAction;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class HoleObject extends PuzzlerObject implements Facing {

	public HoleObject(DEV_LVL devDifficulty, DIRECTION facing, int x, int y) {
		super(devDifficulty, x, y);
		this.facing = facing;
	}

	public HoleObject(LVL difficulty, DIRECTION facing, int x, int y) {
		super(difficulty, x, y);
		this.facing = facing;
	}

	public LAYER getExitLayer(Handler currentHandler) {
		LAYER exit_layer = LAYER.WORLD_CONTENT;

		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.getName().contains("content") == false)
				continue;

			if (currentHandler != tempLayer.getHandler())
				exit_layer = tempLayer;

			else
				break;
		}

		return exit_layer;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "hole" + "_" + getFacing().getName();
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

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction digAction = new DigAction(this);

		RequirementBubbleObject requirementDig = new RequirementBubbleObject(getX(), getY(), digAction.getToolPlayer(),
				getDifficulty());

		tripleAction = new TripleAction(requirementDig, digAction, null, null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingPath() {
		return false;
	}

}
