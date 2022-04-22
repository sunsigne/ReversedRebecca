package com.sunsigne.reversedrebecca.physic.laws.motion;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider.AVOIDERTYPE;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PlayerBlockingAvoiderLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object == null)
			return;

		if (object instanceof PlayerAvoider == false)
			return;

		PlayerAvoider avoider = (PlayerAvoider) object;

		if (avoider.getPlayerAvoiderType() != AVOIDERTYPE.AROUND)
			avoider.setPlayerBlockingAvoider(false);
		else
			avoider.setPlayerBlockingAvoider(isPlayerAtCriticalDistance(avoider));
	}

	private boolean isPlayerAtCriticalDistance(PlayerAvoider avoider) {

		boolean registeredBlocking = avoider.isPlayerBlockingAvoider();

		if (registeredBlocking == false)
			return new PlayerFinder().isPlayerFutherThan(avoider, 2) == false;

		else {
			return new PlayerFinder().isPlayerCloserThan(avoider, 5);
		}
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
