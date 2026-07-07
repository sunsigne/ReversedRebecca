package com.sunsigne.reversedrebecca.characteristics.drunk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.system.Window;

public class DrunkLevel extends Characteristic {

	////////// CHARACTERISTICS ////////////

	private static DrunkLevel instance = new DrunkLevel();

	@Override
	protected Characteristic getInstance() {
		return instance;
	}

	@Override
	public void reset() {
		new DrunkTask().setDrunk(0);
	}

	////////// RENDER ////////////

	public void beforeObjectRender(Graphics2D g2d, int drunk) {
		switch (drunk) {
		case 1:
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		default:
			Color color = new Color(64 + 10 * drunk, 64 + 10 * drunk, 64 + 10 * drunk, 160);
			g2d.setColor(color);
			g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
		}
	}

	public void afterObjectRender(Graphics2D g2d, int drunk) {
		switch (drunk) {
		case 1:
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		default:
			g2d.fillRect(0, 0, Window.WIDHT, Window.HEIGHT);
		}
	}

}
