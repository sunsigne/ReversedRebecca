package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.world.World;

public class CounterOneRequest implements Request {

	////////// REQUEST ////////////

	public CounterOneRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new CounterOneRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "COUNTER_ONE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	public int getCounterNumber() {
		return 1;
	}
	
	@Override
	public void doAction(PiranhaObject object, String target) {

		// analyse target

		String type = target.split(",")[0].toUpperCase();
		String value = target.split(",")[1].toUpperCase();

		// process action

		switch (type) {

		case "CREATE":
			createAction(object, value);
			break;
		case "ADD":
			addAction(object, value);
			break;
		case "REMOVE":
			removeAction(object, value);
			break;
		}
	}

	private void createAction(PiranhaObject object, String value) {
		String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);
		path = path.concat("counter" + ".txt");
		String name = new Translatable().getTranslatedText(value, path);
		World.get().getLevelStats().getCounter(getCounterNumber()).setName(name);
	}

	private void addAction(PiranhaObject object, String value) {
		Integer count = Integer.parseInt(value);
		World.get().getLevelStats().getCounter(getCounterNumber()).addCount(count);
	}

	private void removeAction(PiranhaObject object, String value) {
		Integer count = Integer.parseInt(value);
		World.get().getLevelStats().getCounter(getCounterNumber()).removeCount(count);
	}
	
}
