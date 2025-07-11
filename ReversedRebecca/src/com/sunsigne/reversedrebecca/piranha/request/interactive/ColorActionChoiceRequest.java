package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ColorActionChoiceRequest extends ColorActionMyselfRequest {

	////////// REQUEST ////////////

	public ColorActionChoiceRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new ColorActionChoiceRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "COLOR_ACTION_CHOICE";
	}

	@Override
	protected PiranhaObject getObject(PiranhaObject object) {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return null;

		Handler handler = player.getHandler();

		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof ChoiceObject)
				return (ChoiceObject) tempUpdatable;
		}

		return null;
	}

}
