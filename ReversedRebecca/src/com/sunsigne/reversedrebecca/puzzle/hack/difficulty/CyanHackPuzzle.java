package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class CyanHackPuzzle extends HackPuzzle {

	public CyanHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createVirus();
		createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// system content
		ProcessorFolder windows = createFolder("Windows", cpu);

		// desktop content
		ProcessorFolder system = createSystem(windows);

		// root content
		createDesktop(system);

		// antivirus
		addLocker(windows);
		addLocker(system);
	}

}
