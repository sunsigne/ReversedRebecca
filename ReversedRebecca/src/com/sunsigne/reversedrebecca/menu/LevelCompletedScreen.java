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
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.Counter;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelStats;
import com.sunsigne.reversedrebecca.world.lvlstats.YOUARE;

public class LevelCompletedScreen implements Updatable, TickFree {

	public LevelCompletedScreen(String lvl) {
		loadImages();
		loadMusic();

		loadStats();
		loadFont();
		loadText();
		createContinueButton(lvl);
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
		counter1.setName(format(counter1.getName()) + " :");
		counter2 = stats.getCounter(2);
		counter2.setName(format(counter2.getName()) + " :");
		counter3 = stats.getCounter(3);
		counter3.setName(format(counter3.getName()) + " :");

		goodDeed_stats = format(stats.getDeed().getGoodText());
		badDeed_stats = format(stats.getDeed().getBadText());

		String you_are_stats_raw = stats.getYouAre().getName();
		String you_are_stats_unformatted = new Translatable().getTranslatedText("LevelYouAre" + you_are_stats_raw,
				FilePath.MENU);
		you_are_stats = format(you_are_stats_unformatted);
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

	private void loadImages() {
		String path = "textures/menu/";

		karma_image = new ImageTask().loadImage(path + "karma");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawDarkGray(g, Window.WIDHT, Window.HEIGHT);

		drawTitle(g);
		drawText(g, counter1.getName(), 0);
		drawStats(g, String.valueOf(counter1.getCount()), 0);
		drawText(g, counter2.getName(), 70);
		drawStats(g, String.valueOf(counter2.getCount()), 70);
		drawText(g, counter3.getName(), 140);
		drawStats(g, String.valueOf(counter3.getCount()), 140);

		drawDeed(g, goodDeed_text, goodDeed_stats, 295);
		drawDeed(g, badDeed_text, badDeed_stats, 455);
		drawKarma(g);
		drawYouAre(g);
		drawClickToContinue(g);
		drawProgressSaved(g);
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

		rect = new int[] { Window.WIDHT / 2 + 140 - 8 * stat.length(), 250 + y, 0, 0 };
		Color yellow = new Color(235, 235, 100);
		new TextDecoration().drawOutlinesString(g, text_font, stat, yellow, Color.BLACK, DIRECTION.LEFT, rect);
	}

	private void drawKarma(Graphics g) {
		int w = 777;
		g.drawImage(karma_image, (Window.WIDHT - w) / 2, 595, w, 61, null);
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

	private void createContinueButton(String lvl) {
		GenericListener onPress = () -> loadNextLvl(lvl);

		ButtonObject button = new ButtonObject(null, 0, 0, Window.WIDHT, Window.HEIGHT, onPress, null) {
			@Override
			public void render(Graphics g) {

			}
		};
		LAYER.MENU.addObject(button);
	}

	private void loadNextLvl(String lvl) {
		LAYER.MENU.getHandler().clear();
		new World(lvl);
	}

}
