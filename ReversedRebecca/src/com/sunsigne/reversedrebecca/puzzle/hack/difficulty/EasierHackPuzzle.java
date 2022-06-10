package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class EasierHackPuzzle extends HackPuzzle {

	public EasierHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// windows content
		ProcessorFolder system32 = createFolder("System32", cpu);

		// system content
		ProcessorFolder windows = createFolder("Windows", system32);

		// desktop content
		ProcessorFolder system = createSystem(windows);

		// root content
		createDesktop(system);
	}

}
