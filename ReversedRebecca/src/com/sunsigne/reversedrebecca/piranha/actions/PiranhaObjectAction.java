package com.sunsigne.reversedrebecca.piranha.actions;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public abstract class PiranhaObjectAction extends Action {

	public PiranhaObjectAction() {
		super(null, null, null, 0);
	}

	public abstract PiranhaObjectAction getAction();

	public void create(PiranhaObject object, String target) {
		setName(new Translatable().getTranslatedText(getName(), FilePath.ACTION));
		setListener(getListener(object, target));
		setKeyEvent(getRegisteredKeyEvent());
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// LISTENER ////////////

	public abstract GenericListener getListener(PiranhaObject object, String target);

	////////// KEYBOARD ////////////

	public abstract int getRegisteredKeyEvent();

}
