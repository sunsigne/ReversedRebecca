package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HighlightRequest extends HighlightAbsoluteRequest {

	////////// REQUEST ////////////

	public HighlightRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new HighlightRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "HIGHLIGHT";
	}

	@Override
	public int[] getRelativePos(PiranhaObject object) {
		return new int[] { 0, 0 };
	}

}
