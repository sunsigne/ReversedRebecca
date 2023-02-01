package com.sunsigne.reversedrebecca.piranha.request.gotoo;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class SpamAffectingRequest extends AffectingRequest {

	////////// REQUEST ////////////

	public SpamAffectingRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new SpamAffectingRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SPAM_AFFECTING";
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		if (target.equalsIgnoreCase("null"))
			return;

		if (list.getList().isEmpty() == false) {
			for (String tempTarget : list.getList()) {
				if (tempTarget.equalsIgnoreCase(target) == false)
					continue;

				list.removeObject(tempTarget);
				map.get(tempTarget).destroy();
				createSpamTimer(object, target);
				return;
			}
		}

		createClasicalTimer(target);
		super.doAction(object, target);
	}

	private GameList<String> list = new GameList<>(LISTTYPE.ARRAY);
	private HashMap<String, GameTimer> map = new HashMap<>();

	private void createClasicalTimer(String target) {
		list.addObject(target);
		GameTimer timer = new GameTimer(20, () -> {
			list.removeObject(target);
			map.remove(target);
		});
		map.put(target, timer);
	}

	private void createSpamTimer(PiranhaObject object, String target) {
		list.addObject(target);
		GameTimer timer = new GameTimer(20, () -> {
			for (String tempTarget : list.getList()) {
				map.remove(tempTarget);
				if (list.getList().iterator().hasNext())
					super.doAction(object, tempTarget);
			}
			list.clear();
		});
		map.put(target, timer);
	}
}
