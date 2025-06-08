package com.sunsigne.reversedrebecca.piranha.request.conditional;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SeePlayerAndCloserThanRequest extends ConditionalRequest {

	////////// REQUEST ////////////

	public SeePlayerAndCloserThanRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SeePlayerAndCloserThanRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SEE_PLAYER_AND_CLOSER_THAN";
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
		int valueToCheck = Integer.parseInt(target.split("\\?")[0]);
		boolean isClose = new PlayerFinder().isPlayerCloserThan(object, valueToCheck);

		Player player = new PlayerFinder().getPlayer();
		boolean inSight = new SightFinder(object, player).isGoalInSight();

		return isClose && inSight;
	}

}
