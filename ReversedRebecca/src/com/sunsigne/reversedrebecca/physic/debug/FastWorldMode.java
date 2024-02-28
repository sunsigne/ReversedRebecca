package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzler.door.NullDoorObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FastWorldMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new FastWorldMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 4;
	}

	@Override
	public String getName() {
		return "debugmode_fast_world";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState() == false)
			return;

		if (object instanceof NullDoorObject)
			return;

		object.tick();
		object.tick();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
