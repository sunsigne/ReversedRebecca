package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.menu.DemoEndScreen;
import com.sunsigne.reversedrebecca.menu.LevelCompletedScreen;
import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.world.World;

public class EndLevelRequest implements Request {

	////////// REQUEST ////////////

	public EndLevelRequest() {
		new RequestList().addRequest(this, getType());
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

		String endType = target.split(",")[0].toUpperCase();

		// starting game case
		if (endType.equalsIgnoreCase("START_GAME")) {
			LAYER.LOADING.addObject(new LoadingScreen(true));
			World.get().destroy();
			new Conductor().startGame();
			return;
		}

		// update level saved
		String lvlmenu = target.split(",")[1].toLowerCase();
		String lvl = target.split(",")[2].toLowerCase();
		new Save().registerNextLevel(lvlmenu, lvl);

		switch (endType) {

		case "NORMAL":
			LAYER.MENU.addObject(new LevelCompletedScreen(lvl));
			break;

		case "QUIET":
			LAYER.LOADING.addObject(new LoadingScreen(true));
			quiteEndLevel(lvlmenu, lvl);
			break;

		case "END_GAME":
			World.get().destroy();
			LAYER.MENU.addObject(new DemoEndScreen());
			LAYER.LOADING.getHandler().clear();
			break;
		}
	}

	private void quiteEndLevel(String lvlmenu, String lvl) {
		// tutorial case
		if (lvlmenu.contains(FilePath.LVL000) && lvl.contains(FilePath.LVL000)) {
			new Save().resetProgression();
			World.get().destroy();
		}

		// load next level
		new World(lvl);
	}

}
