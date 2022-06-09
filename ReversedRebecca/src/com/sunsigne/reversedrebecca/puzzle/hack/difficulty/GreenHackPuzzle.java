package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorSystem;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusLocker;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;

public class GreenHackPuzzle extends HackPuzzle {

	public GreenHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {


		// system32 content
		ProcessorCPU[] cpu = createCPU();
		AntivirusLocker cpu_locker = createLocker(getCol(9), getRow(1), cpu);

		// windows content
		ProcessorFolder system32 = createFolder("System32", getCol(3), getRow(1), cpu);
		system32.push(cpu_locker);

		// peripherals content
		ProcessorFolder peripherals = createPeripheralManager();

		// system content
		ProcessorFolder windows = createFolder("Windows", getCol(3), getRow(1), system32);
		AntivirusLocker system_locker = createLocker(getCol(7), getRow(1), windows, peripherals);
		
		// desktop content
		ProcessorSystem system = createSystem(windows, peripherals, system_locker);

		// root content
		createDesktop(system);
	}

}
