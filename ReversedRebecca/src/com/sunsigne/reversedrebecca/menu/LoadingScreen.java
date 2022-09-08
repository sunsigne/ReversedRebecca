package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LoadingScreen implements Updatable, TickFree {

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
	}

}
