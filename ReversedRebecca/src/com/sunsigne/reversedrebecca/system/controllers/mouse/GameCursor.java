package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.Point;

public class GameCursor {

	private static Point pos;

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		GameCursor.pos = pos;
	}

}
