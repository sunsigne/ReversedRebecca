package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class OrangeHackPuzzle extends HackPuzzle {

	public OrangeHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createVirus();
		ProcessorFolder peripherals = createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// system content
		ProcessorFolder windows = createFolder(translate("FolderWindows"), cpu);
		
		// desktop content
		ProcessorFolder system = createSystem(windows, peripherals);
		ProcessorEatable[] mp3 = createMP3Files();
		ProcessorEatable[] png = createPNGFiles();
		ProcessorFolder music = createFolder("music_0", translate("FolderMusics"), mp3);
		ProcessorFolder image = createFolder("image_0", translate("FolderImages"), png);

		// root content
		createDesktop(system, image, music);

		// antivirus
		addLocker(3, 4, windows);
		addTerminator(windows);

		addTerminator(image);
		addReverser(windows, image);
		
		addLocker(1, 3, music);
		addParalyzer(music, peripherals);
		
		addParalyzer(system);
		addLocker(1, 2, system);		
	}

}
