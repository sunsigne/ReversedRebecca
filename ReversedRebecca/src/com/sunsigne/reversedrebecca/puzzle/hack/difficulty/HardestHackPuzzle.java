package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorEatable;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusParalyzer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class HardestHackPuzzle extends HackPuzzle {

	public HardestHackPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
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
		ProcessorFolder music = createFolder("music", translate("FolderMusics"), mp3);
		ProcessorFolder image = createFolder("image", translate("FolderImages"), png);

		// root content
		AntivirusParalyzer paralyzer = new AntivirusParalyzer(this);
		getComputer().addObject(paralyzer);
		createDesktop(system, image, music, paralyzer);

		// antivirus
		addParalyzer(windows);
		addSpawner(windows);
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

		addReverser(system);
		addLocker(1, 2, system);
	}

}
