package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.world.World;

public class CounterRequest implements Request {

	////////// REQUEST ////////////

	public CounterRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new CounterRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COUNTER";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// analyse target

		int number = Integer.parseInt(target.split(",")[0].toUpperCase());
		String type = target.split(",")[1].toUpperCase();
		String value = target.split(",")[2].toUpperCase();

		// process action

		switch (type) {

		case "CREATE":
			createAction(object, number, value);
			break;
		case "ADD":
			addAction(object, number, value);
			break;
		case "REMOVE":
			removeAction(object, number, value);
			break;
		}
	}

	private void createAction(PiranhaObject object, int number, String value) {
		String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);
		path = path.concat("counter" + ".txt");
		String name = new Translatable().getTranslatedText(value, path);
		World.get().getLevelStats().getCounter(number).setName(name);
	}

	private void addAction(PiranhaObject object, int number, String value) {
		Integer count = Integer.parseInt(value);
		World.get().getLevelStats().getCounter(number).addCount(count);
	}

	private void removeAction(PiranhaObject object, int number, String value) {
		Integer count = Integer.parseInt(value);
		World.get().getLevelStats().getCounter(number).removeCount(count);
	}
	
}
