package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.state.ConditionRequest;

public class ConditionPlayerRequest extends ConditionRequest {

	////////// REQUEST ////////////

	public ConditionPlayerRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ConditionPlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CONDITION_PLAYER";
	}

	@Override
	public void doClassicAction(PiranhaObject object, String target) {
		Player player = new PlayerFinder().getPlayer();
		super.doClassicAction(player, target);
	}

	@Override
	protected String getConditionToCheck(PiranhaObject object) {
		Player player = new PlayerFinder().getPlayer();
		return super.getConditionToCheck(player);
	}

}
