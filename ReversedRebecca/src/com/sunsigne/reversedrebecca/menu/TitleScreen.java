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
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class TitleScreen extends MenuScreen {

	private static final MENU menu = MENU.MAIN;

	public TitleScreen() {
		super(menu);
	}

	@Override
	protected void createNewMenu() {
		loadImages();

		createPlayButton();
		createOptionsButton();
		createQuitButton();
		createFlagLanguageButton();
	}

	////////// BUTTONS ////////////

	private TitleScreenButton createTitleScreenButton(String text, int x, GenericListener onPress) {
		return new TitleScreenButton(text, x, 840, 420, 140, onPress, null);
	}

	private void createPlayButton() {
		GenericListener onPress = () -> startWorld();
		String text = new Translatable().getTranslatedText("PlayButton", file);
		ButtonObject button = createTitleScreenButton(text, 140, onPress);
		menu.getHandler().addObject(button);
	}

	private void createOptionsButton() {
		GenericListener onPress = null;
		String text = new Translatable().getTranslatedText("OptionsButton", file);
		ButtonObject button = createTitleScreenButton(text, 740, onPress);
		menu.getHandler().addObject(button);
	}

	private void createQuitButton() {
		GenericListener onPress = () -> new Conductor().stopApp();
		String text = new Translatable().getTranslatedText("QuitButton", file);
		ButtonObject button = createTitleScreenButton(text, 1340, onPress);
		menu.getHandler().addObject(button);
	}

	protected void createFlagLanguageButton() {
		GenericListener onPress = () -> openLanguageScreen();
		ButtonObject button = new FlagLangageButton(onPress, null);
		menu.getHandler().addObject(button);
	}

	////////// WORLD ////////////

	private void startWorld() {
		String currentlvl = new Save().getLevel(false);

		// if currentlvlmenu and currentlvl are the same
		if (new Save().getLevel(true).equalsIgnoreCase(currentlvl)) {
			loadLvlMenu();
			return;
		}

		// else load classical level
		clearAll();
		new World(currentlvl);
	}

	private void loadLvlMenu() {

		// fade menu
		PhysicLaw law = PhysicList.getList().getObject(new FadeMenuLaw());
		((FadeMenuLaw) law).setFading(true);

		// user can control player
		Player player = new PlayerFinder().getPlayer();
		int time = player.getCondition() == CONDITION.BED ? 4 : 0; // if in bed, must awake first
		GenericListener listener = () -> new PlayerFinder().setUserAllowedToControlPlayer(true);
		new GameTimer(time * Game.SEC, listener);
	}

	private void openLanguageScreen() {
		for (Updatable tempUpdatable : menu.getHandler().getList()) {
			if (tempUpdatable instanceof FlagLangageButton == false)
				continue;

			tempUpdatable.getHandler().removeObject(tempUpdatable);
			break;
		}
		new LanguageScreen();
	}

	////////// TEXTURE ////////////

	private BufferedImage title_img;

	private void loadImages() {

		if (World.get() == null)
			drawRebeccasRoom();

		if (title_img == null)
			drawTitle();
	}

	private void drawRebeccasRoom() {
		new World(new Save().getLevel(true));
		new PlayerFinder().setUserAllowedToControlPlayer(false);
	}

	private void drawTitle() {
		title_img = new ImageTask().loadImage("textures/menu/" + "title");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawTransluantLayer(g2d);
		drawTitle(g);
	}

	private void drawTransluantLayer(Graphics2D g2d) {
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

	private void drawTitle(Graphics g) {
		g.drawImage(title_img, 530, 80, 856, 380, null);
	}

}
