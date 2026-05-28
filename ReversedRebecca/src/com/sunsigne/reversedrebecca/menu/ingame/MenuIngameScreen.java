package com.sunsigne.reversedrebecca.menu.ingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.SuperMenuScreen;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public abstract class MenuIngameScreen extends SuperMenuScreen {

	public MenuIngameScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TEXTURE ////////////

	private BufferedImage image;

	protected BufferedImage getImage() {
		if (image == null) {
			image = new ImageTask().loadImage("textures/menu/" + getName(), true);
			xl = getName().contains("_xl");
			xxl = xl = getName().contains("_xxl");
		}
		return image;
	}

	////////// RENDER ////////////

	private boolean xl;
	private boolean xxl;

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
		int x = xxl ? 20 : 289;
		int y = xl ? 226 : 305;
		int width = xxl ? 1880 : 1324;
		int height = xl ? 600 : 474;
		g.drawImage(getImage(), x, y, width, height, null);

	}

}
