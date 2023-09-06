package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.loot.LootObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;
import com.sunsigne.reversedrebecca.system.Size;

public class PickupLootCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public PickupLootCondition() {
		GlobalInstructionList.getList().addObject(this);
	}

	private static GlobalInstruction globalInstruction = new PickupLootCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "PICKUPLOOT->";
	}

	public void registerValue(LootObject lootObject) {
		int gap = Size.XS / 2;
		GoalObject lootPos = new GoalObject(lootObject.getX() - gap, lootObject.getY() - gap, true);
		String value = lootPos.getX() + "-" + lootPos.getY();

		analyse(getConditionType() + value);
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
