package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.Piranha;

public class GlobalInstructionList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<GlobalInstruction> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<GlobalInstruction> getList() {
		return list;
	}

	////////// OPTIMIZATION ////////////

	public void optimize(GameList<PiranhaObject> allPiranhaObjects) {
		for (GlobalInstruction tempInstruction : list.getList()) {
			tempInstruction.resetExceptions();
		}

		for (PiranhaObject tempObject : allPiranhaObjects.getList()) {
			String content = new Piranha().getContent(tempObject);
			if (content == null)
				continue;

			for (GlobalInstruction tempInstruction : list.getList()) {
				tempInstruction.optimize(tempObject, content);
			}
		}
	}

}
