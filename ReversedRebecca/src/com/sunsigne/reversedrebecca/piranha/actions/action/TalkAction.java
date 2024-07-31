package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;

public class TalkAction extends PiranhaObjectAction {

	////////// NPC ACTION ////////////

	public TalkAction() {
		ActionList.getList().addObject(this);
	}

	private static PiranhaObjectAction action = new TalkAction();

	@Override
	public PiranhaObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "TALK";
	}

	////////// LISTENER ////////////

	@Override
	public GenericListener getListener(PiranhaObject object, String target) {
		GenericListener listener = () -> {
			String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);

			String[] superTarget = dissectTarget(target);
			String fileTarget = superTarget[0];
			String tag = superTarget[1];
			path = path.concat(fileTarget + ".txt");

			String dialogue = new Translatable().getTranslatedText(null, path);

			ChatBox chatbox = new ChatBox(object, target, dialogue, tag);
			chatbox.openChat();

			if (object.getTripleAction() != null) {
				object.setTripleAction(object.getTripleAction().removeAction(this));
				object.createTextAction();
			}
		};

		return listener;
	}

	private String[] dissectTarget(String target) {
		if (target.contains("*") == false)
			return new String[] { target, null };

		String fileTarget = target.split("\\*")[0];
		String tag = target.split("\\*")[1];

		return new String[] { fileTarget, tag };
	}

	////////// KEYBOARD ////////////

	@Override
	public Key getRegisteredKey() {
		return new ActionOneKey();
	}

	@Override
	public int getRegisteredKeyEvent() {
		return ActionOneKey.getKey();
	}

}
