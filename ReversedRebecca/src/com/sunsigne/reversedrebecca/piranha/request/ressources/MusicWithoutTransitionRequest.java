package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class MusicWithoutTransitionRequest extends MusicRequest {

	////////// REQUEST ////////////

	public MusicWithoutTransitionRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new MusicWithoutTransitionRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "MUSIC_WITHOUT_TRANSITION";
	}

	@Override
	protected boolean getTransition() {
		return false;
	}

}
