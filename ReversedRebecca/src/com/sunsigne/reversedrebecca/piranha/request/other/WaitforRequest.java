package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class WaitforRequest implements Request {

	////////// REQUEST ////////////

	public WaitforRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request request = new WaitforRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "WAITFOR";
	}

	@Override
	public boolean hasCompactWriting() {
		return false;
	}

	// WARNING ! Only ONE waitfor can be active on the same time.
	@Override
	public void doAction(PiranhaObject object, String target) {

		// removal of the previous waitfor (even if unaccomplished)
		object.setWaitfor(null);

		if (target.equalsIgnoreCase("null"))
			return;

		String condition = String.valueOf(target.split("\\?")[0]);

		String conditionType = String.valueOf(condition.split(":")[0]);
		String value = String.valueOf(condition.split(":")[1]);
		String action = String.valueOf(target.split("\\?")[1]);

		GenericListener generic = new GenericListener() {

			@Override
			public void doAction() {
				Request request = RequestList.getList().getObject(new GotoRequest());
				request.doAction(object, action);
			}
		};

		// search for listener
		ConditionalListener listener = getListener(object, generic, conditionType, value);
		object.setWaitfor(listener);
	}

	////////// LISTENER ////////////

	private ConditionalListener getListener(PiranhaObject object, GenericListener generic, String conditionType,
			String condition) {

		switch (conditionType) {

		case "TIMER":
			return getTimeListener(generic, Integer.parseInt(condition));

		case "FACING":
			return getFacingListener(generic, object, condition);

		case "PLAYER_FUTHER_THAN":
			return getPlayerDistanceListener(generic, object, Integer.parseInt(condition), true);

		case "PLAYER_CLOSER_THAN":
			return getPlayerDistanceListener(generic, object, Integer.parseInt(condition), false);

		case "PLAYER_FACING":
			return getPlayerFacingListener(generic, condition);
			
		case "SLOW":
		case "MOVE":
		case "FAST":
			doMoveAction(object, conditionType, condition);
			return getMoveListener(generic, object);
		}

		return null;
	}

	private ConditionalListener getTimeListener(GenericListener generic, int time) {

		return new ConditionalListener() {

			GameTimer timer = new GameTimer(time * Game.SEC);

			@Override
			public boolean canDoAction() {
				return timer.isReady();
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

	private void doMoveAction(PiranhaObject object, String conditionType, String condition) {
		for (Request tempAction : RequestList.getList().getList()) {
			if (conditionType.equalsIgnoreCase(tempAction.getType()))
				tempAction.doAction(object, condition);
		}
	}

	private ConditionalListener getMoveListener(GenericListener generic, PiranhaObject object) {

		Position goal = object.getGoal();
		
		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return goal.getX() - object.getX() == 0 & goal.getY() - object.getY() == 0;
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

	private ConditionalListener getFacingListener(GenericListener generic, PiranhaObject object, String condition) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return object.getFacing().getName().equalsIgnoreCase(condition);
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

	private ConditionalListener getPlayerDistanceListener(GenericListener generic, PiranhaObject object, int distance,
			boolean futherType) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				if (futherType)
					return new PlayerFinder().isPlayerFutherThan(object, distance);
				else
					return new PlayerFinder().isPlayerCloserThan(object, distance);
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

	private ConditionalListener getPlayerFacingListener(GenericListener generic, String condition) {

		boolean reversed = condition.contains("!");
		if (reversed)
			condition = condition.substring(1);

		DIRECTION facing = DIRECTION.NULL;

		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(condition)) {
				facing = tempFacing;
				break;
			}
		}

		DIRECTION final_facing = facing;

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				Player player = new PlayerFinder().getPlayer();
				if (reversed)
					return player.getFacing() != final_facing;
				else
					return player.getFacing() == final_facing;
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

}
