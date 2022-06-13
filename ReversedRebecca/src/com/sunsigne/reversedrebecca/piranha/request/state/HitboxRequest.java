package com.sunsigne.reversedrebecca.piranha.request.state;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class HitboxRequest implements Request {

	////////// REQUEST ////////////

	public HitboxRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new HitboxRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "HITBOX";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		String[] targets = target.split(",");
		int x = Integer.valueOf(targets[0]);
		int y = Integer.valueOf(targets[1]);
		int width = Integer.valueOf(targets[2]);
		int height = Integer.valueOf(targets[3]);
		
		object.setBounds(x, y, width, height);
	}

}
