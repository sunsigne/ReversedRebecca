package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
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

		// system content
		ProcessorFolder windows = createFolder("Windows", cpu);

		// music content
		
		
		// desktop content
		ProcessorFolder system = createSystem(windows, peripherals);
		ProcessorFolder music = createFolder("music_0", "music", system);

		// root content
		createDesktop(system);

		// antivirus
		addLocker(windows);
		addLocker(windows);
		addLocker(system);
	}

}
