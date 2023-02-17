package com.sunsigne.reversedrebecca.physic.natural.correlated;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class PlayerBlockingAvoiderLaw implements PhysicLaw {

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {
		if (object instanceof PlayerAvoider == false)
			return;

		PlayerAvoider avoider = (PlayerAvoider) object;

		switch (avoider.getPlayerAvoiderType()) {
		case AROUND:
			avoider.setPlayerBlockingAvoider(isPlayerAtCriticalDistance(avoider));
			break;
		case AROUND_OMNISCIENT:
			avoider.setPlayerBlockingAvoider(true);
			break;
		case CUTSCENE:
			avoider.setPlayerBlockingAvoider(new PlayerFinder().isPlayerCloserThan(avoider, 3));
			break;
		case FORCE:
		case PUSH:
		case PUSH_DOWN:
		case PUSH_HURT:
		case PUSH_LEFT:
		case PUSH_RIGHT:
		case PUSH_UP:
		case STOP:
		case THROUGH:
			avoider.setPlayerBlockingAvoider(false);
			break;

		}
	}

	private boolean isPlayerAtCriticalDistance(PlayerAvoider avoider) {

		boolean registeredBlocking = avoider.isPlayerBlockingAvoider();

		if (registeredBlocking == false)
			return new PlayerFinder().isPlayerFutherThan(avoider, 2) == false;

		else
			return new PlayerFinder().isPlayerCloserThan(avoider, 5);
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
