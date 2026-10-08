package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.BonusScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.object.menu.DrawingButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class DrawingsScreen extends SubMenuScreen {

	public DrawingsScreen() {
		super(BACK);

		createSadisticButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "drawings_xll";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new BonusScreen(SADISTIC_NERD);
	}

	////////// BUTTONS ////////////

	private int row;

	private void createDrawingsButton(String text, PresetMousePos preset) {
		createDrawingsButton(text, preset, 0, 0);
	}
	
	private void createDrawingsButton(String text, PresetMousePos preset, int x0, int y0) {
		row++;

		ButtonObject button = new DrawingButtonObject(text, 250, 136 + row * 72, x0, y0);
		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
	}

	private void createSadisticButton() {
		createDrawingsButton("head_sock", SADISTIC_NERD);
		createDrawingsButton("head_sock_color", SADISTIC_NERD);
		createDrawingsButton("lockpicking", SADISTIC_NERD);
		createDrawingsButton("sadistic_nerd", SADISTIC_NERD);
		createDrawingsButton("alicia_hug", SADISTIC_NERD);
		createDrawingsButton("dave_chair", SADISTIC_NERD, -15, 50);
		createDrawingsButton("el_coffee", SADISTIC_NERD, 60, -55);
		createDrawingsButton("el_donut", SADISTIC_NERD, 60, -55);
		createDrawingsButton("armed_duo", SADISTIC_NERD, -15, 40);
	}

	////////// BUTTON ACTION ////////////

	////////// RENDER ////////////

	@Override
	protected void drawTitle(Graphics g) {

	}

	@Override
	protected void drawVersion(Graphics g) {

	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos SADISTIC_NERD = new PresetMousePos(945, 590);

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
