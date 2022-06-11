package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
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
		createVirus();
		createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// system content
		ProcessorFolder windows = createFolder("Windows", cpu);

		// desktop content
		ProcessorFolder system = createSystem(windows);
		ProcessorEatable[] mp3 = createMP3Files();
		ProcessorFolder music = createFolder("music_0", "Musics", mp3);

		// root content
		createDesktop(system, music);

		// antivirus
		addLocker(2, 3, windows);
		
		addLocker(1, 2, music);
		
		addReverser(system);
		addLocker(system);
	}

}
