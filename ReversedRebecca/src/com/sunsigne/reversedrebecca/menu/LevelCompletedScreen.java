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

public class LevelCompletedScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	public LevelCompletedScreen() {
		title_font = new FontTask().createNewFont("square_sans_serif_7", 80f);
		text_font = new FontTask().createNewFont("square_sans_serif_7", 40f);
		you_are_font = new FontTask().createNewFont("square_sans_serif_7", 55f);

		title_text = new Translatable().getTranslatedText("LevelCompleted", file).toUpperCase();
		time_text = new Translatable().getTranslatedText("LevelTime", file).toUpperCase() + " :";
		puzzle_text = new Translatable().getTranslatedText("LevelPuzzle", file).toUpperCase() + " :";
		bestDeed_text = new Translatable().getTranslatedText("LevelBestDeed", file).toUpperCase() + " :";
		worstDeed_text = new Translatable().getTranslatedText("LevelWorstDeed", file).toUpperCase() + " :";
		ending_text = new Translatable().getTranslatedText("LevelEndAchieved", file).toUpperCase() + " :";
		click_text = new Translatable().getTranslatedText("LevelClickToContinue", file).toUpperCase();
		
//		you_are_text = new Translatable().getTranslatedText("LevelYouAre", file).toUpperCase() + " :";
	}

	////////// RENDER ////////////

	private Font title_font;
	private Font text_font;
	private Font you_are_font;

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);

		drawTitle(g);
		drawTime(g);
		drawPuzzle(g);
		drawBestDeed(g);
		drawWorstDeed(g);
		drawEnding(g);
		drawYouAre(g);
		drawClickToContinue(g);
	}

	private String title_text;

	private void drawTitle(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 170, 0, 0 };
		new TextDecoration().drawOutlinesString(g, title_font, title_text, Color.ORANGE, Color.BLACK, DIRECTION.NULL,
				rect);
	}

	private String time_text;

	private void drawTime(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 370, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, time_text, DIRECTION.RIGHT, rect);
	}

	private String puzzle_text;
	
	private void drawPuzzle(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 430, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, puzzle_text, DIRECTION.RIGHT, rect);
	}

	private String bestDeed_text;
		
	private void drawBestDeed(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 490, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, bestDeed_text, DIRECTION.RIGHT, rect);
	}

	private String worstDeed_text;
	
	private void drawWorstDeed(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 550, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, worstDeed_text, DIRECTION.RIGHT, rect);
	}

	private String ending_text;
	
	private void drawEnding(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2 - 50, 610, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, ending_text, DIRECTION.RIGHT, rect);
	}

	private void drawYouAre(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 820, 0, 0 };
		// PRAGMATIQUE, RAPIDE, CONSCIENCIEUX, GENTIL, MECHANT
		new TextDecoration().drawOutlinesString(g, you_are_font, "VOUS ÊTES : CONSCIENCIEUX", Color.ORANGE, Color.BLACK, DIRECTION.NULL,
				rect);
	}
	
	private String click_text;
	
	private void drawClickToContinue(Graphics g) {
		int[] rect = new int[] { Window.WIDHT / 2, 1000, 0, 0 };
		new TextDecoration().drawOutlinesString(g, text_font, click_text, Color.LIGHT_GRAY, Color.BLACK, DIRECTION.NULL,
				rect);
	}
	
}
