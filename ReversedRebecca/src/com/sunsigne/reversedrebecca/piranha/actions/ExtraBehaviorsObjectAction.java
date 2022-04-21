package com.sunsigne.reversedrebecca.piranha.actions;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class ExtraBehaviorsObjectAction extends Action {

	public ExtraBehaviorsObjectAction() {
		super(null, null, null, 0);
	}

	public abstract ExtraBehaviorsObjectAction getAction();

	public void create(ExtraBehaviorsObject object, String target) {
		setName(new Translatable().getTranslatedText(getName(), object.getFile()));
		setListener(getListener(object, target));
		setKeyEvent(getKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// LISTENER ////////////

	public abstract GenericListener getListener(ExtraBehaviorsObject object, String target);

	////////// KEYBOARD ////////////

	public abstract int getKeyEvent();

}
