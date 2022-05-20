package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.FlagLangageButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.Feeling.CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.physic.natural.independant.FadeMenuLaw;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	public TitleScreen() {
		LAYER.MENU.addObject(this);
		loadImages();

		createPlayButton();
		createOptionsButton();
		createQuitButton();
		createFlagLanguageButton();
	}

	////////// BUTTONS ////////////

	///// start /////

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("PlayButton", file);

		ButtonObject playButton = new TitleScreenButton(text, 140, 840, 420, 140, onPress, onRelease);
		LAYER.MENU.addObject(playButton);
	}

	private void startWorld() {
		String currentlvl = new Save().getLevel(false);

		// if currentlvlmenu and currentlvl are the same
		if (new Save().getLevel(true).equalsIgnoreCase(currentlvl)) {
			fadeMenu();
			userCanControlPlayer();
			return;
		}

		// else do a classical loading
		LAYER.MENU.getHandler().clear();
		new World(currentlvl);
	}

	private void fadeMenu() {
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		((FadeMenuLaw) law).setFading(true);
	}

	private void userCanControlPlayer() {
		Player player = new PlayerFinder().getPlayer();
		int time = player.getCondition() == CONDITION.BED ? 4 : 0;
		
		GenericListener listener = () -> new PlayerFinder().setUserAllowedToControlPlayer(true);
		new GameTimer(time * Game.SEC, listener);
	}

	///// options /////

	private void createOptionsButton() {
		GenericListener onPress = null;
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("OptionsButton", file);

		ButtonObject optionsButton = new TitleScreenButton(text, 740, 840, 420, 140, onPress, onRelease);
		LAYER.MENU.addObject(optionsButton);
	}

	///// quit /////

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		GenericListener onRelease = null;

		String text = new Translatable().getTranslatedText("QuitButton", file);

		ButtonObject quitButton = new TitleScreenButton(text, 1340, 840, 420, 140, onPress, onRelease);
		LAYER.MENU.addObject(quitButton);
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

	////////// TEXTURE ////////////

	private BufferedImage title_img;

	private void loadImages() {
		drawRebeccasRoom();
		title_img = new ImageTask().loadImage("textures/menu/" + "title");
	}

	private void drawRebeccasRoom() {
		new World(new Save().getLevel(true));
		new PlayerFinder().setUserAllowedToControlPlayer(false);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawBackground(g2d);
		g.drawImage(title_img, 530, 80, 856, 380, null);
	}

	public void drawBackground(Graphics2D g2d) {
		int alpha = 155;
		Color purple = new Color(0, 25, 195, alpha);
		Color black = new Color(0, 0, 0, alpha);

		GradientPaint up_paint = new GradientPaint(0, 0, purple, 0, Window.HEIGHT / 4, black);
		g2d.setPaint(up_paint);
		g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT / 2);

		GradientPaint down_paint = new GradientPaint(0, 3 * Window.HEIGHT / 4, black, 0, Window.HEIGHT, purple);
		g2d.setPaint(down_paint);
		g2d.fillRect(0, Window.HEIGHT / 2, Window.WIDHT, Window.HEIGHT / 2);
	}

}
