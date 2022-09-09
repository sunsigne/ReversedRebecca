package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.ConditionalListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;

public class MusicRequest implements Request {

	////////// REQUEST ////////////

	public MusicRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new MusicRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "MUSIC";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	protected boolean getTransition() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		GenericListener generic;

		if (target.equalsIgnoreCase("null"))
			generic = () -> new SoundTask().stopMusic(getTransition());
		else
			generic = () -> new SoundTask().playMusic(target.toLowerCase(), getTransition(), true);

		// request during menu or title screen is "registered" to be run later.
		if (LAYER.MENU.getHandler().getList().isEmpty() == false) {
			ConditionalListener listener = getMenuListener(generic, target.toLowerCase());
			object.setWaitfor(listener);
		} else
			generic.doAction();

	}

	private ConditionalListener getMenuListener(GenericListener generic, String target) {

		return new ConditionalListener() {

			@Override
			public boolean canDoAction() {
				return LAYER.MENU.getHandler().getList().isEmpty();
			}

			@Override
			public GenericListener getAction() {
				return generic;
			}
		};
	}

}
