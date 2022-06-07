package com.sunsigne.reversedrebecca.puzzle.hack;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class CyanHackPuzzle extends HackPuzzle {

	public CyanHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createVirus();
		createCPU();
	}

	private void createCPU() {
		ProcessorCPU cpu = new ProcessorCPU(this, "CPU-1", getCol(1), getRow(1));
		LAYER.PUZZLE.addObject(cpu);
		cpu = new ProcessorCPU(this, "CPU-2", getCol(3), getRow(1));
		LAYER.PUZZLE.addObject(cpu);
	}

}
