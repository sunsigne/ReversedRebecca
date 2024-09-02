package com.sunsigne.reversedrebecca.piranha;

import java.util.ConcurrentModificationException;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.ForceInit;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.pattern.list.ListCloner;
import com.sunsigne.reversedrebecca.piranha.condition.GlobalInstructionList;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class Piranha {

	public void loadRessources() {
		new ForceInit().loadAllClassesInPackage(Piranha.class.getPackageName());
	}

	////////// USEFULL ////////////

	private boolean userData = false;

	public GameList<PiranhaObject> getAllPiranhaObjects() {
		var list = new GameList<PiranhaObject>(LISTTYPE.ARRAY);

		try {
			for (LAYER tempLayer : LAYER.values()) {

				var handler_list = new ListCloner().deepClone(tempLayer.getHandler());

				for (Updatable tempUpdatable : handler_list.getList()) {

					if (tempUpdatable instanceof PiranhaObject)
						list.addObject((PiranhaObject) tempUpdatable);
				}
			}
		} catch (ConcurrentModificationException e) {
			// unlikly to do infinite loop, as "concurrent modifier" will eventually stop modifying
			list = getAllPiranhaObjects();
		}

		return list;
	}

	public String getContent(PiranhaObject object) {
		if (new FileTask().doesExist(userData, object.getPiranhaFile()) == false)
			return null;

		String content = new FileTask().read(userData, object.getPiranhaFile());
		return content.toUpperCase();
	}

	////////// OPTIMIZATION ////////////

	public void optimize() {
		GameList<PiranhaObject> allPiranhaObjects = getAllPiranhaObjects();

		new GlobalInstructionList().optimize(allPiranhaObjects);

		for (PiranhaObject tempObject : allPiranhaObjects.getList()) {
			tempObject.optimize();
		}
	}

}
