package com.sunsigne.reversedrebecca.object.puzzler.computer;

public class NullComputerObject extends ComputerObject {

	public NullComputerObject(int x, int y) {
		super(LVL.NULL, x, y);
		setDisabled(true);
	}

}
