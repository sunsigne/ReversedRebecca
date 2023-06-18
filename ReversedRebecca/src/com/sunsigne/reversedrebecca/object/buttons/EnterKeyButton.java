package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.menu.ingame.submenu.ControlsIngameScreen;
import com.sunsigne.reversedrebecca.menu.submenu.ControlsScreen;
import com.sunsigne.reversedrebecca.pattern.list.GameLimitedList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor.CURSOR_TYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class EnterKeyButton extends TitleScreenText implements KeyboardEvent {

	public EnterKeyButton(int x, int y, Key key, boolean actionKey, boolean ingame) {
		super("...", x, y, 150, 80);

		this.key = key;
		this.actionKey = actionKey;
		this.ingame = ingame;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		ControllerManager.getInstance().setUsingGamepad(false);
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

	private boolean ingame;

	@Override
	public void keyPressed(KeyEvent e) {
		var analyzer = new KeyAnalyzer(e.getKeyCode());
		if (analyzer.isAllowedKey() == false) {
			new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
			return;
		}

		// play sound
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_validate");

		// registering new key
		String keyText = analyzer.getKeyText();
		key.registerKey(keyText, e.getKeyCode());
		key.refreshKey();

		// destroy objet
		removeObject();
		new GameCursor().setCursor(CURSOR_TYPE.NORMAL);

		// refresh Menu
		preventDuplicated();

		if (ingame)
			new ControlsIngameScreen(actionKey);
		else
			new ControlsScreen(actionKey);
	}

	private GameLimitedList<Key> createList() {
		var list = new GameLimitedList<Key>(LISTTYPE.ARRAY);

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
