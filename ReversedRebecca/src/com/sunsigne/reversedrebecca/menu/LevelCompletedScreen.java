package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelEndStats;

public class LevelCompletedScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	public LevelCompletedScreen() {
		loadStats();
		loadFont();
		loadText();
	}

	////////// DATA ////////////

	private String time_stats;
	private String puzzle_stats;
	
	private void loadStats() {
		LevelEndStats stats = World.get().getLevelEndStats();		
	
		String unit = new Translatable().getTranslatedText("LevelTimeUnit", file).toUpperCase();
		time_stats = stats.getTime().replace(",", ".") + "  " + unit;
		puzzle_stats = stats.getPuzzleCount();
		
//		you_are_text = new Translatable().getTranslatedText("LevelYouAre", file).toUpperCase() + " :";
	}

	////////// FONT ////////////
	
	private Font title_font;
	private Font text_font;
	private Font you_are_font;
	
	private void loadFont() {
		title_font = new FontTask().createNewFont("F5.6-Regular.otf", 80f);
		text_font = new FontTask().createNewFont("F5.6-Regular.otf", 40f);
		you_are_font = new FontTask().createNewFont("F5.6-Regular.otf", 55f);
	}

	////////// TEXT ////////////
	
	private String title_text;
	private String time_text;
	private String puzzle_text;
	private String bestDeed_text;
	private String worstDeed_text;
	private String ending_text;
	private String click_text;
	
	private void loadText() {
		title_text = new Translatable().getTranslatedText("LevelCompleted", file).toUpperCase();
		time_text = new Translatable().getTranslatedText("LevelTime", file).toUpperCase() + " :";
		puzzle_text = new Translatable().getTranslatedText("LevelPuzzle", file).toUpperCase() + " :";
		bestDeed_text = new Translatable().getTranslatedText("LevelBestDeed", file).toUpperCase() + " :";
		worstDeed_text = new Translatable().getTranslatedText("LevelWorstDeed", file).toUpperCase() + " :";
		ending_text = new Translatable().getTranslatedText("LevelEndingAchieved", file).toUpperCase() + " :";
		click_text = new Translatable().getTranslatedText("LevelClickToContinue", file).toUpperCase();
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
		
		drawText(g, bestDeed_text, 120);
		drawText(g, worstDeed_text, 180);
		drawText(g, ending_text, 240);


		
		drawYouAre(g);
		drawClickToContinue(g);
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
		new TextDecoration().drawOutlinesString(g, text_font, text, yellow, Color.BLACK, DIRECTION.LEFT,
				rect);
	}

	private void drawYouAre(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 820, 0, 0 };
		// PRAGMATIQUE, RAPIDE, CONSCIENCIEUX, GENTIL, MECHANT
		new TextDecoration().drawOutlinesString(g, you_are_font, "VOUS ETES : CONSCIENCIEUX", Color.ORANGE, Color.BLACK, DIRECTION.NULL,
				rect);
	}
	
	private void drawClickToContinue(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 1010, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, click_text, Color.LIGHT_GRAY, Color.BLACK, DIRECTION.NULL,
				rect);
	}
	
}
