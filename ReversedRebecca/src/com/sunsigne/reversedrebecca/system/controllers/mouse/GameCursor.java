package com.sunsigne.reversedrebecca.system.controllers.mouse;

import java.awt.MouseInfo;
import java.awt.Point;

import com.sunsigne.reversedrebecca.system.main.ITick;

public class GameCursor implements ITick {

	////////// POSITION ////////////

	private Point pos;

	public Point getPos() {
		return pos;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		pos = MouseInfo.getPointerInfo().getLocation();
	}

}
