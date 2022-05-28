package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.submenu.ControlsScreen;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class EnterKeyButton extends TitleScreenText implements KeyboardEvent {

	public EnterKeyButton(int x, int y, Key key) {
		super("...", x, y, 150, 80);

		this.key = key;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		new GameCursor().setCursor(null);
		MousePos mouse = new MousePos();

		mouse.setX(getX() + getWidth() / 2);
		mouse.setY(getY() + getHeight() / 2);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);
	private Key key;

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// registering new key
		String newKeyText = KeyEvent.getKeyText(e.getKeyCode());
		int newKeyCode = e.getKeyCode();
		key.registerKey(newKeyText, newKeyCode);

		// destroy objet
		getHandler().removeObject(this);
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);

		// refresh Menu
		new ControlsScreen();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
