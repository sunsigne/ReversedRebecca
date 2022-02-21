package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.PlayerHealth;
import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class GUIHealth extends GameObject implements GUI {

	private GUIHealth() {
		super(0, 0, Size.M, Size.M);
		GUIList.getList().addObject(this);
		loadImages();
	}

	////////// GUI ////////////

	private static GUI gui = new GUIHealth();

	@Override
	public GUI getGUI() {
		return gui;
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
		if (PlayerHealth.getInstance().isInvulnerable())
			return;

		// drawing maxHp empty heart
		int maxHp = PlayerHealth.getInstance().getMaxHp();
		for (int index = 0; index < maxHp; index++) {
			g.drawImage(empty_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}

		// drawing hp full heart above
		int hp = PlayerHealth.getInstance().getHp();
		for (int index = 0; index < hp; index++) {
			g.drawImage(full_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}
	}

}
