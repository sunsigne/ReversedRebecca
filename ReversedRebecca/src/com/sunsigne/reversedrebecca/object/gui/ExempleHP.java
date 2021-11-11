package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.Texture;

public class ExempleHP extends GameObject implements Texture {

	public ExempleHP() {
		super(0, 0);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	@Override
	public BufferedImage getImage() {
		return null;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(getX(), getY(), getWidth(), getHeight());

		Font font = new Font("arial", 1, 50);
		String text = "hp";
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(text, getX() + 20, getY() + 60);
	}

}
