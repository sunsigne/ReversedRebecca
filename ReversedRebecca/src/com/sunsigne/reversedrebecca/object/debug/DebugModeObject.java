package com.sunsigne.reversedrebecca.object.debug;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.physic.debug.DebugMode;
import com.sunsigne.reversedrebecca.ressources.images.Texture;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public abstract class DebugModeObject extends GameObject implements Texture {

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
			g.drawImage(getImage(), getX(), getY() - (debugMode.getIndex() - 1) * getHeight(), getWidth(), getHeight(),
					null);
	}

}
