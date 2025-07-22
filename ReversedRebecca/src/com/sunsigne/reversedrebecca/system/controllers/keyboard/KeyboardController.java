package com.sunsigne.reversedrebecca.system.controllers.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class KeyboardController extends KeyAdapter {

	public KeyboardController(KeyboardEvent keyboardEvent) {
		this.keyboardEvent = keyboardEvent;
		Game.getInstance().addKeyListener(this);
		Game.getInstance().requestFocus();
	}

	public void removeKeyListener() {
		Game.getInstance().removeKeyListener(this);
	}

	////////// KEYBOARD ////////////

	private KeyboardEvent keyboardEvent;
	public ConcurrentLinkedQueue<KeyEvent> keyboardPressedEvent = new ConcurrentLinkedQueue<>();
	public ConcurrentLinkedQueue<KeyEvent> keyboardReleasedEvent = new ConcurrentLinkedQueue<>();

	@Override
	public void keyPressed(KeyEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			keyboardPressedEvent.add(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ControllerManager.getInstance().setUsingGamepad(false);

		if (LAYER.LOADING.getHandler().getList().isEmpty())
			keyboardReleasedEvent.add(e);
	}

	////////// TICK ////////////

	public static void tick() {
		KeyEvent e;
		for (KeyListener tempKeyListener : Game.getInstance().getKeyListeners()) {
			if (tempKeyListener instanceof KeyboardController == false)
				continue;

			KeyboardController tempKeyController = (KeyboardController) tempKeyListener;
			while ((e = tempKeyController.keyboardPressedEvent.poll()) != null)
				tempKeyController.keyboardEvent.keyPressed(e);

			while ((e = tempKeyController.keyboardReleasedEvent.poll()) != null)
				tempKeyController.keyboardEvent.keyReleased(e);
		}
	}

}
