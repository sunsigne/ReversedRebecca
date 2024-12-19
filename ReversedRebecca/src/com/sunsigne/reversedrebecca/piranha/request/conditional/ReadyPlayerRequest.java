package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.menu.Cutscene;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ReadyPlayerRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public ReadyPlayerRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new ReadyPlayerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "READY_PLAYER";
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
		return null;
	}

	@Override
	protected boolean analyseCondition(PiranhaObject object, String target) {

		boolean valueToCheck = Boolean.parseBoolean(String.valueOf(target.split("\\?")[0]));

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return !valueToCheck;

		if (Cutscene.isRunning())
			return !valueToCheck;

		if (new ChoosingRequest().isPlayerChoosing(object))
			return !valueToCheck;

		if (player.isStunned())
			return !valueToCheck;

		return valueToCheck;
	}

}
