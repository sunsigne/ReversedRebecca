package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.LevelCompletedScreen;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class EndLevelRequest implements Request {

	////////// REQUEST ////////////

	public EndLevelRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new EndLevelRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "END_LVL";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		World.get().freeze(true);
		
		// update save file
		new Save().registerSave();
		new Save().eraseSave(); // the name is alarming but it just erase some specific intended data

		// update level saved
		String lvlmenu = target.split(",")[1].toLowerCase();
		String lvl = target.split(",")[2].toLowerCase();
		new Save().registerNextLevel(lvlmenu, lvl);

		// load level completed screen
		String ending = target.split(",")[0];
		LAYER.MENU.addObject(new LevelCompletedScreen(ending, lvl));
	}

}
