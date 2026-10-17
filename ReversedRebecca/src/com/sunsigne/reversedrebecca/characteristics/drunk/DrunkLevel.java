package com.sunsigne.reversedrebecca.characteristics.drunk;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sunsigne.reversedrebecca.characteristics.Characteristic;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class DrunkLevel extends Characteristic implements CameraDependency {

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
		int x = -(int) CAMERA.getX();
		int y = -(int) CAMERA.getY();

		Color color = new Color(64, 64, 64, 5 * drunk);
		g2d.setColor(color);
		g2d.fillRect(x, y, Window.WIDHT, Window.HEIGHT);

		if (drunk >= 4)
			g2d.rotate(Math.toRadians(drunk - 3.5f), x + Window.WIDHT / 2, y + Window.HEIGHT / 2);

		if (drunk >= 6) {
			color = new Color(0, 230, 90, 10 * drunk - 50);
			g2d.setColor(color);
			g2d.fillRect(x, y, Window.WIDHT, Window.HEIGHT);
		}

		if (drunk >= 8)
			new TransluantLayer().drawPsycopath(g2d);
	}

	public void afterObjectRender(Graphics2D g2d, int drunk) {
		int x = -(int) CAMERA.getX();
		int y = -(int) CAMERA.getY();

		if (drunk >= 4)
			g2d.rotate(Math.toRadians(3.5f - drunk), x + Window.WIDHT / 2, y + Window.HEIGHT / 2);
	}

}
