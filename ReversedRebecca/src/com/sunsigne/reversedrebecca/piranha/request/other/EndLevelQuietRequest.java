package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.world.World;

public class EndLevelQuietRequest implements Request {

	////////// REQUEST ////////////

	public EndLevelQuietRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new EndLevelQuietRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "END_LVL_QUIET";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		World.get().freeze(true);
		LAYER.LOADING.addObject(new LoadingScreen(true));

		// update save file
		new Save().registerSave();
		new Save().eraseSave(); // the name is alarming but it just erase some specific intended data

		// update level saved
		String lvlmenu = target.split(",")[0].toLowerCase();
		String lvl = target.split(",")[1].toLowerCase();
		new Save().registerNextLevel(lvlmenu, lvl);

		// tutorial case
		if (lvlmenu.contains(FilePath.LVL000) && lvl.contains(FilePath.LVL000)) {
			new Save().resetProgression();
			World.get().destroy();
		}

		// load next level
		new World(lvl);
	}

}
