package com.sunsigne.reversedrebecca.instructions;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MassiveInstruction {

	protected MassiveInstruction(String value) {
		loadAllExtra();
		createInstructionAnalyzerForAllNPC(value);
	}

	private void createInstructionAnalyzerForAllNPC(String value) {
		for (ExtraBehaviorsObject tempObject : object_list.getList())
			new InstructionAnalyzer(tempObject, value);
	}

	////////// MAP OR LIST ////////////

	private GameList<ExtraBehaviorsObject> object_list = new GameList<ExtraBehaviorsObject>(LISTTYPE.LINKED);

	private void loadAllExtra() {
		for (LAYER tempLayer : LAYER.values()) {
			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {
				if (tempUpdatable instanceof ExtraBehaviorsObject)
					object_list.addObject((ExtraBehaviorsObject) tempUpdatable);
			}
		}
	}

}
