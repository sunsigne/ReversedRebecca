package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.PiranhaObjectAction;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

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
			path = path.concat(target + ".csv");
			String dialogue = new Translatable().getTranslatedText(null, path);

			ChatBox chatbox = new ChatBox(object, target, dialogue);
			chatbox.openChat();

			object.setTripleAction(object.getTripleAction().removeAction(this));
			object.createTextAction();
		};

		return listener;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getRegisteredKeyEvent() {
		return ActionOneKey.getKey();
	}

}
