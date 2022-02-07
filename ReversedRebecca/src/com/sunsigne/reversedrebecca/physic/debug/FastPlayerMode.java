package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
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
		return "fast_player_mode";
	}

	////////// TICK ////////////

	private int basic_player_speed = new Player(0, 0).speed; 
	
	@Override
	public void tick(Updatable object) {
		if (object instanceof Player == false)
			return;

		Player player = (Player) object;

		int modified_speed = getState() ? 3 * basic_player_speed : basic_player_speed;
		player.speed = modified_speed;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}
	
}
