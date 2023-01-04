package com.sunsigne.reversedrebecca.object.puzzle.dig.tool;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class DigShovelToolObject extends DigToolObject {

	public DigShovelToolObject(Puzzle puzzle, int x_pos_in_menu, int y_pos_in_menu, int w, int h, boolean selectable) {
		super(puzzle, x_pos_in_menu, y_pos_in_menu, w, h, selectable);
	}

	@Override
	public DIG_STATE getState() {
		return DIG_STATE.DIG;
	}

	////////// MOUSE ////////////

	@Override
	protected void pickupTool(DigPuzzle puzzle) {
		replaceHandByShovel(puzzle);
		super.pickupTool(puzzle);
	}

	private void replaceHandByShovel(DigPuzzle puzzle) {
		puzzle.setState(getState());

		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof DigHandToolObject == false)
				continue;

			LAYER.PUZZLE.getHandler().removeObject(tempUpdatable);
			return;
		}

	}

}
