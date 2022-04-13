package com.sunsigne.reversedrebecca.piranha.request.uncompact;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.livings.behaviors.WaitforBehavior;
import com.sunsigne.reversedrebecca.pattern.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;

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

	@Override
	public void doAction(ExtraBehaviorsObject object, String target) {

		String condition = String.valueOf(target.split(",")[0]);

		String conditionType = String.valueOf(condition.split(":")[0]);
		String value = String.valueOf(condition.split(":")[1]);
		String action = String.valueOf(target.split(",")[1]);

		GenericListener generic = new GenericListener() {

			@Override
			public void doAction() {
				Request request = RequestList.getList().getObject(new GotoRequest());
				request.doAction(object, action);
			}
		};

		// search for listener
		ConditionalListener listener = getListener(object, generic, conditionType, value);

		if (listener != null)
			object.addBehavior(new WaitforBehavior(object, listener));
	}

	////////// LISTENER ////////////

	private ConditionalListener getListener(ExtraBehaviorsObject object, GenericListener generic, String conditionType,
			String condition) {

		switch (conditionType) {

		case "TIMER":
			return getTimeListener(generic, Integer.parseInt(condition));

		case "PLAYER_FUTHER_THAN":
			return getPlayerDistanceListener(generic, object, Integer.parseInt(condition));
		}

		return null;
	}

	private ConditionalListener getTimeListener(GenericListener generic, int time) {

		return new ConditionalListener() {

			GameTimer timer = new GameTimer(time);

			@Override
			public boolean canDoAction() {
				return timer.isReady();
			}

			@Override
			public void doAction() {
				generic.doAction();
			}
		};
	}

	private ConditionalListener getPlayerDistanceListener(GenericListener generic, ExtraBehaviorsObject object,
			int distance) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return new PlayerFinder().isPlayerFutherThan(object, distance);
			}

			@Override
			public void doAction() {
				generic.doAction();
			}
		};
	}

}
