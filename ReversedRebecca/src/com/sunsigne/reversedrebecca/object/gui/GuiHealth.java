package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class GuiHealth extends GameObject {

	public GuiHealth() {
		super(0, 0, Size.M, Size.M);
		loadImages();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage empty_img;
	private BufferedImage full_img;

	private void loadImages() {
		empty_img = new ImageTask().loadImage("textures/gui/" + "heart_empty");
		full_img = new ImageTask().loadImage("textures/gui/" + "heart_full");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (new PlayerFinder().getPlayerHealth() == null)
			return;

		// drawing maxHp empty heart
		int maxHp = new PlayerFinder().getPlayerHealth().getMaxHp();
		for (int index = 0; index < maxHp; index++) {
			g.drawImage(empty_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}

		// drawing hp full heart above
		int hp = new PlayerFinder().getPlayerHealth().getHp();
		for (int index = 0; index < hp; index++) {
			g.drawImage(full_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}
	}

}
