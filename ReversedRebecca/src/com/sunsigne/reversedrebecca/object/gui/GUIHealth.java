package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
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
		Player player = new PlayerFinder().getPlayer();

		if (player.isInvulnerable())
			return;

		// drawing maxHp empty heart
		for (int index = 0; index < player.getMaxHp(); index++) {
			g.drawImage(empty_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}

		// drawing hp full heart above
		for (int index = 0; index < player.getHp(); index++) {
			g.drawImage(full_img, getX() + index * getWidth(), getY(), getWidth(), getHeight(), null);
		}
	}

}
