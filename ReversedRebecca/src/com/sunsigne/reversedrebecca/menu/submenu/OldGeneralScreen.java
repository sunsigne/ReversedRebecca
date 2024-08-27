package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class GeneralScreen extends SubMenuScreen {

	public GeneralScreen() {
		super(RESET);
		loadText();
		
		createResetButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "general";
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText resetDetail;
		int x = 325 + 416;
		int y = 503 + 104;

		// your progress will be ...
		text = translate("ResetDetail" + "1");
		resetDetail = new TitleScreenText(text, x, y + 146 - 56);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... permanently lost
		text = translate("ResetDetail" + "2");
		resetDetail = new TitleScreenText(text, x, y + 181 - 56);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen(BACK);
	}

	////////// BUTTONS ////////////

	private void createResetButton() {
		GenericListener onPress = () -> new ResetScreen();
		ButtonObject button = new TitleScreenButton(translate("Reset"), 741, 607, 415, 80, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		LAYER.MENU.addObject(button);
		buttons.put(RESET, button);
	}

	////////// PRESET MOUSE POS ////////////
	
	public static final PresetMousePos RESET = new PresetMousePos(925, 650);
	
	////////// GAMEPAD ////////////
	
	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;
	
		if (isPresetNull())
			setPreset(RESET);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}
		
		else if (getPreset() == RESET)
			resetPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}
	
	private void resetPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(RESET).mousePressed(null);
	}
	
	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(RESET);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}
		
}
