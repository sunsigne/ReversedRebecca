package com.sunsigne.reversedrebecca.object.gui;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageBank;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.DebugMode;
import com.sunsigne.reversedrebecca.system.Window;

public class GUIDebug extends GameObject {

	public GUIDebug() {
		super(false, false, 0, 0);

		x = Window.WIDHT - 4 * 32;
		y = Window.HEIGHT - 4 * 32;
		w = 4 * 32;
		h = 4 * 32;
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public ImageBank getImageBank(int... index) {
		return ImageBank.TOOL[index[0]][0];
	}

	@Override
	public void render(Graphics g) {

		DebugMode debugmode = Conductor.DEBUG_MODE;

		if (debugmode.getMultiToolMode().getState())
			g.drawImage(getImage(1), x, y, w, h, null);

		if (debugmode.getWallPassMode().getState())
			g.drawImage(getImage(2), x, y - h, w, h, null);

		if (debugmode.getSkipMode().getState())
			g.drawImage(getImage(3), x, y - 2 * h, w, h, null);

		if (debugmode.getHitboxMode().getState())
			g.drawImage(getImage(4), x, y - 3 * h, w, h, null);

		if (debugmode.getFastMode().getState())
			g.drawImage(getImage(5), x, y - 4 * h, w, h, null);
	}

}
