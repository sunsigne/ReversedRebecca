package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class InvulnerablePlayerRequest implements Request {

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
		return "PLAYER_INVULNERABLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		boolean invulnerable = Boolean.parseBoolean(target);
		Player player = new PlayerFinder().getPlayer();
		player.setInvulnerable(invulnerable);
	}

}
