package com.sunsigne.reversedrebecca.system.controllers.mouse;

import com.sunsigne.reversedrebecca.pattern.GameTimer;

public class PresetMousePos {

	public PresetMousePos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int x, y;
	public static boolean usingPreset;
	
	public void moveMouse() {
		usingPreset = true;
		new GameTimer(5, () -> usingPreset = false);
		
		new MousePos().setX(x, true);
		new MousePos().setY(y, true);
	}

}
