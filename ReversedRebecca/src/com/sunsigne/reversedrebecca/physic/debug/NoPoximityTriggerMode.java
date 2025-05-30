package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class NoPoximityTriggerMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new NoPoximityTriggerMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 8;
	}

	@Override
	public String getName() {
		return "debugmode_no_poximity_trigger";
	}

	////////// TICK ////////////

	public static boolean isActive() {
		return debugMode.getState();
	}

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
