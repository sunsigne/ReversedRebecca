package com.sunsigne.reversedrebecca.piranha.condition;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class GlobalInstructionList {

	////////// MAP OR LIST ////////////

	private static GameLimitedList<GlobalInstruction> list = new GameLimitedList<>(LISTTYPE.ARRAY);

	public static GameLimitedList<GlobalInstruction> getList() {
		return list;
	}

	////////// OPTIMIZATION ////////////

	private boolean userData = false;

	public void optimize() {
		for (GlobalInstruction tempInstruction : list.getList()) {
			tempInstruction.resetExceptions();
		}

		GameList<PiranhaObject> allObject = getAllPiranhaObjects();

		for (PiranhaObject tempObject : allObject.getList()) {
			String content = getContent(tempObject);
			if (content == null)
				continue;

			for (GlobalInstruction tempInstruction : list.getList()) {
				tempInstruction.optimize(tempObject, content);
			}
		}
	}

	private String getContent(PiranhaObject object) {
		if (new FileTask().doesExist(userData, object.getPiranhaFile()) == false)
			return null;

		String content = new FileTask().read(userData, object.getPiranhaFile());
		return content.toUpperCase();
	}

	private GameList<PiranhaObject> getAllPiranhaObjects() {
		var list = new GameList<PiranhaObject>(LISTTYPE.ARRAY);

		try {
			for (LAYER tempLayer : LAYER.values()) {
	
				Handler handler = tempLayer.getHandler();
	
				
					for (Updatable tempUpdatable : handler.getList()) {
		
						if (tempUpdatable instanceof PiranhaObject)
							list.addObject((PiranhaObject) tempUpdatable);
		
						try {
							handler.getList().iterator().next();
						} catch (NoSuchElementException e) {
							break;
						}
					}			
				}
		} catch (ConcurrentModificationException e) {
			// unlikly to do infinite loop, as "concurrent modifier" will eventually stop modifying
			list = getAllPiranhaObjects();
		}

		return list;
	}

}
