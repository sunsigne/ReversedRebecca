package com.sunsigne.reversedrebecca.object.puzzle.key.lock;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class UpsideDownLockObject extends LockObject {

	public UpsideDownLockObject(Puzzle puzzle) {
		super(puzzle, false);
	}

	////////// NAME ////////////

	protected String getName() {
		return "LOCK UPSIDEDOWN";
	}
	
	////////// TICK ////////////

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0];
		int mouseY = new MousePos().get()[1];
			
		mouseX = xmax + xmin - mouseX;
		mouseY = ymax + ymin - mouseY;

		followMouse(mouseX, mouseY);
		keepWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY() + getHeight(), getWidth(), -getHeight(), null);
	}

}
