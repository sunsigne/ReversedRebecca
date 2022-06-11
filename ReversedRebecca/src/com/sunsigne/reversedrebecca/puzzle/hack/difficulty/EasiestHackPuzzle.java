package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class EasiestHackPuzzle extends HackPuzzle {

	public EasiestHackPuzzle(GenericListener actionOnWinning) {
		super(actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createVirus();
		createPeripheralManager();

		// desktop content
		ProcessorCPU[] cpu = createCPU();
		
		// root content
		createDesktop(cpu);
	}

	@Override
	protected void createDesktop(ProcessorObject... processors) {
		ProcessorDesktop desktop = new ProcessorDesktop(this, processors);
		getComputer().addObject(desktop);
		LAYER.PUZZLE.addObject(desktop);
	}

}
