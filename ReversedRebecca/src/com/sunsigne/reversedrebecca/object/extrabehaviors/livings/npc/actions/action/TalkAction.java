package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.NPC;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.ActionList;
import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class TalkAction extends NPCAction {

	////////// NPC ACTION ////////////

	public TalkAction() {
		ActionList.getList().addObject(this);
	}

	private static NPCAction action = new TalkAction();

	@Override
	public NPCAction getAction() {
		return action;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "TALK";
	}

	////////// LISTENER ////////////

	@Override
	public GenericListener getListener(NPC npc, String target) {
		GenericListener listener = () -> {
			String path = npc.getInstructionMap().split("NPC-")[0];
			path = path.concat(target + ".csv");
			String dialogue = new Translatable().getTranslatedText(null, path);

			ChatBox chatbox = new ChatBox(dialogue);
			chatbox.openChat();
		};

		return listener;
	}

	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}

}
