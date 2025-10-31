package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class GiveHpPlayer implements Request {

	////////// REQUEST ////////////

	public GiveHpPlayer() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new GiveHpPlayer();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "GIVE_HP_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		int hp = Integer.parseInt(target);

		if (hp > 0)
			player.addHp(hp);
		if (hp < 0)
			player.removeHp(-hp);
	}

}
