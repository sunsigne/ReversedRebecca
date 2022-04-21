package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.piranha.actions.ActionList;
import com.sunsigne.reversedrebecca.piranha.actions.ExtraBehaviorsObjectAction;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.other.TripleActionRequest;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class TalkAction extends ExtraBehaviorsObjectAction {

	////////// NPC ACTION ////////////

	public TalkAction() {
		ActionList.getList().addObject(this);
	}

	private static ExtraBehaviorsObjectAction action = new TalkAction();

	@Override
	public ExtraBehaviorsObjectAction getAction() {
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
		return ActionOneKey.getKey();
	}

}
