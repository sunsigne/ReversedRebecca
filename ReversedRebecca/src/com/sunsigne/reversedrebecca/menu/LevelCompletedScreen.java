package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeSound;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.Counter;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelStats;
import com.sunsigne.reversedrebecca.world.lvlstats.YOUARE;

public class LevelCompletedScreen implements Updatable, GamepadEvent {

	public LevelCompletedScreen(String nextLvl) {
		this.nextLvl = nextLvl;

		loadImages();
		loadMusic();

		loadStats();
		loadFont();
		loadText();
		createContinueButton();
	}

	private void loadMusic() {
		boolean isPsycopath = World.get().getLevelStats().getYouAre() == YOUARE.PSYCHOPATH;
		String name = isPsycopath ? "absolute_evilness" : "victory_screen";
		new SoundTask().playMusic(name, false, false);
	}

	////////// USEFUL ////////////

	private String format(String text) {
		return new FormattedString().getNoSpecialCharacter(text).toUpperCase();
	}

	////////// DATA ////////////

	private Counter counter1;
	private Counter counter2;
	private Counter counter3;

	private String goodDeed_stats;
	private String badDeed_stats;
	private String you_are_stats;

	private void loadStats() {
		LevelStats stats = World.get().getLevelStats();

		counter1 = stats.getCounter(1);
		counter1.setName(format(counter1.getName()));
		counter2 = stats.getCounter(2);
		counter2.setName(format(counter2.getName()));
		counter3 = stats.getCounter(3);
		counter3.setName(format(counter3.getName()));

		goodDeed_stats = format(stats.getDeed().getGoodText());
		badDeed_stats = format(stats.getDeed().getBadText());

		String you_are_stats_raw = stats.getYouAre().getName();
		String you_are_stats_unformatted = new Translatable().getTranslatedText("LevelYouAre" + you_are_stats_raw,
				FilePath.MENU);
		you_are_stats = format(you_are_stats_unformatted);
	}

	////////// TICK ////////////

	private int time;
	private boolean flag;

	@Override
	public void tick() {
		time++;

		int delay = 30;
		SoundTask sound = new SoundTask();
		double volume = VolumeSound.getVolume() / 2;

		if (time == 90)
			sound.playSound(SOUNDTYPE.SOUND, "jump");
		if (time == 120)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);
		if (time == 120 + delay)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);
		if (time == 120 + 2 * delay)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);
		if (time == 120 + 3 * delay)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);
		if (time == 120 + 4 * delay)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);
		if (time == 120 + 5 * delay)
			sound.play(SOUNDTYPE.SOUND, volume, "button_validate", false, false);

		if (isYouAreReady && flag == false) {
			flag = true;
			GenericListener listener = () -> {
				isYouAreReadyConfirmed = true;
				sound.playSound(SOUNDTYPE.SOUND, "button");
			};
			new GameTimer(20, true, listener);
		}
	}

	////////// FONT ////////////

	private Font title_font;
	private Font text_font;
	private Font you_are_font;
	private Font saved_font;

	private void loadFont() {
		title_font = new FontTask().createNewFont("F5.6-Regular.otf", 80f);
		text_font = new FontTask().createNewFont("F5.6-Regular.otf", 40f);
		you_are_font = new FontTask().createNewFont("F5.6-Regular.otf", 55f);
		saved_font = new FontTask().createNewFont("F5.6-Regular.otf", 25f);
	}

	////////// TEXT ////////////

	private String title_text;
	private String goodDeed_text;
	private String badDeed_text;
	private String click_text;
	private String saved_text;

	private void loadText() {
		title_text = format(new Translatable().getTranslatedText("LevelCompleted", FilePath.MENU));
		goodDeed_text = format(new Translatable().getTranslatedText("LevelGoodDeed", FilePath.MENU)) + " :";
		badDeed_text = format(new Translatable().getTranslatedText("LevelBadDeed", FilePath.MENU)) + " :";
		click_text = format(new Translatable().getTranslatedText("LevelClickToContinue", FilePath.MENU));
		saved_text = format(new Translatable().getTranslatedText("ProgressSaved", FilePath.MENU));
	}

	////////// TEXTURE ////////////

	private BufferedImage karma_image;
	private BufferedImage karma_cursor;

	private void loadImages() {
		String path = "textures/menu/";

		karma_image = new ImageTask().loadImage(path + "karma");
		karma_cursor = new ImageTask().loadImage(path + "karma_cursor");
	}

	////////// RENDER ////////////

	private boolean isYouAreReady;
	private boolean isYouAreReadyConfirmed;

	@Override
	public void render(Graphics g) {

		// title

		int delay = 40;
		if (time >= delay)
			drawGrayLayer(g, delay);
		if (time >= 90)
			drawTitle(g);

		// counter 1

		int count = 0;
		int speed = time;
		delay = 30;

		if (time >= 120) {
			drawText(g, counter1.getName() + " :", 0);
			speed = (time - 120) * (int) Math.sqrt(counter1.getCount()) / 10;
			count = Math.min(counter1.getCount(), speed);
			drawStats(g, String.valueOf(count), 0);
		}

		// counter 2

		if (time >= 120 + delay) {
			drawText(g, counter2.getName() + " :", 70);
			speed = (time - 120 - delay) * (int) Math.sqrt(counter2.getCount()) / 10;
			count = Math.min(counter2.getCount(), speed);
			drawStats(g, String.valueOf(counter2.getCount()), 70);
		}

		// counter 3

		if (time >= 120 + delay * 2) {
			drawText(g, counter3.getName() + " :", 140);
			speed = (time - 120 - delay * 2) * (int) Math.sqrt(counter3.getCount()) / 10;
			count = Math.min(counter3.getCount(), speed);
			drawStats(g, String.valueOf(counter3.getCount()), 140);
		}

		// deeds

		if (time >= 120 + delay * 3)
			drawDeed(g, goodDeed_text, goodDeed_stats, 295);
		if (time >= 120 + delay * 4)
			drawDeed(g, badDeed_text, badDeed_stats, 455);

		// karma

		if (time >= 120 + delay * 5)
			drawKarma(g, 120 + delay * 5);

		// you are

		if (isYouAreReadyConfirmed) {
			drawYouAre(g);

			if (time / 40 % 2 == 0)
				drawClickToContinue(g);
		}

		drawProgressSaved(g);
	}

	private void drawGrayLayer(Graphics g, int delay) {
		Color gray = new Color(40, 40, 40, 224);
		g.setColor(gray);
		int speed = 20 * (time - delay);
		g.fillRect(Math.min(0, -Window.WIDHT / 2 + speed), 0, Window.WIDHT / 2, Window.HEIGHT);
		g.fillRect(Math.max(0, Window.WIDHT / 2 - speed) + Window.WIDHT / 2, 0, Window.WIDHT / 2, Window.HEIGHT);
	}

	private void drawTitle(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 90, 0, 0 };
		new TextDecoration().drawOutlinesString(g, title_font, title_text, Color.ORANGE, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	private void drawText(Graphics g, String text, int y) {
		int[] rect = new int[] { Window.WIDHT / 2 - 80, 250 + y, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, text, DIRECTION.NULL, rect);
	}

	private void drawStats(Graphics g, String text, int y) {
		int[] rect = new int[] { Window.WIDHT / 2 + 290, 250 + y, 0, 0 };
		Color yellow = new Color(235, 235, 100);
		new TextDecoration().drawOutlinesString(g, text_font, text, yellow, Color.BLACK, DIRECTION.RIGHT, rect);
	}

	private void drawDeed(Graphics g, String text, String stat, int y) {
		int[] rect = new int[] { Window.WIDHT / 2 - 80 - 12 * stat.length(), 250 + y, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, text, DIRECTION.NULL, rect);

		rect = new int[] { Window.WIDHT / 2 + 160 - 8 * stat.length(), 250 + y, 0, 0 };
		Color yellow = new Color(235, 235, 100);
		new TextDecoration().drawOutlinesString(g, text_font, stat, yellow, Color.BLACK, DIRECTION.LEFT, rect);
	}

	private void drawKarma(Graphics g, int delay) {
		int w = 777;
		g.drawImage(karma_image, (Window.WIDHT - w) / 2, 595, w, 61, null);

		if (time <= delay + 30)
			delay = time;
		else
			delay = delay + 30;

		int karma_gap = getKarmaGap();
		int karma_pos = 0;

		if (karma_gap > 0)
			karma_pos = (int) Math.min(karma_gap, 2.8f * (time - delay));
		if (karma_gap < 0)
			karma_pos = (int) Math.max(karma_gap, -2.8f * (time - delay));

		if (karma_pos == karma_gap)
			isYouAreReady = true;

		g.drawImage(karma_cursor, karma_pos + 2 + (Window.WIDHT - Size.M) / 2, 580, Size.M, Size.M, null);
	}

	private boolean flag1;

	private int getKarmaGap() {
		YOUARE youare = World.get().getLevelStats().getYouAre();
		int gap = 155;
		int karmaGap = 0;

		switch (youare) {

		case PSYCHOPATH:
			karmaGap = (int) (-3.2f * gap);
			break;
		case SADISTIC:
			karmaGap = -2 * gap;
			break;
		case MEAN:
			karmaGap = -gap;
			break;
		case NICE:
			karmaGap = gap;
			break;
		case ANGELIC:
			karmaGap = 2 * gap;
			break;
		case NEUTRAL:
		default:
			karmaGap = 0;
			break;
		}

		String sound = "karma_" + youare.getName().toLowerCase();

		if (flag1)
			sound = null;
		else
			flag1 = true;

		new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
		return karmaGap;
	}

	private void drawYouAre(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 875, 0, 0 };
		new TextDecoration().drawOutlinesString(g, you_are_font, you_are_stats, Color.ORANGE, Color.BLACK,
				DIRECTION.NULL, rect);
	}

	private void drawClickToContinue(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 1040, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, click_text, Color.LIGHT_GRAY, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	private void drawProgressSaved(Graphics g) {
		int[] rect = new int[] { 20, 30, 0, 0 };
		new TextDecoration().drawOutlinesString(g, saved_font, saved_text, Color.LIGHT_GRAY, Color.BLACK,
				DIRECTION.LEFT, rect);
	}

	////////// BUTTON ////////////

	private String nextLvl;

	private void createContinueButton() {
		GenericListener onPress = () -> loadNextLvl();

		ButtonObject button = new ButtonObject(null, 0, 0, Window.WIDHT, Window.HEIGHT, onPress, null) {
			@Override
			public void render(Graphics g) {

			}
		};
		LAYER.MENU.addObject(button);
	}

	private void loadNextLvl() {
		if (isYouAreReadyConfirmed == false) {
			isYouAreReadyConfirmed = flag = flag1 = true;
			time = 500;
			return;
		}

		LAYER.MENU.getHandler().clear();
		new World(nextLvl);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		loadNextLvl();
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
