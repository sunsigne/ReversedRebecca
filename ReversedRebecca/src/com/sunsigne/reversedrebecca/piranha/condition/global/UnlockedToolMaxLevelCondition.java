package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class UnlockedToolMaxLevelCondition extends GlobalInstruction {
	
	////////// GLOBAL INSTRUCTION ////////////
	
	public UnlockedToolMaxLevelCondition() {
		GlobalInstructionList.getList().addObject(this);
	}
	
	private static GlobalInstruction globalInstruction = new UnlockedToolMaxLevelCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "UNLOCKEDTOOLMAXLEVEL->";
	}

	public void registerValue(ToolPlayer toolPlayer, LVL lvl) {
		// prevent the condition to occurs during map loading
		if (LAYER.LOADING.getHandler().getList().isEmpty() == false)
			return;

		String value = toolPlayer.getName() + ":" + lvl.getName();
		analyse(getConditionType() + value);
	}
	
	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}
	
}
