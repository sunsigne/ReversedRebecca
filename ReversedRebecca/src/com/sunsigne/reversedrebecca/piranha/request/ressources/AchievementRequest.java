package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementTask;

public class AchievementRequest implements Request {

	////////// REQUEST ////////////

	public AchievementRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new AchievementRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "ACHIEVEMENT";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		new AchievementTask().unlockAchievement(target);
	}

}
