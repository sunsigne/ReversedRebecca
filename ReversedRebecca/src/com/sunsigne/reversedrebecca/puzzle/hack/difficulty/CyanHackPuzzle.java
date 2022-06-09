package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorSystem;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusLocker;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class CyanHackPuzzle extends HackPuzzle {

	public CyanHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();
		AntivirusLocker cpu_locker = createLocker(getCol(9), getRow(1), cpu);

		// windows content
		ProcessorFolder system32 = createFolder("System32", getCol(3), getRow(1), cpu);
		system32.push(cpu_locker);

		// system content
		ProcessorFolder windows = createFolder("Windows", getCol(3), getRow(1), system32);
		AntivirusLocker folder_locker = createLocker(getCol(5), getRow(1), system32, windows);
		if (folder_locker.getTarget() == system32)
			windows.push(folder_locker);

		// desktop content
		ProcessorSystem system = createSystem(windows);
		if (folder_locker.getTarget() == windows)
			system.push(folder_locker);

		// root content
		createDesktop(system);
	}

}
