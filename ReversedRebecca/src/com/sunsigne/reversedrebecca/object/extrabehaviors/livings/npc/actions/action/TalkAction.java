package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.npc.actions.action;

import java.awt.event.KeyEvent;

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

	public String getName() {
		return "TALK";
	}
	
	////////// LISTENER ////////////

	public GenericListener getListener(NPC npc, String target) {
		GenericListener listener = () -> {
			String text = new Translatable().getTranslatedText(target, npc.getInstructionMap());
			System.out.println(text);
		};
		
		return listener;
	}

	////////// KEYBOARD ////////////

	public int getKeyEvent() {
		return KeyEvent.VK_E;
	}

}
