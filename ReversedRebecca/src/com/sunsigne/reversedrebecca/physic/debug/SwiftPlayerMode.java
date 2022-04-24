package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class SwiftPlayerMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new SwiftPlayerMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debugmode_swift_player";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState() == false)
			return;
		
		if (object instanceof Player == false)
			return;

		Player player = (Player) object;
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
