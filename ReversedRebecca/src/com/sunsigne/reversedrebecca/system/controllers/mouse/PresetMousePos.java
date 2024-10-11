package com.sunsigne.reversedrebecca.system.controllers.mouse;

import com.sunsigne.reversedrebecca.pattern.GameTimer;

public class PresetMousePos {

	public PresetMousePos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int x, y;
	public static boolean usingPreset;
	private static GameTimer timer;

	public void moveMouse() {
		usingPreset = true;

		if (timer != null)
			timer.destroy();
		timer = new GameTimer(5, true, () -> usingPreset = false);

		MousePos mousePos = new MousePos();
		mousePos.setX(x, true);
		mousePos.setY(y, true);
	}

}
