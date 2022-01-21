package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.Size;

public class GuiHealth extends GameObject implements Texture {

	public GuiHealth() {
		super(0, 0);
		createTexture();
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage heart_empty_img;
	private BufferedImage heart_full_img;
	
	@Override
	public void createTexture() {
		heart_empty_img = new ImageTask().loadImage("textures/" + "gui_heart_empty" + ".png");
		heart_full_img = new ImageTask().loadImage("textures/" + "gui_heart_full" + ".png");
	}
	
	@Override
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
		
		g.drawImage(getImage(), getX(), getY(), Size.L, Size.L, null);
	}
	
}
