package com.sunsigne.reversedrebecca.object.puzzle.key;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class UpsideDownLockObject extends LockObject {

	public UpsideDownLockObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
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

}
