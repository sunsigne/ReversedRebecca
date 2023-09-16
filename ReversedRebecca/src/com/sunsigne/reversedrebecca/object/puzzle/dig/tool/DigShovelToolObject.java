package com.sunsigne.reversedrebecca.object.puzzle.dig.tool;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
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
	protected void pickupTool() {
		replaceHandByShovel();		
		super.pickupTool();
	}

	private void replaceHandByShovel() {
		getPuzzle().setState(getState());
		getPuzzle().tool_list.removeObject(getPuzzle().tool_list.getList().get(0));
		
		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof DigHandToolObject == false)
				continue;

			LAYER.PUZZLE.getHandler().removeObject(tempUpdatable);
			return;
		}
	}

}
