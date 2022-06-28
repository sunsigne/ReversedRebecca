package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelStats;

public class LevelCompletedScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	public LevelCompletedScreen(String ending, String lvl) {
		loadStats(ending);
		loadFont();
		loadText();
		createContinueButton(lvl);
	}

	////////// USEFUL ////////////

	private String format(String text) {
		return new FormatedString().getNoSpecialCharacter(text).toUpperCase();
	}

	////////// DATA ////////////

	private String time_stats;
	private String puzzle_stats;
	private String goodDeed_stats;
	private String badDeed_stats;
	private String ending_stats;
	private String you_are_stats;

	private void loadStats(String ending_stats) {
		LevelStats stats = World.get().getLevelStats();

		String time = format(stats.getTime());
		String unit = format(new Translatable().getTranslatedText("LevelTimeUnit", file));
		time_stats = time + "  " + unit;

		puzzle_stats = format(stats.getPuzzleCount());
		goodDeed_stats = format(stats.getGoodDeed());
		badDeed_stats = format(stats.getBadDeed());
		you_are_stats = format(stats.getYouAre());
		this.ending_stats = ending_stats;
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
		saved_font = new FontTask().createNewFont("F5.6-Regular.otf", 30f);
	}

	////////// TEXT ////////////

	private String title_text;
	private String time_text;
	private String puzzle_text;
	private String goodDeed_text;
	private String badDeed_text;
	private String ending_text;
	private String click_text;
	private String saved_text;

	private void loadText() {
		title_text = format(new Translatable().getTranslatedText("LevelCompleted", file));
		time_text = format(new Translatable().getTranslatedText("LevelTime", file)) + " :";
		puzzle_text = format(new Translatable().getTranslatedText("LevelPuzzle", file)) + " :";
		goodDeed_text = format(new Translatable().getTranslatedText("LevelGoodDeed", file)) + " :";
		badDeed_text = format(new Translatable().getTranslatedText("LevelBadDeed", file)) + " :";
		ending_text = format(new Translatable().getTranslatedText("LevelEndingAchieved", file)) + " :";
		click_text = format(new Translatable().getTranslatedText("LevelClickToContinue", file));
		saved_text = format(new Translatable().getTranslatedText("ProgressSaved", file));
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawDarkGray(g, Window.WIDHT, Window.HEIGHT);

		drawTitle(g);
		drawText(g, time_text, 0);
		drawStats(g, time_stats, 0);
		drawText(g, puzzle_text, 60);
		drawStats(g, puzzle_stats, 60);
		drawText(g, goodDeed_text, 120);
		drawStats(g, goodDeed_stats, 120);
		drawText(g, badDeed_text, 180);
		drawStats(g, badDeed_stats, 180);
		drawText(g, ending_text, 240);
		drawStats(g, ending_stats, 240);
		drawYouAre(g);
		drawClickToContinue(g);
		drawProgressSaved(g);
	}

	private void drawTitle(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 170, 0, 0 };
		new TextDecoration().drawOutlinesString(g, title_font, title_text, Color.ORANGE, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	private void drawText(Graphics g, String text, int y) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 380 + y, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, text, DIRECTION.RIGHT, rect);
	}

	private void drawStats(Graphics g, String text, int y) {
		int[] rect = new int[] { Window.WIDHT / 2 + 20, 380 + y, 0, 0 };
		Color yellow = new Color(235, 235, 100);
		new TextDecoration().drawOutlinesString(g, text_font, text, yellow, Color.BLACK, DIRECTION.LEFT, rect);
	}

	private void drawYouAre(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 820, 0, 0 };
		new TextDecoration().drawOutlinesString(g, you_are_font, you_are_stats, Color.ORANGE, Color.BLACK,
				DIRECTION.NULL, rect);
	}

	private void drawClickToContinue(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 1010, 0, 0 };
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
