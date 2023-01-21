package com.sunsigne.reversedrebecca.piranha.request.ressources;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;

public class SoundRequest implements Request {

	////////// REQUEST ////////////

	public SoundRequest() {
		RequestList.getList().addObject(this);
	}

	private static Request action = new SoundRequest();

	@Override
	public Request getRequest() {
		return action;
	}

	@Override
	public String getType() {
		return "SOUND";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	protected void playSound(PiranhaObject object, String path) {
		new SoundTask().playSound(SOUNDTYPE.SOUND, path);
	}

	@Override
	public void doAction(PiranhaObject object, String target) {

		// request unavailable during loading screen
		if (LAYER.LOADING.getHandler().getList().isEmpty() == false)
			return;

		// request unavailable during menu, except if it is fading
		if (LAYER.MENU.getHandler().getList().isEmpty() == false && isMenuFading() == false)
			return;

		playSound(object, target.toLowerCase());
	}

	private boolean isMenuFading() {
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		return ((FadeMenuLaw) law).isFading();
	}

}
