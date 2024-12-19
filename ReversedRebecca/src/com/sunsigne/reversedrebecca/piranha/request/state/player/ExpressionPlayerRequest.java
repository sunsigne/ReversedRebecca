package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.ressources.ExpressionRequest;

public class ExpressionPlayerRequest extends ExpressionRequest {

	////////// REQUEST ////////////

	public ExpressionPlayerRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ExpressionPlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "EXPRESSION_PLAYER";
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Player player = new PlayerFinder().getPlayer();
		super.doAction(player, target);
	}

}
