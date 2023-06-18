package com.sunsigne.reversedrebecca.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.Save;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.world.World;

public abstract class MenuScreen extends SuperMenuScreen {

	public MenuScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		loadImages();
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
		new World(new Save().getLevel(true));
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
		drawVersion(g);
	}

	protected void drawTransluantLayer(Graphics2D g2d) {
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

	private void drawVersion(Graphics g) {
		var font = new Font("arial", 1, 30);
		int[] rect = new int[] { 940, 460, 0, 0 };

		new TextDecoration().drawOutlinesString(g, font, Infos.VERSION, DIRECTION.NULL, rect);
	}

}
