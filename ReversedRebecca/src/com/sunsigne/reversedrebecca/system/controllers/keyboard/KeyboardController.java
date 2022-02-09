package com.sunsigne.reversedrebecca.system.controllers.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

	@Override
	public void keyPressed(KeyEvent e) {
		keyboardEvent.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyboardEvent.keyReleased(e);
	}

}
