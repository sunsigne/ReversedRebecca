package com.sunsigne.reversedrebecca.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageBank;
import com.sunsigne.reversedrebecca.system.Window;

public class LoadingScreen extends GameObject {

	public LoadingScreen() {
		super(false, true, 0, 0);
	}

	@Override
	public void tick() {
	}

	@Override
	public ImageBank getImageBank(int... index) {
		return null;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);

		Font font = new Font("arial", 1, 80);
		g.setFont(font);
		g.setColor(Color.WHITE);
		String text = "LOADING";
		g.drawString(text, 1180, 1000);
	}


}
