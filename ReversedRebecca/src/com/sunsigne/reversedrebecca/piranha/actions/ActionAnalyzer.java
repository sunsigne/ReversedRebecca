package com.sunsigne.reversedrebecca.piranha.actions;

import java.lang.reflect.InvocationTargetException;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.piranha.ChoiceObject;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.AffectingRequest;
import com.sunsigne.reversedrebecca.piranha.request.gotoo.GotoRequest;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.world.World;

public class ActionAnalyzer {

	private String getFile(PiranhaObject object) {
		return "maps/" + World.get().getMapName() + "/" + FilePath.ACTION;
	}

	public Action getAction(PiranhaObject object, String actionInstruction) {
		if (actionInstruction == null)
			return null;

		if (actionInstruction.equalsIgnoreCase("null"))
			return null;

		String actionType = actionInstruction.split("\\(")[0];
		String name = actionInstruction.split("\\(")[1].split("\\)")[0];
		String target = actionInstruction.split("\\)")[1];

		PiranhaObjectAction genericAction = getPiranhaObjectAction(actionType);
		PiranhaObjectAction objectAction = null;

		// if action not found, treat it as a goto
		if (genericAction == null)
			objectAction = createNewAction(object, actionType, target);

		try {
			// creation of a new Instance, otherwise it would override the Action for ALL
			// ExtraBehaviorsObject
			if (objectAction == null)
				objectAction = genericAction.getAction().getClass().getDeclaredConstructor().newInstance();
			objectAction.create(object, target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		// reattribution of key and name
		String keyANDname = getTranslatedText(name, getFile(object));
		objectAction.setKeyEvent(getKey(keyANDname), getKeyEvent(keyANDname));
		objectAction.setName(getName(keyANDname));

		return objectAction;
	}

	private String getTranslatedText(String valueToRead, String filePath) {
		String text = new Translatable().getStrictTranslatedText(valueToRead, filePath);

		if (text.isEmpty())
			text = new Translatable().getStrictTranslatedText(valueToRead, FilePath.ACTION);

		if (text.isEmpty())
			text = new Translatable().getTranslatedText(valueToRead, filePath);

		return text;
	}

	private PiranhaObjectAction getPiranhaObjectAction(String actionType) {

		for (PiranhaObjectAction tempAction : ActionList.getList().getList()) {
			if (actionType.equalsIgnoreCase(tempAction.getName()))
				return tempAction;
		}
		return null;
	}

	private PiranhaObjectAction createNewAction(PiranhaObject object, String actionType, String target) {

		PiranhaObjectAction defaultAction = new PiranhaObjectAction() {

			@Override
			public PiranhaObjectAction getAction() {
				return this;
			}

			@Override
			public String getName() {
				return actionType;
			}

			@Override
			public GenericListener getListener(PiranhaObject ignore, String ignore2) {
				GenericListener listener = () -> {

					Request request;
					if (object instanceof ChoiceObject)
						request = new AffectingRequest();
					else
						request = new GotoRequest();

					request.doAction(object, target);

					object.setTripleAction(object.getTripleAction().removeAction(getAction()));
					object.createTextAction();
				};
				return listener;
			}

			@Override
			public Key getRegisteredKey() {
				return new ActionOneKey();
			}

			@Override
			public int getRegisteredKeyEvent() {
				return ActionOneKey.getKey();
			}

		};

		return defaultAction;
	}

	private Key getKey(String keyANDname) {
		switch (keyANDname.split("%")[0]) {
		case "Key1":
			return new ActionOneKey();
		case "Key2":
			return new ActionTwoKey();
		case "Key3":
			return new ActionThreeKey();
		}
		return new ActionOneKey();
	}

	private int getKeyEvent(String keyANDname) {
		switch (keyANDname.split("%")[0]) {
		case "Key1":
			return ActionOneKey.getKey();
		case "Key2":
			return ActionTwoKey.getKey();
		case "Key3":
			return ActionThreeKey.getKey();
		}
		return ActionOneKey.getKey();
	}

	private String getName(String keyANDname) {
		if (keyANDname.contains("%") == false)
			return keyANDname;

		return (keyANDname.split("%")[1]);

	}

}
