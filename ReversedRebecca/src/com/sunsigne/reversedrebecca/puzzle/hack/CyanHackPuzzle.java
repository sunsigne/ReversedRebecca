package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class CyanHackPuzzle extends HackPuzzle {

	public CyanHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createCPU();
	}

	private void createCPU() {
		ProcessorCPU cpu1 = new ProcessorCPU(this, "CPU-1", getCol(3), getRow(1));
		getComputer().addObject(cpu1);
		ProcessorCPU cpu2 = new ProcessorCPU(this, "CPU-2", getCol(5), getRow(1));
		getComputer().addObject(cpu2);
		ProcessorCPU cpu3 = new ProcessorCPU(this, "CPU-BIS", getCol(3), getRow(1));
		getComputer().addObject(cpu3);

		ProcessorFolder system32 = new ProcessorFolder(this, "System32", getCol(3), getRow(1), cpu1, cpu2);
		getComputer().addObject(system32);

		ProcessorFolder windows = new ProcessorFolder(this, "Windows", getCol(3), getRow(1), system32);
		getComputer().addObject(windows);

		ProcessorFolder windows2 = new ProcessorFolder(this, "Windows2", getCol(5), getRow(1), cpu3);
		getComputer().addObject(windows2);

		ProcessorDesktop desktop = new ProcessorDesktop(this, windows, windows2);
		getComputer().addObject(desktop);

		LAYER.PUZZLE.addObject(desktop);

	}

}
