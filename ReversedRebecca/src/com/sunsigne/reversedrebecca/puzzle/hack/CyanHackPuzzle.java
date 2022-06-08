package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorSystem;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class CyanHackPuzzle extends HackPuzzle {

	public CyanHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		ProcessorCPU[] cpu = createCPU();
		ProcessorFolder system32 = createFolder("System32", getCol(3), getRow(1), cpu);
		ProcessorFolder windows = createFolder("Windows", getCol(3), getRow(1), system32);
		ProcessorSystem system = createSystem(windows);
		createDesktop(system);
	}

}
