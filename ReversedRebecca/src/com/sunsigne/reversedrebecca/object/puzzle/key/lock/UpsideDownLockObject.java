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
		MousePos mousePos = new MousePos();
		int mouseX = mousePos.getX();
		int mouseY = mousePos.getY();
			
		keepMouseWithinZone(mouseX, mouseY, xmin - 5, xmax + 5, ymin, ymax);
		
		mouseX = xmax + xmin - mouseX;
		mouseY = ymax + ymin - mouseY;

		followMouse(mouseX- getWidth() / 2, mouseY - getHeight() / 2);
		keepWithinZone(mouseX - getWidth() / 2, mouseY - getHeight() / 2, xmin - getWidth() / 2,
				xmax - getWidth() / 2, ymin - getHeight() / 2, ymax - getHeight() / 2);
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY() + getHeight(), getWidth(), -getHeight(), null);
	}

}
