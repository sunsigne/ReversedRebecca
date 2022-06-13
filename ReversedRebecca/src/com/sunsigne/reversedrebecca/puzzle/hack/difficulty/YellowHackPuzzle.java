package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class YellowHackPuzzle extends HackPuzzle {

	public YellowHackPuzzle(GenericListener actionOnWinning) {
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
		ProcessorFolder windows = createFolder(translate("FolderWindows"), cpu);

		// desktop content
		ProcessorFolder system = createSystem(windows);
		ProcessorEatable[] mp3 = createMP3Files();
		ProcessorEatable[] png = createPNGFiles();
		ProcessorFolder music = createFolder("music_0", translate("FolderMusics"), mp3);
		ProcessorFolder image = createFolder("image_0", translate("FolderImages"), png);

		// root content
		createDesktop(system, image, music);

		// antivirus
		addLocker(3, 4, windows);
		addTerminator(windows);

		addLocker(1, 2, image);
		addReverser(windows, image);
		
		addLocker(1, 2, music);

		addReverser(system);
		addLocker(system);
	}

}
