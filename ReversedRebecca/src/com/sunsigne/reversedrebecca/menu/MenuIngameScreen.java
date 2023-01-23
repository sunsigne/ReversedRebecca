package com.sunsigne.reversedrebecca.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public abstract class MenuIngameScreen implements Updatable, TickFree {

	public MenuIngameScreen() {
		LAYER.MENU.getHandler().clear();
		LAYER.MENU.addObject(this);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// USEFUL ////////////

	protected String translate(String text) {
		return new Translatable().getTranslatedText(text, FilePath.MENU);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null) {
			image = new ImageTask().loadImage("textures/menu/" + getName(), true);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
		g.drawImage(getImage(), 289, 305, 1324, 474, null);

	}

}
