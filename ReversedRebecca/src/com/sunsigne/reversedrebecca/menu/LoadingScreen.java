package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LoadingScreen implements Updatable, TickFree {

	public LoadingScreen() {

	}

	public LoadingScreen(boolean saving) {
		this.saving = saving;
	}

	////////// USEFUL ////////////

	private String format(String text) {
		return new FormattedString().getNoSpecialCharacter(text).toUpperCase();
	}

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("dogicabold.ttf", 50f);
	private String text = new Translatable().getTranslatedText("Loading", FilePath.MENU);

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);

		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text.toUpperCase() + "...", 30, Window.HEIGHT - 40);

		if (saving)
			drawProgressSaved(g);
	}

	private boolean saving;
	private Font saved_font = new FontTask().createNewFont("F5.6-Regular.otf", 25f);
	private String saved_text = format(new Translatable().getTranslatedText("ProgressSaved", FilePath.MENU));

	private void drawProgressSaved(Graphics g) {
		int[] rect = new int[] { 20, 30, 0, 0 };
		new TextDecoration().drawOutlinesString(g, saved_font, saved_text, Color.LIGHT_GRAY, Color.BLACK,
				DIRECTION.LEFT, rect);
	}

}
