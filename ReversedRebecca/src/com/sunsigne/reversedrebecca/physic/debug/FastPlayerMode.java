package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.SpeedVariator.SPEEDTYPE;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.players.Player;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class FastPlayerMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new FastPlayerMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
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
			player.setSpeed(SPEEDTYPE.FAST);
		else
			player.setSpeed(SPEEDTYPE.NORMAL);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
