package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.menu.DemoObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class DemoScreen extends SubMenuScreen {

	public DemoScreen() {
		this(BACK, 0);
	}

	protected DemoScreen(PresetMousePos defaultPreset, int listStart) {
		super(defaultPreset);
		loadText();
		loadDemos();

		customBackButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "demo";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.PLAY);
	}

	////////// TEXT ////////////

	private void loadText() {
		TitleScreenText demo = new TitleScreenText(translate("demo"), Window.WIDHT / 2 - 208, 22);
		LAYER.MENU.addObject(demo);
	}

	////////// DEMOS ////////////

	private void loadDemos() {
		boolean female = LivingOption.getType() == LIVING_TYPE.FEMALE;
		boolean male = LivingOption.getType() == LIVING_TYPE.MALE;
		
		String alexia = male ? "alexia_alexis" : "alexia_alicia";
		String nolancy = male ? "nolancy_nolan" : "nolancy_nancy";
		String stephabrina = female ? "stephabrina_sabrina" : "stephabrina_stephan";
		String dougly = female ? "dougly_dolly" : "dougly_doug";
		
		loadDemo("sarah", 0, 0);
		loadDemo("camille", 1, 0);
		loadDemo("delta", 2, 0);
		loadDemo(alexia, 0, 1);
		loadDemo("erika", 1, 1);
		loadDemo(dougly, 2, 1);
		loadDemo("nathan", 0, 2);
		loadDemo(nolancy, 1, 2);
		loadDemo(stephabrina, 2, 2);
	}

	private void loadDemo(String name, int col, int row) {
		DemoObject demo_object = new DemoObject(name, Size.L - 10 + col * 6 * Size.M, 421 + row * 185);
		LAYER.MENU.addObject(demo_object);
	}

	////////// BUTTONS ////////////

	@Override
	protected String getBackButtonText() {
		return translate("QuitButton");
	}

	private void customBackButton() {
		getBackButton().setRectsize(RECTSIZE.CUSTOM_BACK_BUTTON);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);
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
