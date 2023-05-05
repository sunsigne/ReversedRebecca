package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.chat.ChatBox;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class UserCanInputRestartDialogue extends WorldControllers {

	private static WorldControllers worldKeyboard = new UserCanInputRestartDialogue();

	@Override
	public WorldControllers getWorldControllers() {
		return worldKeyboard;
	}

	////////// KEYBOARD ////////////

	public static Action lastChat;

	@Override
	public void inputPressed(int key, int button) {
		if (key != KeyEvent.VK_ENTER)
			return;

		// if no object
		Handler handler = LAYER.PUZZLE.getHandler();
		if (handler.getList().isEmpty())
			return;

		// if no chatbox
		Updatable updatable = handler.getList().get(0);
		if (updatable instanceof ChatBox == false)
			return;

		// restart dialogue
		handler.clear();
		lastChat.doAction();
	}

	@Override
	public void inputReleased(int key, int button) {

	}

}
