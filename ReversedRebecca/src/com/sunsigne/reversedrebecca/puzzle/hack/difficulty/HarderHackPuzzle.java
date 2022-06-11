package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class HarderHackPuzzle extends HackPuzzle {

	public HarderHackPuzzle(GenericListener actionOnWinning) {
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
		createDesktop(system, image, music);

		// antivirus
		addParalyzer(windows);
		addLocker(4, 6, windows);
		addTerminator(windows);

		addTerminator(image);
		addParalyzer(image);

		addShrinker(music);
		addTerminator(music);

		addLocker(1, 2, system);
	}

}
