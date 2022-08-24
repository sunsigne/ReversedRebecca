package com.sunsigne.reversedrebecca.piranha.request.action;

import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.InFrontOfFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class ChoiceRequest implements Request {

	////////// REQUEST ////////////

	public ChoiceRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new ChoiceRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "CHOICE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// if there is no player, there is no choice (ofc)

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		// determinate the position

		int[] pos = new InFrontOfFinder().getPos(player);
		ChoiceObject choice = new ChoiceObject("CHOICE", pos[0], pos[1]);

		// determinate the choice

		Request request = RequestList.getList().getObject(new TripleActionRequest());
		request.doAction(choice, target);

		// create the object

		player.getHandler().addObject(choice);
		choice.tick();
	}

}
