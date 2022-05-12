package com.sunsigne.reversedrebecca.piranha.actions;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;

public abstract class PiranhaObjectAction extends Action {

	public PiranhaObjectAction() {
		super(null, null, null, 0);
	}

	public abstract PiranhaObjectAction getAction();

	public void create(PiranhaObject object, String target) {
		setName(new Translatable().getTranslatedText(getName(), object.getFile()));
		setListener(getListener(object, target));
		setKeyEvent(getKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// LISTENER ////////////

	public abstract GenericListener getListener(PiranhaObject object, String target);

	////////// KEYBOARD ////////////

	String file = "userdata/actions.csv";

	@Override
	public int getKeyEvent() {
		String registeredAction = new FileTask().read(getName(), file);
		switch (registeredAction) {
		case "Action1":
			return ActionOneKey.getKey();
		case "Action2":
			return ActionTwoKey.getKey();
		case "Action3":
			return ActionThreeKey.getKey();
		}

		return ActionOneKey.getKey();
	}

}
