package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.ressources.layers.LayerDualizer;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public interface IndexRequest extends Request {

	public default boolean playerExcluded() {
		return true;
	}

	public default GameObject getGameObject(PiranhaObject object, String target) {
		return getGameObject(object, target, 0);
	}

	public default GameObject getGameObject(PiranhaObject object, String target, int gap) {

		// determinate the position

		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);

		// determinate the object from the index

		String data = String.valueOf(target.split(":")[1]);
		Handler handler = getSubLayer(object, String.valueOf(data.contains(",") ? data.split(",")[0] : null));
		int index = Integer.parseInt(data.contains(",") ? data.split(",")[1] : data) - 1;

		GameList<GameObject> object_list = Handler.getObjectsAtPos(handler, goal.getX() + gap, goal.getY() + gap,
				object.getSize(), playerExcluded());

		// if no object found

		if (object_list.getList().isEmpty())
			return null;

		// if index is too high, get last element

		if (object_list.getList().size() <= index)
			index = object_list.getList().size() - 1;

		return object_list.getList().get(index);
	}

	public default int getIndex(Handler handler, String value) {

		switch (value.toUpperCase()) {

		case "FRONT":
			return handler.getList().size();
		case "BACK":
			return 0;
		}

		return 0;
	}

	public default Handler getSubLayer(PiranhaObject object, String value) {

		Handler handler = object.getHandler();

		switch (value.toUpperCase()) {

		case "GROUND":
			return new LayerDualizer().getGroundFromContent(handler).getHandler();
		case "WORLD":
			return handler;
		case "LIGHT":
			return new LayerDualizer().getLightFromContent(handler).getHandler();
		}

		return handler;
	}

	public default void addObject(Handler handler, int index, GameObject creation) {
		try {
			handler.getList().add(index, creation);
		} catch (IndexOutOfBoundsException e) {
			// may occurs if index is superior to list size,
			// in what case, element can just be appended at the end
			handler.getList().add(creation);
		}
	}

}
