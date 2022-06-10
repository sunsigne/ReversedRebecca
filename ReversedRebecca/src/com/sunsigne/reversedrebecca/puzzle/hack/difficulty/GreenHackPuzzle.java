package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorSystem;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class GreenHackPuzzle extends HackPuzzle {

	public GreenHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		ProcessorFolder peripherals = createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// windows content
		ProcessorFolder system32 = createFolder("System32", cpu);

		// system content
		ProcessorFolder windows = createFolder("Windows", system32);

		// desktop content
		ProcessorSystem system = createSystem(windows, peripherals);

		// root content
		createDesktop(system);

		// antivirus
		addLocker(system32);
		addLocker(system32);
		addLocker(system);
		addShrinker(peripherals);
	}

}
