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
