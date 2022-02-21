package com.sunsigne.reversedrebecca.object;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.Size;

public class GoalObject extends GameObject {

	public GoalObject(int x, int y, boolean reversed) {
		super(reversed ? x / Size.M : x * Size.M, reversed ? y / Size.M : y * Size.M);

	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

}
