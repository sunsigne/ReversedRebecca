package com.sunsigne.reversedrebecca.piranha.request.state.player;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.piranha.request.move.TeleportRequest;
import com.sunsigne.reversedrebecca.piranha.request.other.DisplayRequest;
import com.sunsigne.reversedrebecca.piranha.request.state.ConditionRequest;
import com.sunsigne.reversedrebecca.piranha.request.state.FacingRequest;

public class StatePlayerRequest implements Request {

	////////// REQUEST ////////////

	public StatePlayerRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new StatePlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "STATE_PLAYER";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		String[] targets = target.split(",");

		Request teleport = RequestList.getList().getObject(new TeleportRequest());
		teleport.doAction(player, targets[0]);

		Request display = RequestList.getList().getObject(new DisplayRequest());
		display.doAction(player, targets[1] + "," + targets[2]);

		Request facing = RequestList.getList().getObject(new FacingRequest());
		facing.doAction(player, targets[3]);

		Request condition = RequestList.getList().getObject(new ConditionRequest());
		condition.doAction(player, targets[4]);

		int size = targets.length;
		if (size < 6)
			return;

		Request got0 = RequestList.getList().getObject(new GotoRequest());
		for (int index = 5; index < size; index++)
			got0.doAction(object, targets[index]);
	}

}
