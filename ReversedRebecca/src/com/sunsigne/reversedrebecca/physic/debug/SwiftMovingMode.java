package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class SwiftMovingMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new SwiftMovingMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public int getNum() {
		return 3;
	}

	@Override
	public String getName() {
		return "debugmode_swift_moving";
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (getState() == false)
			return;

		if (object instanceof SpeedVariator == false)
			return;

		if (object instanceof Player)
			((Player) object).setSpeedness(SPEEDNESS.SWIFT);

		else
			((SpeedVariator) object).setSpeedness(SPEEDNESS.FAST);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
