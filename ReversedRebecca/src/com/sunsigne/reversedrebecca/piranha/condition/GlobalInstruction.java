package com.sunsigne.reversedrebecca.piranha.condition;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class GlobalInstruction extends LocalInstruction {

	protected void analyse(String condition) {
		loadAllPiranha();
		createInstructionAnalyzerForAllObject(condition);
	}

	protected void createInstructionAnalyzerForAllObject(String condition) {
		for (PiranhaObject tempObject : object_list.getList())
			analyse(tempObject, condition);
	}

	////////// MAP OR LIST ////////////

	private GameList<PiranhaObject> object_list = new GameList<PiranhaObject>(LISTTYPE.LINKED);

	protected GameList<PiranhaObject> getList() {
		return object_list;
	}
	
	private void loadAllPiranha() {
		for (LAYER tempLayer : LAYER.values()) {
			if (tempLayer.isMapLayer() == false)
				return;

			var list = tempLayer.getHandler().getList();

			for (Updatable tempUpdatable : list) {
				if (tempUpdatable instanceof PiranhaObject)
					object_list.addObject((PiranhaObject) tempUpdatable);
			}
		}
	}

}
