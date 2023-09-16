package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class UnlockedToolCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public UnlockedToolCondition() {
		GlobalInstructionList.getList().addObject(this);
	}

	private static GlobalInstruction globalInstruction = new UnlockedToolCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "UNLOCKEDTOOL->";
	}

	public void registerValue(ToolPlayer toolPlayer, LVL lvl) {
		if (mapLoadingPrevention(false))
			return;

		String value = toolPlayer.getName() + ":" + lvl.getName();
		analyse(getConditionType() + value);
	}

	private boolean mapLoadingPrevention(boolean activated) {
		return activated && LAYER.LOADING.getHandler().getList().isEmpty() == false;
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
