package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.Deed;

public class DeedRequest implements Request {

	////////// REQUEST ////////////

	public DeedRequest() {
		new RequestList().addRequest(this, getType());
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
		String file = "deeds" + ".txt";
		path = path.concat(file);

		// get the translation

		String living_type = LivingOption.getType() == LIVING_TYPE.DEFAULT ? ""
				: "/" + LivingOption.getType().getName();
		String typePath = path.substring(0, path.length() - file.length() - 1).concat(living_type + "/" + file);

		String lvl_deed = new Translatable().getStrictTranslatedText(target_deed, typePath);
		if (lvl_deed.isEmpty())
			lvl_deed = new Translatable().getTranslatedText(target_deed, path);

		// analyse the deed

		String text = lvl_deed.split("%")[1];
		int weight = Integer.parseInt(lvl_deed.split("%")[0]);

		// register the deed

		switch (type) {

		case "GOOD":
			System.out.println("GOOD_DEED : " + text);
			deed.setGoodDeed(weight, text);
			break;

		case "BAD":
			System.out.println("BAD_DEED : " + text);
			deed.setBadDeed(weight, text);
			break;
		}
	}

}
