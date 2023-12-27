package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.EnterKeyButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
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
		return new OptionsIngameScreen(BACK);
	}

	////////// TEXT ////////////

	private TitleScreenText firstLine;
	private TitleScreenText secondLine;
	private TitleScreenText thirdLine;
	private TitleScreenText fourthLine;

	private String menuPauseText;
	private String movementsText;
	private String action1Text;
	private String action2Text;
	private String action3Text;
	private String dialogueText;

	private String format(String text) {
		return new FormattedString().getNoSpecialCharacter(text).toUpperCase();
	}

	private void loadText() {
		int x = 525;
		int y = 323 + y_gap;

		firstLine = getTitleScreenText(x, y);
		secondLine = getTitleScreenText(x, y + 104);
		thirdLine = getTitleScreenText(x, y + 208);
		fourthLine = getTitleScreenText(x, y + 312);

		menuPauseText = format(translate("MenuPause"));
		movementsText = format(translate("Movements"));
		action1Text = format(translate("Action1"));
		action2Text = format(translate("Action2"));
		action3Text = format(translate("Action3"));
		dialogueText = format(translate("Dialogue"));

		LAYER.MENU.addObject(firstLine);
		LAYER.MENU.addObject(secondLine);
		LAYER.MENU.addObject(thirdLine);
		LAYER.MENU.addObject(fourthLine);
	}

	private TitleScreenText getTitleScreenText(int x, int y) {
		return new TitleScreenText("", x, y) {

			@Override
			public void render(Graphics g) {
				if (ControllerManager.getInstance().isUsingGamepad() == false)
					super.render(g);
			}
		};
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
		return new TitleScreenButton(text, 1095 + x, 323 + y + y_gap, 150, 80, onPress, null) {

			@Override
			public void render(Graphics g) {
				if (ControllerManager.getInstance().isUsingGamepad() == false)
					super.render(g);
			}
		};
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

			@Override
			public void render(Graphics g) {
				if (ControllerManager.getInstance().isUsingGamepad() == false)
					super.render(g);
			}
		};

		LAYER.MENU.addObject(button);
	}

	private ButtonObject createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 920 + x, 460 + 312 + y_gap, 60, 60, onPress, null) {

			@Override
			public void render(Graphics g) {
				if (ControllerManager.getInstance().isUsingGamepad() == false)
					super.render(g);
			}
		};
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
		LAYER.MENU.getHandler().removeObject(button);
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

	////////// TEXTURE ////////////

	private BufferedImage gamepad_image;

	protected BufferedImage get_gamepad_image() {
		if (gamepad_image == null)
			gamepad_image = new ImageTask().loadImage("textures/menu/" + "gamepad", true);
		return gamepad_image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (ControllerManager.getInstance().isUsingGamepad() == false) {
			super.render(g);
			return;
		}

		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
		g.drawImage(get_gamepad_image(), 580, 170, 730, 710, null);
		drawGamepadTexts(g);
	}

	private Font font = new FontTask().createNewFont("dogicabold.ttf", 35f);

	private void drawGamepadTexts(Graphics g) {
		var text = new TextDecoration();
		int[] rect;

		rect = new int[] { 888, 768, 150, 80 };
		text.drawOutlinesString(g, font, menuPauseText, DIRECTION.NULL, rect);
		rect = new int[] { 435, 298, 150, 80 };
		text.drawOutlinesString(g, font, movementsText, DIRECTION.RIGHT, rect);
		rect = new int[] { 1280, 162, 150, 80 };
		text.drawOutlinesString(g, font, action1Text, DIRECTION.LEFT, rect);
		rect = new int[] { 1280, 216, 150, 80 };
		text.drawOutlinesString(g, font, action2Text, DIRECTION.LEFT, rect);
		rect = new int[] { 1280, 270, 150, 80 };
		text.drawOutlinesString(g, font, action3Text, DIRECTION.LEFT, rect);
		rect = new int[] { 1305, 424, 150, 80 };
		text.drawOutlinesString(g, font, dialogueText, DIRECTION.LEFT, rect);
	}

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
