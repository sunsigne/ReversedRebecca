package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import com.sunsigne.reversedrebecca.menu.LoadingScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameController;
import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.world.World;

public class QuitScreen extends MenuIngameScreen {

	public QuitScreen() {
		super(QUIT);
		loadText();

		createQuitButton();
		createBackButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "quit";
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText quitText;
		int x = 325 + 416;
		int y = 343;

		text = translate("Quit" + "Detail" + "1");
		quitText = new TitleScreenText(text, x, y + 245);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);

		text = translate("Quit" + "Detail" + "2");
		quitText = new TitleScreenText(text, x, y + 280);
		quitText.setFontSize(18f);
		LAYER.MENU.addObject(quitText);
	}

	////////// BUTTONS ////////////

	private void createOptionScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress) {
		this.createOptionScreenButton(text, preset, x, y, onPress, "button");
	}

	private void createOptionScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress, String sound) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 343 + y, 415, 80, onPress, null) {
			public String getSound() {
				return sound;
			}
		};

		buttons.put(preset, button);
		LAYER.MENU.addObject(button);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> loadTitleScreen();
		createOptionScreenButton(translate("Confirm"), QUIT, 416, 51, onPress);
	}

	private void createBackButton() {
		GenericListener onPress = () -> new ResumeScreen();
		createOptionScreenButton(translate("Cancel"), BACK, 416, 155, onPress, "button_back");
	}

	////////// BUTTON ACTION ////////////

	private void loadTitleScreen() {
		LAYER.LOADING.addObject(new LoadingScreen());

		new MenuIngameController().unloadResumeScreen();
		World.get().destroy();
		new TitleScreen(TitleScreen.PLAY);

		LAYER.LOADING.getHandler().clear();
	}

	////////// PRESET MOUSE POS ////////////
	
	public static final PresetMousePos QUIT = new PresetMousePos(945, 430);
	public static final PresetMousePos BACK = new PresetMousePos(945, 535);
	
	////////// GAMEPAD ////////////
	
	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;
	
		if (isPresetNull())
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}
		
		else if (getPreset() == QUIT)
			quitPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}
	
	private void quitPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(QUIT).mousePressed(null);
	}
	
	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(QUIT);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
