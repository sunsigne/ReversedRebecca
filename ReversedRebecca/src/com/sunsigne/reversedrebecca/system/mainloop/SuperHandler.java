package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class SuperHandler {

	////////// MAP OR LIST ////////////

	private static GameList<Handler> super_handler_gameList = new GameList<>(LISTTYPE.ARRAY);

	protected static GameList<Handler> getGameList() {
		return super_handler_gameList;
	}

	////////// TICK ////////////

	protected static void tick() {
		for (Handler tempObject : getGameList().getList())
			tempObject.tick();
	}

	////////// RENDER ////////////

	protected static void render(Graphics g) {
		for (Handler tempObject : getGameList().getList())
			tempObject.render(g);
	}
}
