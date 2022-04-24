package com.sunsigne.reversedrebecca.physic.natural.independant;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class PlayerFinderLaw implements PhysicLaw {

	private static Player player;

	public static Player getPlayer() {
		return player;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof World) // to happens ONCE a tick
			return;

		if (object instanceof Player)
			player = (Player) object;
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
