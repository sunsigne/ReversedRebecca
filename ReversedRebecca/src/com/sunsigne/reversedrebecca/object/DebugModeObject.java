package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class DebugModeObject extends GameObject {

	public DebugModeObject(DebugMode debugMode) {
		super(Window.WIDHT - Size.L, Window.HEIGHT - Size.L, Size.L, Size.L);
		this.debugMode = debugMode;
	}

	////////// DEBUG MODE ////////////

	private DebugMode debugMode;

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (debugMode.getState())
			g.drawImage(debugMode.getImage(), getX(), getY() - (debugMode.getIndex()) * getHeight(), getWidth(), getHeight(),
					null);
	}

}
