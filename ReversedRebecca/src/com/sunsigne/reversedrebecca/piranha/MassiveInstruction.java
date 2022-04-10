package com.sunsigne.reversedrebecca.piranha;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MassiveInstruction extends InstructionAnalyzer {

	protected void analyse(String condition) {
		loadAllExtra();
		createInstructionAnalyzerForAllObject(condition);
	}

	private void createInstructionAnalyzerForAllObject(String condition) {
		for (ExtraBehaviorsObject tempObject : object_list.getList())
			analyse(tempObject, condition);
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
