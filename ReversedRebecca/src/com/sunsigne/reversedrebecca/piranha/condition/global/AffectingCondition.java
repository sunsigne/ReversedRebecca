package com.sunsigne.reversedrebecca.piranha.condition.global;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstruction;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class AffectingCondition extends GlobalInstruction {

	public void registerValue(String value) {
		String conditionType = "!->";

		analyse(conditionType + value);
	}

	public void registerValue(String name, String value) {
		String conditionType = "!->";

		String condition = conditionType + value;
		loadAllPiranha(name);
		createInstructionAnalyzerForAllObject(condition);
	}

	private void loadAllPiranha(String name) {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer() == false)
				return;

			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {

				if (tempUpdatable instanceof PiranhaObject == false)
					continue;

				PiranhaObject tempObject = (PiranhaObject) tempUpdatable;
				if (tempObject.getName().equalsIgnoreCase(name))
					getList().addObject(tempObject);
			}
		}
	}

}
