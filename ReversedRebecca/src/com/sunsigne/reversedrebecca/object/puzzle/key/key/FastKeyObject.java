package com.sunsigne.reversedrebecca.object.puzzle.key.key;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class FastKeyObject extends KeyObject {

	public FastKeyObject(Puzzle puzzle) {
		super(puzzle);
		multiplySpeedBy(2);
	}

	////////// NAME ////////////

	protected String getName() {
		return "KEY FAST";
	}
	
}
