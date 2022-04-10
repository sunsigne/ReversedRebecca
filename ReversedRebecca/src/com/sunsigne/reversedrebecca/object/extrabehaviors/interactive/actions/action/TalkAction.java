package com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.action;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.actions.ActionList;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.piranha.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.TripleActionRequest;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class TalkAction extends ObjectAction {

	////////// NPC ACTION ////////////

	public TalkAction() {
		ActionList.getList().addObject(this);
	}

	private static ObjectAction action = new TalkAction();

	@Override
	public ObjectAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "TALK";
	}

	////////// LISTENER ////////////

	@Override
	public GenericListener getListener(ExtraBehaviorsObject object, String target) {
		GenericListener listener = () -> {
			String path = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);
			path = path.concat(target + ".csv");
			String dialogue = new Translatable().getTranslatedText(null, path);

			ChatBox chatbox = new ChatBox(object, target, dialogue);
			chatbox.openChat();

			Request instruction = RequestList.getList().getObject(new TripleActionRequest());
			instruction.doAction(object, null);
		};

		return listener;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}

}
