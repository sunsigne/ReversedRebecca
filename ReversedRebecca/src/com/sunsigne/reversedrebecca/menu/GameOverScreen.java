package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.PausePreventer;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GameOverScreen implements Updatable, PhysicFree, GamepadEvent {

	public GameOverScreen() {
		PausePreventer.state = null;
		new SoundTask().playMusic("3_words_and_a_smile", true, true);

		loadImages();
		loadFont();
		loadText();
		createContinueButton();
	}

	////////// USEFUL ////////////

	private String format(String text) {
		return new FormattedString().getNoSpecialCharacter(text).toUpperCase();
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		time++;
		if (time == 280)
			clickToContinueConfirmed = true;
	}

	////////// FONT ////////////

	private Font title_font;
	private Font text_font;

	private void loadFont() {
		title_font = new FontTask().createNewFont("F5.6-Regular.otf", 100f);
		text_font = new FontTask().createNewFont("F5.6-Regular.otf", 40f);
	}

	////////// TEXT ////////////

	private String gameover_text;
	private String click_text;

	private void loadText() {
		gameover_text = format(new Translatable().getTranslatedText("GameOver", FilePath.MENU));
		click_text = format(new Translatable().getTranslatedText("LevelClickToContinue", FilePath.MENU));
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private void loadImages() {
		String path = "textures/menu/";

		image = new ImageTask().loadImage(path + "game_over");
	}

	////////// RENDER ////////////

	private boolean clickToContinueConfirmed;

	@Override
	public void render(Graphics g) {

		// title

		int delay = 40;
		if (time >= delay)
			drawGrayLayer(g, delay);

		if (time >= 220) {
			drawGameOver(g);
			drawBlackHole(g);
		}

		// click to continue

		if (clickToContinueConfirmed && time / 71 % 2 == 0)
			drawClickToContinue(g);
	}

	private void drawGrayLayer(Graphics g, int delay) {
		int alpha = Math.min(224, time - delay);
		Color gray = new Color(40, 40, 40, alpha);
		g.setColor(gray);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
	}

	private void drawGameOver(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 100, 500, 0, 0 };
		new TextDecoration().drawOutlinesString(g, title_font, gameover_text, Color.WHITE, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	private void drawBlackHole(Graphics g) {
		g.drawImage(image, Window.WIDHT / 2 + 240, 330, 390, 390, null);
	}

	private void drawClickToContinue(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 1040, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, click_text, Color.LIGHT_GRAY, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	////////// BUTTON ////////////

	private void createContinueButton() {
		GenericListener onPress = () -> resetLvl();

		ButtonObject button = new ButtonObject(null, 0, 0, Window.WIDHT, Window.HEIGHT, onPress, null) {
			@Override
			public void render(Graphics g) {

			}

			@Override
			public String getSound() {
				return null;
			}
		};
		LAYER.MENU.addObject(button);
	}

	private void resetLvl() {
		if (clickToContinueConfirmed == false)
			return;

		if (DifficultyOption.getDifficulty() == GAME_DIFFICULTY.HARD) {
			sendToTitleScreen();
			return;
		}

		new World(new Save().getLevel(false));
	}

	private void sendToTitleScreen() {
		LAYER.LOADING.addObject(new LoadingScreen(true));
		LAYER.MENU.getHandler().clear();
		World.get().destroy();
		new Conductor().startGame();
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (ButtonEvent.isActionButton(e))
			resetLvl();
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
