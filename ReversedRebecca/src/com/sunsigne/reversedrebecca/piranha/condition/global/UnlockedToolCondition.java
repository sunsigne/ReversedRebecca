package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class UnlockedToolCondition extends GlobalInstruction {

	public void registerValue(ToolPlayer toolPlayer, LVL lvl) {
		// prevent the condition to occurs during map loading
		if (LAYER.LOADING.getHandler().getList().isEmpty() == false)
			return;

		String conditionType = "UNLOCKEDTOOL->";
		String value = toolPlayer.getName() + ":" + lvl.getName();
		analyse(conditionType + value);
	}

}
