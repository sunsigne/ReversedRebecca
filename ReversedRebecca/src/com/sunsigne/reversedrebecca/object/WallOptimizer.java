package com.sunsigne.reversedrebecca.object;

import com.sunsigne.reversedrebecca.object.characteristics.Velocity;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;

public interface WallOptimizer extends Velocity {

	////////// WALL OPTIMIZER ////////////

	boolean isPlayerTooFar();

	void setPlayerTooFar(boolean playerTooFar);

	////////// PHYSICS ////////////

	@Override
	public default PhysicLaw[] getPhysicLinker() {
		if (isPlayerTooFar())
			return PhysicLinker.OPTIMIZED_WALL;
		else
			return PhysicLinker.COLLISIONNER;
	}

	////////// TICK ////////////

	@Override
	public default void tick() {
		boolean playerTooFar = new PlayerFinder().isPlayerFutherThan(this, 3);
		setPlayerTooFar(playerTooFar);
	}

}
