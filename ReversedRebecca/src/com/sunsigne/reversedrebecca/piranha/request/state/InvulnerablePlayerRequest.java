package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.conditional.ConditionalRequest;

public class InvulnerablePlayerRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public InvulnerablePlayerRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new InvulnerablePlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "INVULNERABLE_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		boolean invulnerable = Boolean.parseBoolean(target);
		Player player = new PlayerFinder().getPlayer();
		player.setInvulnerable(invulnerable);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		Player player = new PlayerFinder().getPlayer();
		return String.valueOf(player.isInvulnerable());
	}

}
