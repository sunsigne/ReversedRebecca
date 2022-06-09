package com.sunsigne.reversedrebecca.object.puzzle.hack.peripheral;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public abstract class PeripheralObject extends ProcessorObject {

	public PeripheralObject(Puzzle puzzle, String text) {
		super(puzzle, text);
	}

	////////// VIRUS ACTION ////////////

	@Override
	public void doVirusAction() {
		getHandler().removeObject(this);
		getComputer().removeObject(this);
	}

	@Override
	public String getVirusActionSound() {
		return "sound/virus_bite";
	}
	
}
