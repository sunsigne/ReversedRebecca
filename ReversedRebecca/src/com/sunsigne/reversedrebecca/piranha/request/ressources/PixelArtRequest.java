package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.menu.pixelart.PixelArtTask;

public class PixelArtRequest implements Request {

	////////// REQUEST ////////////

	public PixelArtRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new PixelArtRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "PIXEL_ART";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		new PixelArtTask().unlock(target);
	}

}
