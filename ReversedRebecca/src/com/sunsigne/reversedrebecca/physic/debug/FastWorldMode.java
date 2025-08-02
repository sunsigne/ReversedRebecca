package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzler.door.NullDoorObject;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FastWorldMode extends DebugMode {

	////////// DEBUG MODE ////////////

	public static final DebugMode debugMode = new FastWorldMode();

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

		if (isSpecialCase(object))
			return;

		object.tick();
		object.tick();
	}

	private boolean isSpecialCase(Updatable object) {
		if (object instanceof NullDoorObject)
			return true;

		if (object instanceof Player == false)
			return false;

		// makes SwiftMovingMode and FastWorldMode compatible for player movement
		return SwiftMovingMode.debugMode.getDebugMode().getState();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
