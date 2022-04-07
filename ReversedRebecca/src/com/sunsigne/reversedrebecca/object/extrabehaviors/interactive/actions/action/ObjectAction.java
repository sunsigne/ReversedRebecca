package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class ObjectAction extends Action {

	public ObjectAction() {
		super(null, null, null, 0);
	}

	public abstract ObjectAction getAction();

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
