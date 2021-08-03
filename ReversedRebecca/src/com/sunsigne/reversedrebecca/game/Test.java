package com.sunsigne.reversedrebecca.game;

import java.awt.Color;
import java.awt.Graphics;

public class Test extends GameObject {

	public Test() {
		super(60, 60);

	}

	@Override
	public void tick() {
		velX = 0;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);
	}

}
