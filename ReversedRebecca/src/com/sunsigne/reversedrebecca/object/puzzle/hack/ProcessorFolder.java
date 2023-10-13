package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.util.ConcurrentModificationException;

import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusShrinker;
import com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus.AntivirusTerminator;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ProcessorFolder extends ProcessorObject {

	public ProcessorFolder(Puzzle puzzle, String text, ProcessorObject... processors) {
		super(puzzle, text);

		size = processors.length;
		this.processors = new ProcessorObject[size];
		for (int index = 0; index < size; index++) {
			this.processors[index] = processors[index];
		}

		organizeProcessors();
		refreskBack();
	}

	////////// USEFULL ////////////

	public boolean isFull() {
		int count = 0;

		try {
			for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
				if (tempUpdatable instanceof ProcessorObject)
					if (tempUpdatable instanceof AntivirusTerminatorEye == false)
						count++;
			}
		} catch (ConcurrentModificationException e) {
			// nothing to see here
		}

		return count >= 18;
	}

	// very handy when you need to add an AntivirusObject
	public void push(ProcessorObject... processors) {
		if (isFull())
			return;

		size = size + processors.length;
		ProcessorObject[] combinedProcessors = new ProcessorObject[size];
		combinedProcessors = new ArrayCombiner<ProcessorObject>().combine(ProcessorObject.class, this.processors,
				processors);
		this.processors = combinedProcessors;

		organizeProcessors();
		refreskBack();
	}

	public void push(boolean whileVirusWithin, ProcessorObject processors) {
		if (whileVirusWithin == false)
			push(processors);

		else
			pushWhileVirusWithin(processors);
	}

	private void pushWhileVirusWithin(ProcessorObject processor) {
		if (isFull())
			return;

		getComputer().addObject(processor);

		// search for free space to insert the processor
		for (int index = 0; index < size; index++) {
			if (getComputer().containsObject(this.processors[index]))
				continue;

			// if free space found, replaced the old processor by the new one
			int x = processors[index].getX();
			int y = processors[index].getY();
			this.processors[index] = processor;
			processor.setX(x);
			processor.setY(y);
			LAYER.PUZZLE.getHandler().addObject(processor);
			refreskBack();
			return;
		}

		// else do a classic push and an handler insertion
		push(processor);
		LAYER.PUZZLE.getHandler().addObject(processor);
	}

	private int[][] caze;

	protected int[] getCase(int num) {
		if (num > 17)
			num = 17;

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
					{ p.getCol(9) + gap, p.getRow(5) + gap }, { p.getCol(11) + gap, p.getRow(5) + gap }, };
		}
		return caze[num];
	}

	protected void organizeProcessors() {
		int count = 0;

		for (int index = 0; index < size; index++) {
			if (processors[index] instanceof AntivirusTerminatorEye) {
				count++;
				continue;
			}

			processors[index].setX(getCase(index + 1 - count)[0]);
			processors[index].setY(getCase(index + 1 - count)[1]);
		}
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "folder";
	}

	////////// VIRUS ACTION ////////////

	protected ProcessorObject[] processors;
	private int size;

	public ProcessorObject[] getProcessors() {
		return processors;
	}

	@Override
	public void doVirusAction() {
		hideOldFolder();
		displayNewFolder();
	}

	@Override
	public String getVirusActionSound() {
		return "virus_nav";
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
			if (tempUpdatable instanceof AntivirusShrinker)
				handler.softRemoveObject(tempUpdatable);
			else
				handler.removeObject(tempUpdatable);
		}
	}

	protected ProcessorBack previousback;
	private ProcessorBack myback;

	protected void displayNewFolder() {
		var list = new GameList<ProcessorObject>(LISTTYPE.ARRAY);

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
			if (processors[index].getName().contains("terminator") == false)
				LAYER.PUZZLE.addObject(processors[index]);
			else
				list.addObject(processors[index]);
		}

		for (ProcessorObject tempTerminator : list.getList()) {
			if (tempTerminator instanceof AntivirusTerminator)
				((AntivirusTerminator) tempTerminator).virusJustArrived();
			LAYER.PUZZLE.addObject(tempTerminator);
		}

		LAYER.PUZZLE.addObject(previousback);
	}

	protected void refreskBack() {
		myback = new ProcessorBack(getPuzzle(), previousback, processors);
	}

}
