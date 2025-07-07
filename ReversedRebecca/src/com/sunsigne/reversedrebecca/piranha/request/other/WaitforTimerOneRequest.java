package com.sunsigne.reversedrebecca.piranha.request.other;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class WaitforTimerOneRequest implements Request {

	////////// REQUEST ////////////

	public WaitforTimerOneRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new WaitforTimerOneRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "WAITFOR_TIMER_ONE";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	// WARNING ! Only ONE waitfor can be active on the same time.
	@Override
	public void doAction(PiranhaObject object, String target) {

		// removal of the previous waitfor (even if unaccomplished)
		object.setWaitfor(null);

		// search for listener
		ConditionalListener listener = getTimeListener(object, target);
		object.setWaitfor(listener);
	}

	////////// LISTENER ////////////

	private ConditionalListener getTimeListener(PiranhaObject object, String target) {

		return new ConditionalListener() {

			GameTimer timer = new GameTimer(Game.SEC);

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
			}
		};
	}

}
