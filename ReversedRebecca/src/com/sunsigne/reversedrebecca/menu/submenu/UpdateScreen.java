package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.WebTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class UpdateScreen extends SubMenuScreen {

	public UpdateScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		loadText();

		createClickHereButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "update";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.PLAY);
	}

	////////// TEXT ////////////

	private TitleScreenText updateText;
	private TitleScreenText updateInfo;

	private void loadText() {
		int x = 325 + 416;
		int y = 503;

		// game update available
		updateText = new TitleScreenText(translate("UpdateText"), x, y - 40);
		updateText.setFontSize(20f);
		LAYER.MENU.addObject(updateText);

		// new level : police station
		updateInfo = new TitleScreenText(translate("UpdateInfo"), x, y + 356);
		updateInfo.setFontSize(20f);
		LAYER.MENU.addObject(updateInfo);

	}

	////////// BUTTONS ////////////

	private void createClickHereButton() {
		int x = 325 + 416;
		int y = 503;

		GenericListener onPress = () -> new WebTask().openHtml(WebTask.ITCHIO_LINK);
		String text = translate("UpdateButton");
		TitleScreenButton button = new TitleScreenButton(text, x, y + 18, 415, 80, onPress, null);
		button.setFontSize(30f);
		LAYER.MENU.addObject(button);
		buttons.put(CLICK, button);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos CLICK = new PresetMousePos(925, 560);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(CLICK);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == CLICK)
			clickPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void clickPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CLICK).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(CLICK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
