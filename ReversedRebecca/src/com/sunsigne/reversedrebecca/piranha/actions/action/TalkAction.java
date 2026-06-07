package com.sunsigne.reversedrebecca.piranha.actions.action;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
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
			String[] superTarget = dissectTarget(target);
			String fileTarget = superTarget[0];
			String tag = superTarget[1];

			String dialogue = getDialogue(object, fileTarget, tag);
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

	private String getDialogue(PiranhaObject object, String target, String tag) {
		String rootPath = object.getPiranhaFile().substring(0, object.getPiranhaFile().length() - 10);

		String type = LivingOption.getType() == LIVING_TYPE.DEFAULT ? "" : LivingOption.getType().getName();
		String typePath = rootPath.concat(type + "/" + target + ".txt");
		String typelessPath = rootPath.concat(target + ".txt");

		String dialogue = new Translatable().getStrictTranslatedText(null, typePath);
		if (dialogue.isEmpty() || containsTag(dialogue, tag) == false)
			dialogue = new Translatable().getTranslatedText(null, typelessPath);

		return dialogue;
	}

	private boolean containsTag(String dialogue, String tag) {
		if (tag == null)
			return true;

		String lines[] = dialogue.split("%");
		for (int index = 0; index < lines.length; index++) {
			String tagLine = lines[index].split(System.getProperty("line.separator"))[0];
			tagLine = tagLine.replace(" ", "");
			if (tag.equalsIgnoreCase(tagLine)) {
				return true;
			}
		}

		return false;
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
