package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.EnterKeyButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.Key;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class ControlsIngameScreen extends MenuIngameSubMenuScreen {

	public ControlsIngameScreen(boolean actionKeyScreen) {
		this();
		if (actionKeyScreen)
			displayActionsKeys();
	}

	public ControlsIngameScreen() {
		super(BACK);
		loadText();
		createButtons();
		displayControlsKeys();
	}

	protected static final int y_gap = -60;

	////////// NAME ////////////

	@Override
	public String getName() {
		return "controls_xl";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuIngameScreen getPreviousMenu() {
		// return new OptionsIngameScreen(BACK);
		return new ResumeScreen();
	}

	////////// TEXT ////////////

	private TitleScreenText firstLine;
	private TitleScreenText secondLine;
	private TitleScreenText thirdLine;
	private TitleScreenText fourthLine;

	private void loadText() {
		int x = 525;
		int y = 323 + y_gap;

		firstLine = new TitleScreenText("", x, y);
		secondLine = new TitleScreenText("", x, y + 104);
		thirdLine = new TitleScreenText("", x, y + 208);
		fourthLine = new TitleScreenText("", x, y + 312);

		LAYER.MENU.addObject(firstLine);
		LAYER.MENU.addObject(secondLine);
		LAYER.MENU.addObject(thirdLine);
		LAYER.MENU.addObject(fourthLine);
	}

	////////// BUTTONS ////////////

	private void createButtons() {
		createUpKeyButton();
		createDownKeyButton();
		createLeftKeyButton();
		createRightKeyButton();
		createDialogueKeyButton();
		createActionOneKeyButton();
		createActionTwoKeyButton();
		createActionThreeKeyButton();

		createDefaultButton();
		createLeftArrowButton();
		createRightArrowButton();
	}

	private ButtonObject getKeyboardButton(String text, int x, int y, GenericListener onPress) {
		return new TitleScreenButton(text, 1095 + x, 323 + y + y_gap, 150, 80, onPress, null);
	}

	private ButtonObject upButton;

	private void createUpKeyButton() {
		Key key = new UpKey();
		GenericListener onPress = () -> refresh(upButton, key, false);
		String text = key.getRegisteredKey();
		upButton = getKeyboardButton(text, 0, 0, onPress);
	}

	private ButtonObject downButton;

	private void createDownKeyButton() {
		Key key = new DownKey();
		GenericListener onPress = () -> refresh(downButton, key, false);
		String text = key.getRegisteredKey();
		downButton = getKeyboardButton(text, 0, 104, onPress);
	}

	private ButtonObject leftButton;

	private void createLeftKeyButton() {
		Key key = new LeftKey();
		GenericListener onPress = () -> refresh(leftButton, key, false);
		String text = key.getRegisteredKey();
		leftButton = getKeyboardButton(text, 0, 208, onPress);
	}

	private ButtonObject rightButton;

	private void createRightKeyButton() {
		Key key = new RightKey();
		GenericListener onPress = () -> refresh(rightButton, key, false);
		String text = key.getRegisteredKey();
		rightButton = getKeyboardButton(text, 0, 312, onPress);
	}

	private ButtonObject dialogueButton;

	private void createDialogueKeyButton() {
		Key key = new DialogueKey();
		GenericListener onPress = () -> refresh(dialogueButton, key, true);
		String text = key.getRegisteredKey();
		dialogueButton = getKeyboardButton(text, 0, 0, onPress);
	}

	private ButtonObject actionOneButton;

	private void createActionOneKeyButton() {
		Key key = new ActionOneKey();
		GenericListener onPress = () -> refresh(actionOneButton, key, true);
		String text = key.getRegisteredKey();
		actionOneButton = getKeyboardButton(text, 0, 104, onPress);
	}

	private ButtonObject actionTwoButton;

	private void createActionTwoKeyButton() {
		Key key = new ActionTwoKey();
		GenericListener onPress = () -> refresh(actionTwoButton, key, true);
		String text = key.getRegisteredKey();
		actionTwoButton = getKeyboardButton(text, 0, 208, onPress);
	}

	private ButtonObject actionThreeButton;

	private void createActionThreeKeyButton() {
		Key key = new ActionThreeKey();
		GenericListener onPress = () -> refresh(actionThreeButton, key, true);
		String text = key.getRegisteredKey();
		actionThreeButton = getKeyboardButton(text, 0, 312, onPress);
	}

	private void createDefaultButton() {
		GenericListener onPress = () -> resetKeyboard();
		ButtonObject button = new TitleScreenButton(translate("Default"), 750, 450 + 312 + y_gap, 420, 80, onPress,
				null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		LAYER.MENU.addObject(button);
	}

	private ButtonObject createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 920 + x, 460 + 312 + y_gap, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		return button;
	}

	private ButtonObject leftArrowButton;

	private void createLeftArrowButton() {
		GenericListener onPress = () -> displayControlsKeys();
		leftArrowButton = createArrowButton("<", -370, onPress);
	}

	private ButtonObject rightArrowButton;

	private void createRightArrowButton() {
		GenericListener onPress = () -> displayActionsKeys();
		rightArrowButton = createArrowButton(">", 370, onPress);
	}

	////////// BUTTON ACTION ////////////

	private void refresh(ButtonObject button, Key key, boolean actionKey) {
		var handler = button.getHandler();
		if (handler != null)
			handler.removeObject(button);

		EnterKeyButton keyButton = new EnterKeyButton(button.getX(), button.getY() - 10, key, actionKey, true);
		LAYER.MENU.addObject(keyButton);
	}

	private boolean actionKeyScreen;

	private void displayControlsKeys() {
		actionKeyScreen = false;
		firstLine.setText(translate("Up"));
		secondLine.setText(translate("Down"));
		thirdLine.setText(translate("Left"));
		fourthLine.setText(translate("Right"));

		LAYER.MENU.getHandler().softRemoveObject(dialogueButton);
		LAYER.MENU.getHandler().softRemoveObject(actionOneButton);
		LAYER.MENU.getHandler().softRemoveObject(actionTwoButton);
		LAYER.MENU.getHandler().softRemoveObject(actionThreeButton);
		LAYER.MENU.getHandler().softRemoveObject(leftArrowButton);

		LAYER.MENU.getHandler().addObject(upButton);
		LAYER.MENU.getHandler().addObject(downButton);
		LAYER.MENU.getHandler().addObject(leftButton);
		LAYER.MENU.getHandler().addObject(rightButton);
		LAYER.MENU.getHandler().addObject(rightArrowButton);
	}

	private void displayActionsKeys() {
		actionKeyScreen = true;
		firstLine.setText(translate("Dialogue"));
		secondLine.setText(translate("Action1"));
		thirdLine.setText(translate("Action2"));
		fourthLine.setText(translate("Action3"));

		LAYER.MENU.getHandler().softRemoveObject(upButton);
		LAYER.MENU.getHandler().softRemoveObject(downButton);
		LAYER.MENU.getHandler().softRemoveObject(leftButton);
		LAYER.MENU.getHandler().softRemoveObject(rightButton);
		LAYER.MENU.getHandler().softRemoveObject(rightArrowButton);

		LAYER.MENU.getHandler().addObject(dialogueButton);
		LAYER.MENU.getHandler().addObject(actionOneButton);
		LAYER.MENU.getHandler().addObject(actionTwoButton);
		LAYER.MENU.getHandler().addObject(actionThreeButton);
		LAYER.MENU.getHandler().addObject(leftArrowButton);
	}

	private void resetKeyboard() {

		new UpKey().registerKey("Z", 90);
		new DownKey().registerKey("S", 83);
		new LeftKey().registerKey("Q", 81);
		new RightKey().registerKey("D", 68);
		new DialogueKey().registerKey("SPACE", 32);
		new ActionOneKey().registerKey("E", 69);
		new ActionTwoKey().registerKey("R", 82);
		new ActionThreeKey().registerKey("F", 70);

		new ControlsIngameScreen(actionKeyScreen);
	}

	////////// PRESET MOUSE POS ////////////

	// public static final PresetMousePos EXEMPLE = new PresetMousePos(730, 650);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
