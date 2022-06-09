package com.sunsigne.reversedrebecca.object.puzzle.hack;

import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ProcessorFolder extends ProcessorObject {

	public ProcessorFolder(Puzzle puzzle, String text, int x, int y, ProcessorObject... processors) {
		super(puzzle, text, x, y);

		size = processors.length;
		this.processors = new ProcessorObject[size];
		for (int index = 0; index < size; index++) {
			this.processors[index] = processors[index];
		}

		organizeProcessors();
		refreskBack();
	}

	////////// USEFULL ////////////
	
	// very handy when you need to add an AntivirusObject
	public void push(ProcessorObject... processors) {
		size = size + processors.length;
		ProcessorObject[] combinedProcessors = new ProcessorObject[size];
		combinedProcessors = new ArrayCombiner<ProcessorObject>().combine(ProcessorObject.class, this.processors,
				processors);
		this.processors = combinedProcessors;

		organizeProcessors();
		refreskBack();
	}

	private int[][] caze;

	protected int[] getCase(int num) {
		if (caze == null) {
			Puzzle p = getPuzzle();
			int gap = Size.XS + Size.XS / 2;
			caze = new int[][] { { p.getCol(1) + gap, p.getRow(1) + gap }, { p.getCol(3) + gap, p.getRow(1) + gap },
					{ p.getCol(5) + gap, p.getRow(1) + gap }, { p.getCol(7) + gap, p.getRow(1) + gap },
					{ p.getCol(9) + gap, p.getRow(1) + gap }, { p.getCol(11) + gap, p.getRow(1) + gap },

					{ p.getCol(1) + gap, p.getRow(3) + gap }, { p.getCol(3) + gap, p.getRow(3) + gap },
					{ p.getCol(5) + gap, p.getRow(3) + gap }, { p.getCol(7) + gap, p.getRow(3) + gap },
					{ p.getCol(9) + gap, p.getRow(3) + gap }, { p.getCol(11) + gap, p.getRow(3) + gap },

					{ p.getCol(1) + gap, p.getRow(5) + gap }, { p.getCol(3) + gap, p.getRow(5) + gap },
					{ p.getCol(5) + gap, p.getRow(5) + gap }, { p.getCol(7) + gap, p.getRow(5) + gap },
					{ p.getCol(9) + gap, p.getRow(1) + gap }, { p.getCol(11) + gap, p.getRow(5) + gap }, };
		}
		return caze[num];
	}

	protected void organizeProcessors() {
		for (int index = 0; index < size; index++) {
			processors[index].setX(getCase(index + 1)[0]);
			processors[index].setY(getCase(index + 1)[1]);
		}
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "folder";
	}

	////////// VIRUS ACTION ////////////

	protected ProcessorObject[] processors;
	protected int size;

	@Override
	public void doVirusAction() {
		hideOldFolder();
		displayNewFolder();
	}

	@Override
	public String getVirusActionSound() {
		return "sound/virus_nav";
	}

	protected void hideOldFolder() {
		Handler handler = LAYER.PUZZLE.getHandler();
		var list = new GameList<Updatable>(LISTTYPE.LINKED);

		// remove all processor from the handler
		for (Updatable tempUpdatable : handler.getList()) {
			if (tempUpdatable instanceof ProcessorObject)
				list.addObject(tempUpdatable);
		}

		for (Updatable tempUpdatable : list.getList()) {
			handler.removeObject(tempUpdatable);
		}
	}

	protected ProcessorBack myback;
	protected ProcessorBack previousback;

	protected void displayNewFolder() {
		// add to the handler all processors contained in the folder ...
		for (int index = 0; index < size; index++) {
			// ... only if still in the computer
			if (getComputer().containsObject(processors[index]) == false)
				continue;

			// update back button
			if (processors[index] instanceof ProcessorFolder) {
				ProcessorFolder folder = (ProcessorFolder) processors[index];
				folder.previousback = myback;
				folder.refreskBack();
			}

			LAYER.PUZZLE.addObject(processors[index]);
		}
		LAYER.PUZZLE.addObject(previousback);
	}

	protected void refreskBack() {
		myback = new ProcessorBack(getPuzzle(), previousback, processors);
	}

}
