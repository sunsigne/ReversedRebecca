package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class PiranhaNPC extends LivingObject implements PlayerAvoider {

	public PiranhaNPC(String name, int x, int y) {
		super(name, x, y);
		setPlayerAvoiderType(AVOIDERTYPE.STOP);
	}

	////////// PLAYER AVOIDER ////////////

	private boolean playerBlockingAvoider;

	@Override
	public boolean isPlayerBlockingAvoider() {
		return playerBlockingAvoider;
	}

	@Override
	public void setPlayerBlockingAvoider(boolean playerBlockingAvoider) {
		this.playerBlockingAvoider = playerBlockingAvoider;
	}

	private AVOIDERTYPE avoiderType;

	@Override
	public AVOIDERTYPE getPlayerAvoiderType() {
		return avoiderType;
	}

	@Override
	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {
		this.avoiderType = playerAvoiderType;
		PlayerAvoider.super.setPlayerAvoiderType(playerAvoiderType);
	}

	////////// COLLISION ////////////

	@Override
	protected void defaultCollindingReaction(CollisionDetector detectorObject) {
		if (getPlayerAvoiderType() == AVOIDERTYPE.STOP) {
			if (detectorObject instanceof PiranhaPlayer)
				paralyseObject();
		}

		blockPath(detectorObject);
	}

	private void paralyseObject() {
		setStunned(true);
		ConditionalListener listener = getPlayerDistanceListener(this, 3);
		setWaitfor(listener);
	}

	private ConditionalListener getPlayerDistanceListener(LivingObject object, int distance) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return new PlayerFinder().isPlayerFutherThan(object, distance);
			}

			@Override
			public void doAction() {
				setStunned(false); // already happens because of WaitforLaw and LivingObject
			}
		};
	}

}
