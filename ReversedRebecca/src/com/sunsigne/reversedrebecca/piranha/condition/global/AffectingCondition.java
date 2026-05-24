package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;

public class AffectingCondition extends GlobalInstruction {

	////////// GLOBAL INSTRUCTION ////////////

	public AffectingCondition() {
		GlobalInstructionList.getList().addObject(this);
	}

	private static GlobalInstruction globalInstruction = new AffectingCondition();

	@Override
	public GlobalInstruction getGlobalInstruction() {
		return globalInstruction;
	}

	@Override
	public String getConditionType() {
		return "!->";
	}

	public void registerValue(String value) {
		analyse(getConditionType() + value);
	}

	public void registerValue(String name, String value) {
		String condition = getConditionType() + value;
		GameList<PiranhaObject> list = getPiranhaByName(name);
		for (PiranhaObject tempObject : list.getList())
			analyse(tempObject, condition);
	}

	private GameList<PiranhaObject> getPiranhaByName(String name) {
		var list = new GameList<PiranhaObject>(LISTTYPE.ARRAY);

		// look for exact match
		for (PiranhaObject tempObject : getPiranhaList().getList())
			if (tempObject.getName().equalsIgnoreCase(name))
				list.addObject(tempObject);

		if (list.getList().isEmpty() == false)
			return list;

		// if not found, name close to (only for living)
		for (PiranhaObject tempObject : getPiranhaList().getList())
			if (tempObject.getName().contains(name) && tempObject instanceof LivingObject)
				list.addObject(tempObject);

		return list;
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
