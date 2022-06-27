package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class MenuScreen implements Updatable, TickFree {
	
	protected String file = "menu.csv";
	
	public MenuScreen() {
		LAYER.MENU.getHandler().clear();
		LAYER.MENU.addObject(this);
		loadImages();
	}
	
	////////// USEFUL ////////////

	protected String translate(String text) {
		return new Translatable().getTranslatedText(text, file);
	}

	////////// TEXTURE ////////////

	private static BufferedImage title_img;

	private void loadImages() {

		if (World.get() == null)
			drawRebeccasRoom();

		if (title_img == null)
			drawTitle();
	}

	private void drawRebeccasRoom() {
		World world = new World(new Save().getLevel(true));
		world.getLevelStats().getStopWatch().stop();
		new PlayerFinder().setUserAllowedToControlPlayer(false);
	}

	private void drawTitle() {
		title_img = new ImageTask().loadImage("textures/menu/" + "title");
	}

	////////// RENDER ////////////
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawTransluantLayer(g2d);
		drawTitle(g);
	}

	private void drawTransluantLayer(Graphics2D g2d) {
		int alpha = 155;
		Color purple = new Color(0, 25, 195, alpha);
		Color black = new Color(0, 0, 0, alpha);

		GradientPaint up_paint = new GradientPaint(0, 0, purple, 0, Window.HEIGHT / 4, black);
		g2d.setPaint(up_paint);
		g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT / 2);

		GradientPaint down_paint = new GradientPaint(0, 3 * Window.HEIGHT / 4, black, 0, Window.HEIGHT, purple);
		g2d.setPaint(down_paint);
		g2d.fillRect(0, Window.HEIGHT / 2, Window.WIDHT, Window.HEIGHT / 2);
	}

	private void drawTitle(Graphics g) {
		g.drawImage(title_img, 525, 80, 856, 380, null);
	}

}
