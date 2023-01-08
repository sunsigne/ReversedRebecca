package com.sunsigne.reversedrebecca.puzzle.hack.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorCPU;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorDesktop;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorFolder;
import com.sunsigne.reversedrebecca.object.puzzle.hack.ProcessorObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.hack.HackPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class EasierHackPuzzle extends HackPuzzle {

	public EasierHackPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning) {
		super(toolPlayer, actionOnWinning);
	}

	////////// PUZZLE ////////////

	@Override
	public void createPuzzle() {
		createVirus();
		createPeripheralManager();

		// system32 content
		ProcessorCPU[] cpu = createCPU();

		// system content
		ProcessorFolder windows = createFolder(translate("FolderWindows"), cpu);

		// desktop content
		ProcessorFolder system = createSystem(windows);

		// root content
		createDesktop(system);
	}

	@Override
	protected void createDesktop(ProcessorObject... processors) {
		ProcessorDesktop desktop = new ProcessorDesktop(this, processors);
		getComputer().addObject(desktop);
		LAYER.PUZZLE.addObject(desktop);
	}
	
}
