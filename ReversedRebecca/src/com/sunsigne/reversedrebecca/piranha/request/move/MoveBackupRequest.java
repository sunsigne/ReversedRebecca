package com.sunsigne.reversedrebecca.piranha.request.move;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator;
import com.sunsigne.reversedrebecca.object.characteristics.SpeedVariator.SPEEDNESS;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

public class MoveBackupRequest implements Request {

	////////// REQUEST ////////////

	public MoveBackupRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new MoveBackupRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "MOVE_BACKUP";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	public SPEEDNESS getSpeedness() {
		return SPEEDNESS.NORMAL;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		String[] targets = target.split(",");

		int x = Integer.parseInt(targets[0].split("-")[0]);
		int y = Integer.parseInt(targets[0].split("-")[1]);
		int x_backup = Integer.parseInt(targets[1].split("-")[0]);
		int y_backup = Integer.parseInt(targets[1].split("-")[1]);

		GoalObject goal = new GoalObject(x, y, false);
		GoalObject goal_backup = new GoalObject(x_backup, y_backup, false);

		if (object instanceof SpeedVariator) {
			SpeedVariator variator = (SpeedVariator) object;
			variator.setSpeedness(getSpeedness());
		}

		object.setGoal(goal);
		ConditionalListener listener = getConditionalListener(object, goal, goal_backup);
		object.setWaitfor(listener);
	}

	private ConditionalListener getConditionalListener(PiranhaObject object, GoalObject goal, GoalObject goal_backup) {

		return new ConditionalListener() {

			private ConditionalListener getListener() {
				return this;
			}

			@Override
			public boolean canDoAction() {
				return object.getPath() == DIRECTION.NULL;
			}

			@Override
			public GenericListener getAction() {
				return () -> switchGoals(object, goal, goal_backup, getListener());
			}
		};
	}

	// the loop destroy itself once goal or goal_backup is reached
	private void switchGoals(PiranhaObject object, GoalObject goal, GoalObject goal_backup,
			ConditionalListener listener) {

		// case where GOAL is blocked
		if (object.getGoal() == goal)
			object.setGoal(goal_backup);

		// case where GOAL BACKUP is blocked
		else if (object.getGoal() == goal_backup)
			object.setGoal(goal);

		// once goal got defined, restart the loop
		if (object.getGoal() != null)
			object.setWaitfor(listener);
	}

}
