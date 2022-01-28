package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;

public class GuiHealth extends GameObject {

	public GuiHealth() {
		super(0, 0, Size.L, Size.L);
		loadImages();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage heart_empty_img;
	private BufferedImage heart_full_img;
	
	private void loadImages() {
		heart_empty_img = new ImageTask().loadImage("textures/" + "gui_heart_empty" + ".png");
		heart_full_img = new ImageTask().loadImage("textures/" + "gui_heart_full" + ".png");
	}
	
	public BufferedImage getImage() {
		return heart_full_img;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

//		g.setColor(Color.BLUE);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());

//		Font font = new Font("arial", 1, 50);
//		String text = "hp";
//		g.setFont(font);
//		g.setColor(Color.white);
//		g.drawString(text, getX() + 20, getY() + 60);
		
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
