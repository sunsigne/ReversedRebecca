package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusParalyzer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class HardestHackPuzzle extends HackPuzzle {

	public HardestHackPuzzle(GenericListener actionOnWinning) {
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
		ProcessorFolder windows = createFolder("Windows", cpu);

		// music content
		ProcessorEatable[] mp3 = createMP3Files();
		ProcessorEatable[] png = createPNGFiles();

		// desktop content
		ProcessorFolder system = createSystem(windows, peripherals);
		ProcessorFolder music = createFolder("music_0", "Musics", mp3);
		ProcessorFolder image = createFolder("image_0", "Images", png);

		// root content
		AntivirusParalyzer paralyzer = new AntivirusParalyzer(this);
		getComputer().addObject(paralyzer);
		createDesktop(system, image, music, paralyzer);

		// antivirus
		addParalyzer(windows);
		addReverser(windows);
		addLocker(4, 6, windows);
		addTerminator(windows);

		addShrinker(peripherals);
		addParalyzer(peripherals);
		addReverser(peripherals);

		addTerminator(image);
		addParalyzer(image);
		addReverser(image);

		addLocker(2, 4, music);
		addReverser(music);

		addReverser(system);
		addLocker(1, 2, system);
	}

}