package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.FlagLangageButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen implements Updatable, TickFree {

	private String file = "menu.csv";
	
	public TitleScreen() {
		LAYER.MENU.addObject(this);

		createPlayButton();
		createOptionsButton();
		createFlagLanguageButton();
		createQuitButton();
	}

	////////// BUTTONS ////////////

	///// start /////

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("PlayButton", file);

		ButtonObject playButton = new TitleScreenButton(text, 710, 570, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(playButton);
	}

	private void startWorld() {
		LAYER.MENU.getHandler().clear();
		new World("lvl001");
	}

	///// options /////

	private void createOptionsButton() {
		GenericListener onPress = null;
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("OptionsButton", file);

		ButtonObject optionsButton = new TitleScreenButton(text, 710, 740, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(optionsButton);
	}

	///// flag /////

	private void createFlagLanguageButton() {
		GenericListener onPress = () -> openLangScreen();
		GenericListener onRelease = null;

		ButtonObject flagLanguageButton = new FlagLangageButton(onPress, onRelease);
		LAYER.MENU.addObject(flagLanguageButton);
	}

	private void openLangScreen() {
		for (Updatable tempUpdatable : LAYER.MENU.getHandler().getList()) {
			
			tempUpdatable.destroyControls();
			
			if (tempUpdatable instanceof FlagLangageButton)
				LAYER.MENU.getHandler().removeObject(tempUpdatable);
		}
		new LanguageScreen();
	}

	///// quit /////

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("QuitButton", file);

		ButtonObject quitButton = new TitleScreenButton(text, 710, 920, 500, 90, onPress, onRelease);
		LAYER.MENU.addObject(quitButton);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint = new GradientPaint(0, 0, new Color(220, 220, 255), 0, Window.HEIGHT, new Color(0, 0, 200));
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
	}

}
