package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class WaitforComplexeTimerRequest implements Request {

	////////// REQUEST ////////////

	public WaitforComplexeTimerRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new WaitforComplexeTimerRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "WAITFOR_COMPLEXE_TIMER";
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

		// sorting timers
		String condition = String.valueOf(target.split("\\?")[0]);
		String[] timers = condition.split(",");

		// sorting values
		String targets = String.valueOf(target.split("\\?")[1]);
		String[] values = targets.split(",");

		for (int index = timers.length - 1; index > -1; index--) {
			String timer = timers[index];
			int time = Integer.parseInt(timer);

			for (String tempValue : values) {
				String num = tempValue.split("\\*")[0];
				if (Integer.parseInt(num) - 1 != index)
					continue;

				replaceWaitfor(object, tempValue.replace(num + "*", ""), time);
			}
		}
	}

	////////// LISTENER ////////////

	private void replaceWaitfor(PiranhaObject object, String target, int time) {
		ConditionalListener previousWaitfor = object.getWaitfor();
		ConditionalListener listener = getTimeListener(object, target, time, previousWaitfor);
		object.setWaitfor(listener);
	}

	private ConditionalListener getTimeListener(PiranhaObject object, String target, int time,
			ConditionalListener waitfor) {

		return new ConditionalListener() {

			GameTimer timer = new GameTimer(time * Game.SEC);

			@Override
			public GenericListener getAction() {
				return this;
			}

			@Override
			public boolean canDoAction() {
				return timer.isReady();
			}

			@Override
			public void doAction() {
				Request request = new GotoRequest();
				request.doAction(object, target);

				if (waitfor != null)
					object.setWaitfor(waitfor);
			}
		};
	}

}
