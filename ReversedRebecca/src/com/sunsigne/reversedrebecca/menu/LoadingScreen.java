package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class LoadingScreen implements Updatable, Translatable {

	public LoadingScreen() {

	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
		
		String text = getTranslatedText("loading", "loading.csv", 1);
		Font font = new Font("arial", 1, 70);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(text, 20, Window.HEIGHT - 40);		
	}

}
