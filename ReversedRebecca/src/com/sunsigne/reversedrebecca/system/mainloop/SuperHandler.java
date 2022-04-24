package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class SuperHandler {

	////////// MAP OR LIST ////////////

	private static GameList<Handler> list = new GameList<>(LISTTYPE.ARRAY);

	protected static GameList<Handler> getList() {
		return list;
	}

	////////// TICK ////////////

	protected static void tick() {
		for (Handler tempObject : getList().getList())
			tempObject.tick();
	}

	////////// RENDER ////////////

	protected static void render(Graphics g) {
		for (Handler tempObject : getList().getList())
			tempObject.render(g);
	}
}
