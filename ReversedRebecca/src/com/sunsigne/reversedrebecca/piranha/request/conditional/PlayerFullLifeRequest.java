package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class PlayerFullLifeRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public PlayerFullLifeRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new PlayerFullLifeRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "PLAYER_FULL_LIFE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {

	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return "true";

		boolean invulnerable = player.isInvulnerable();
		boolean full_life = player.isFullHp();
		String value = String.valueOf(invulnerable || full_life);

		return value;
	}

}
