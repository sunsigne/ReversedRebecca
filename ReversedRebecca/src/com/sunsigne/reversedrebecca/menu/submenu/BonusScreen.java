package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.menu.submenu.options.GeneralScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class BonusScreen extends SubMenuScreen {

	public BonusScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);

		createPixelArtButton();
		createDrawingsButton();
		createBDButton();
		createSettingsButton();
		createCreditsButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "bonus";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.BONUS);
	}

	////////// BUTTONS ////////////

	private void createBonusScreenButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 503 + y, 415, 80, onPress, null);
		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
	}

	private void createPixelArtButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createBonusScreenButton(translate("PixelArtButton"), PIXEL_ART, 206, 51, onPress);
	}

	private void createDrawingsButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createBonusScreenButton(translate("DrawingsButton"), DRAWINGS, 623, 51, onPress);
	}

	private void createBDButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createBonusScreenButton(translate("BDButton"), BD, 206, 155, onPress);
	}

	private void createSettingsButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createBonusScreenButton(translate("SettingsButton"), SETTINGS, 623, 155, onPress);
	}

	private void createCreditsButton() {
		GenericListener onPress = () -> new GeneralScreen();
		createBonusScreenButton(translate("CreditsButton"), CREDIT, 416, 259, onPress);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos PIXEL_ART = new PresetMousePos(735, 590);
	public static final PresetMousePos DRAWINGS = new PresetMousePos(1155, 590);
	public static final PresetMousePos BD = new PresetMousePos(735, 700);
	public static final PresetMousePos SETTINGS = new PresetMousePos(1155, 700);
	public static final PresetMousePos CREDIT = new PresetMousePos(945, 805);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(PIXEL_ART);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == PIXEL_ART)
			pixelArtPressed(e);
		else if (getPreset() == DRAWINGS)
			drawingsPressed(e);
		else if (getPreset() == BD)
			BDPressed(e);
		else if (getPreset() == SETTINGS)
			settingsPressed(e);
		else if (getPreset() == CREDIT)
			creditsPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void pixelArtPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BD);
		else if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(DRAWINGS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(PIXEL_ART).mousePressed(null);
	}

	private void drawingsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(SETTINGS);
		else if (e.getKey() == ButtonEvent.LEFT)
			setPreset(PIXEL_ART);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(DRAWINGS).mousePressed(null);
	}

	private void BDPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(PIXEL_ART);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(CREDIT);
		else if (e.getKey() == ButtonEvent.RIGHT)
			setPreset(SETTINGS);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BD).mousePressed(null);
	}

	private void settingsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(DRAWINGS);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(CREDIT);
		else if (e.getKey() == ButtonEvent.LEFT)
			setPreset(BD);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(SETTINGS).mousePressed(null);
	}

	private void creditsPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.UP)
			setPreset(BD);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CREDIT).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(CREDIT);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
