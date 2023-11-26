package com.sunsigne.reversedrebecca.piranha.condition.global;

import java.util.List;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

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
		loadAllPiranha(name);
		createInstructionAnalyzerForAllObject(condition);
	}

	private void loadAllPiranha(String name) {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer() == false)
				return;

			var list = tempLayer.getHandler().getList();

			loadNominativePiranha(list, name, true);

			if (list.isEmpty())
				loadNominativePiranha(list, name, false);
		}
	}

	private void loadNominativePiranha(List<Updatable> list, String name, boolean exactNameMatch) {
		for (Updatable tempUpdatable : list) {

			if (tempUpdatable instanceof PiranhaObject == false)
				continue;

			PiranhaObject tempObject = (PiranhaObject) tempUpdatable;

			if (exactNameMatch) {
				if (tempObject.getName().equalsIgnoreCase(name))
					getList().addObject(tempObject);
			} else {
				if (tempObject.getName().contains(name))
					getList().addObject(tempObject);
			}
		}
	}

	////////// OPTIMIZATION ////////////

	private static GameList<PiranhaObject> exceptions = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public GameList<PiranhaObject> getExceptionsList() {
		return exceptions;
	}

}
