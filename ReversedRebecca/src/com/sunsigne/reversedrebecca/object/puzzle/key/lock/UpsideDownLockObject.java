package com.sunsigne.reversedrebecca.object.puzzle.key.lock;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class UpsideDownLockObject extends LockObject {

	public UpsideDownLockObject(Puzzle puzzle) {
		super(puzzle);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		int mouseY = new MousePos().get()[1];

		setY(ymax + ymin - mouseY);

		if (mouseY > ymax)
			setY(ymin);
		if (mouseY < ymin)
			setY(ymax);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY() + getHeight(), getWidth(), -getHeight(), null);
	}

}
