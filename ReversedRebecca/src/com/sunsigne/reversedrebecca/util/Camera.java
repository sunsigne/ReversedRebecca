package com.sunsigne.reversedrebecca.util;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.main.ITick;

public class Camera implements ITick {

	public Camera() {
		startTick();
	}

	////////// POSITION ////////////

	private float x, y;

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		if (!Player.isExisting())
			return;

		GameObject player = Player.get();
		x = -player.getX() + (Window.WIDHT - player.getWidth()) / 2;
		y = -player.getY() + (Window.HEIGHT - player.getHeight()) / 2;
	}

}
