package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;

public class GUIHealth extends GameObject {

	public GUIHealth() {
		super(false, false, 0, 0);

	}

	////////// TICK ////////////

	@Override
	public void tick() {
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
	}
	
}
