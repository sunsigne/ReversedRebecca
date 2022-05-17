package com.sunsigne.reversedrebecca.piranha.request;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public interface IndexRequest extends Request {

	public default GameObject getGameObject(PiranhaObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(":")[0]);
		boolean onTheSpot = pos.equalsIgnoreCase("onthespot");
		int x = onTheSpot ? (object.getX() / Size.M) : Integer.parseInt(pos.split("-")[0]);
		int y = onTheSpot ? (object.getY() / Size.M) : Integer.parseInt(pos.split("-")[1]);
		
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the object from the index 

		int index = Integer.parseInt(target.split(":")[1]) - 1;

		GameList<GameObject> object_list = Handler.getObjectsAtPos(object.getHandler(), goal.getX(), goal.getY(),
				object.getSize());

		// if no object found 
		
		if (object_list.getList().isEmpty())
			return null;

		// if index is too high, get last element 
		
		if (object_list.getList().size() <= index)
			index = object_list.getList().size() - 1;

		return object_list.getList().get(index);
	}
	
}
