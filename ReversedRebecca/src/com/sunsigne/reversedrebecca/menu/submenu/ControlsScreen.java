package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.EnterKeyButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class ControlsScreen extends SubMenuScreen {

	public ControlsScreen() {
		super();
		loadText();
		createButtons();
	}

	////////// NAME ////////////

	public String getName() {
		return "controls";
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen();
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText textButton = null;
		int x = 325;
		int y = 503;

		// up
		text = new Translatable().getTranslatedText("Up", file);
		textButton = new TitleScreenText(text, x, y);
		LAYER.MENU.addObject(textButton);

		// down
		text = new Translatable().getTranslatedText("Down", file);
		textButton = new TitleScreenText(text, x, y + 104);
		LAYER.MENU.addObject(textButton);

		// left
		text = new Translatable().getTranslatedText("Left", file);
		textButton = new TitleScreenText(text, x, y + 208);
		LAYER.MENU.addObject(textButton);

		// right
		text = new Translatable().getTranslatedText("Right", file);
		textButton = new TitleScreenText(text, x, y + 312);
		LAYER.MENU.addObject(textButton);

		// action 1
		text = new Translatable().getTranslatedText("Action1", file);
		textButton = new TitleScreenText(text, x + 625, y);
		LAYER.MENU.addObject(textButton);

		// action 2
		text = new Translatable().getTranslatedText("Action2", file);
		textButton = new TitleScreenText(text, x + 625, y + 104);
		LAYER.MENU.addObject(textButton);

		// action 3
		text = new Translatable().getTranslatedText("Action3", file);
		textButton = new TitleScreenText(text, x + 625, y + 208);
		LAYER.MENU.addObject(textButton);
	}

	////////// BUTTONS ////////////

	private ButtonObject upButton;
	private ButtonObject downButton;
	private ButtonObject leftButton;
	private ButtonObject rightButton;

	private ButtonObject actionOneButton;
	private ButtonObject actionTwoButton;
	private ButtonObject actionThreeButton;

	private ButtonObject getKeyboardButton(String text, int x, int y, GenericListener onPress) {
		return new TitleScreenButton(text, 770 + x, 503 + y, 150, 80, onPress, null);
	}

	private void createButtons() {
		createUpKeyButton();
		createDownKeyButton();
		createLeftKeyButton();
		createRightKeyButton();

		createActionOneKeyButton();
		createActionTwoKeyButton();
		createActionThreeKeyButton();
		
		createDefaultButton();
	}

	private void createUpKeyButton() {
		Key key = new UpKey();
		GenericListener onPress = () -> refresh(upButton, key);
		String text = key.getRegisteredKey();
		upButton = getKeyboardButton(text, 0, 0, onPress);
		LAYER.MENU.addObject(upButton);
	}

	private void createDownKeyButton() {
		Key key = new DownKey();
		GenericListener onPress = () -> refresh(downButton, key);
		String text = key.getRegisteredKey();
		downButton = getKeyboardButton(text, 0, 104, onPress);
		LAYER.MENU.addObject(downButton);
	}

	private void createLeftKeyButton() {
		Key key = new LeftKey();
		GenericListener onPress = () -> refresh(leftButton, key);
		String text = key.getRegisteredKey();
		leftButton = getKeyboardButton(text, 0, 208, onPress);
		LAYER.MENU.addObject(leftButton);
	}

	private void createRightKeyButton() {
		Key key = new RightKey();
		GenericListener onPress = () -> refresh(rightButton, key);
		String text = key.getRegisteredKey();
		rightButton = getKeyboardButton(text, 0, 312, onPress);
		LAYER.MENU.addObject(rightButton);
	}

	private void createActionOneKeyButton() {
		Key key = new ActionOneKey();
		GenericListener onPress = () -> refresh(actionOneButton, key);
		String text = key.getRegisteredKey();
		actionOneButton = getKeyboardButton(text, 625, 0, onPress);
		LAYER.MENU.addObject(actionOneButton);
	}

	private void createActionTwoKeyButton() {
		Key key = new ActionTwoKey();
		GenericListener onPress = () -> refresh(actionTwoButton, key);
		String text = key.getRegisteredKey();
		actionTwoButton = getKeyboardButton(text, 625, 104, onPress);
		LAYER.MENU.addObject(actionTwoButton);
	}

	private void createActionThreeKeyButton() {
		Key key = new ActionThreeKey();
		GenericListener onPress = () -> refresh(actionThreeButton, key);
		String text = key.getRegisteredKey();
		actionThreeButton = getKeyboardButton(text, 625, 208, onPress);
		LAYER.MENU.addObject(actionThreeButton);
	}
	
	private void createDefaultButton() {
		GenericListener onPress = () -> resetKeyboard();
		String text = new Translatable().getTranslatedText("Default", file);
		ButtonObject button = new TitleScreenButton(text, 1050, 503 + 312, 420, 80, onPress, null);
		LAYER.MENU.addObject(button);
	}

	////////// BUTTON ACTION ////////////

	private void refresh(ButtonObject button, Key key) {
		button.getHandler().removeObject(button);
		EnterKeyButton keyButton = new EnterKeyButton(button.getX(), button.getY() - 10, key);
		LAYER.MENU.addObject(keyButton);
	}

	private void resetKeyboard() {
		
		// up
		new UpKey().registerKey("Z", 90);
		new UpKey().refreshKey();
		
		// down
		new DownKey().registerKey("S", 83);
		new DownKey().refreshKey();
		
		// left
		new LeftKey().registerKey("Q", 81);
		new LeftKey().refreshKey();
		
		// right
		new RightKey().registerKey("D", 68);
		new RightKey().refreshKey();
		
		// action 1
		new ActionOneKey().registerKey("E", 69);
		new ActionOneKey().refreshKey();
		
		// action 2
		new ActionTwoKey().registerKey("R", 82);
		new ActionTwoKey().refreshKey();
		
		// action 3
		new ActionThreeKey().registerKey("F", 70);
		new ActionThreeKey().refreshKey();
		
		// refresh Menu
		new ControlsScreen();
	}

}
