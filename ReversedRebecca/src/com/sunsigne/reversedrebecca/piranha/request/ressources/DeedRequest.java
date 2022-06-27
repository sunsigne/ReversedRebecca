package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.Deed;

public class DeedRequest implements Request {

	////////// REQUEST ////////////

	public DeedRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new DeedRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "DEED";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// analyse target

		String type = target.split(",")[0].toUpperCase();
		String target_deed = target.split(",")[1].toUpperCase();

		// determinate the deed

		Deed deed = World.get().getLevelStats().getDeed();
		String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);
		path = path.concat("deeds" + ".csv");
		String lvl_deed = new Translatable().getTranslatedText(target_deed, path);

		// analyse the deed

		String text = lvl_deed.split("%")[1];
		int weight = Integer.parseInt(lvl_deed.split("%")[0]);

		// register the deed

		switch (type) {

		case "GOOD":
			deed.setGoodDeed(weight, text);
			break;

		case "BAD":
			deed.setBadDeed(weight, text);
			break;
		}
	}

}
