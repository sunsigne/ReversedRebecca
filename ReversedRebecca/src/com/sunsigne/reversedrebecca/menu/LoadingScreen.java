package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LoadingScreen implements Updatable, TickFree {

	private String file = "menu.csv";

	////////// RENDER ////////////

	private Font font = new FontTask().createNewFont("dogicabold", 50f);

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);

		// for a weird reason, getting this text out systematically corrupts DualChecker
		String text = new Translatable().getTranslatedText("Loading", file);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text.toUpperCase() + "...", 30, Window.HEIGHT - 40);
	}

}
