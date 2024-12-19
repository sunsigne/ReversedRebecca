package com.sunsigne.reversedrebecca.piranha.request.interactive;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.piranha.request.IndexRequest;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class OpenPuzzleRequest implements IndexRequest {

	////////// REQUEST ////////////

	public OpenPuzzleRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request action = new OpenPuzzleRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "OPEN_PUZZLE";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		PuzzlerObject puzzler = getPuzzlerObject(object, target);
		if (puzzler == null)
			return;

		Action action = getPuzzlerAction(puzzler, target);
		if (action == null)
			return;

		action.forceAction();
	}

	private PuzzlerObject getPuzzlerObject(PiranhaObject object, String target) {

		// determinate the position

		String pos = String.valueOf(target.split(",")[0]);
		int x = Integer.parseInt(pos.split("-")[0]);
		int y = Integer.parseInt(pos.split("-")[1]);
		GoalObject goal = new GoalObject(x, y, false);

		// determinate the objects

		GameList<GameObject> object_list = Handler.getObjectsAtPos(object.getHandler(), goal.getX(), goal.getY(),
				object.getSize(), true);

		// if no object found

		if (object_list.getList().isEmpty())
			return null;

		// if puzzler found in the list

		int size = object_list.getList().size();
		for (int index = 0; index < size; index++) {
			GameObject tempObject = object_list.getList().get(index);
			if (tempObject instanceof PuzzlerObject)
				return (PuzzlerObject) tempObject;
		}

		// if no puzzler found in the list
		return null;
	}

	private Action getPuzzlerAction(PuzzlerObject puzzler, String target) {
		int num = Integer.parseInt(String.valueOf(target.split(",")[1]));
		return puzzler.getTripleAction().getAction(num - 1);
	}

}
