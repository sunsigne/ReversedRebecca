package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorTrash;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusLocker;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
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
		createPeripheralManager();
		
		// desktop content
		ProcessorCPU[] cpu = createCPU();
		AntivirusLocker locker = createLocker(cpu);
		ProcessorObject[] cpu_AND_locker = new ArrayCombiner<ProcessorObject>().combine(ProcessorObject.class, cpu, locker);
		
		// root content
		createDesktop(cpu_AND_locker);
	}

	@Override
	protected void createDesktop(ProcessorObject... processors) {
		ProcessorDesktop desktop = new ProcessorDesktop(this, processors);
		desktop.push(new ProcessorTrash(this));  // just to fill the last case
		getComputer().addObject(desktop);
		LAYER.PUZZLE.addObject(desktop);
	}

}
