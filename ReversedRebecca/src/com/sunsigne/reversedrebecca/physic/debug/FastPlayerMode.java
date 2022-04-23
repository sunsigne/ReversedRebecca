package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FastPlayerMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new FastPlayerMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debugmode_fast_player";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof Player == false)
			return;

		Player player = (Player) object;

		if (getState())
			player.setSpeedness(SPEEDNESS.SWIFT);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
