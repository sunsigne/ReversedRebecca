package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.submenu.ControlsScreen;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class EnterKeyButton extends TitleScreenText implements KeyboardEvent {

	public EnterKeyButton(int x, int y, Key key, boolean actionKey) {
		super("...", x, y, 150, 80);

		this.key = key;
		this.actionKey = actionKey;
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
	private boolean actionKey;

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
		key.refreshKey();

		// destroy objet
		getHandler().removeObject(this);
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);

		// refresh Menu
		preventDuplicated();
		new ControlsScreen(actionKey);
	}

	private GameLimitedList<Key> createList() {
		var list = new GameLimitedList<Key>(LISTTYPE.LINKED);

		list.addObject(new UpKey());
		list.addObject(new DownKey());
		list.addObject(new LeftKey());
		list.addObject(new RightKey());
		list.addObject(new DialogueKey());
		list.addObject(new ActionOneKey());
		list.addObject(new ActionTwoKey());
		list.addObject(new ActionThreeKey());

		list.removeObject(key);
		return list;
	}

	private void preventDuplicated() {
		var keyList = createList();

		// if an existing key has the same value than the new key
		for (Key tempKey : keyList.getList()) {
			int tempCode = tempKey.loadKey();
			if (tempCode == key.loadKey())
				// remove that existing key
				overrideKey(tempKey);
		}
	}

	private void overrideKey(Key key) {
		key.registerKey("...", 65535); // unmapped value for keyboard
		key.refreshKey();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
