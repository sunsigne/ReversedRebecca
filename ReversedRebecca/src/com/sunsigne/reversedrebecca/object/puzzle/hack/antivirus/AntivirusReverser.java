package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;

public class AntivirusReverser extends AntivirusObject {

	public AntivirusReverser(Puzzle puzzle) {
		super(puzzle, "Reverser");
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "reverser";
	}

	////////// ANTIVIRUS ////////////

	@Override
	public void antivirusAction() {
		getVirus().setReversed(true);
	}

	@Override
	public void destroyAntivirus() {
		getVirus().setReversed(false);
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		// to activate as soon as entering the Folder
		if (isClickable())
			antivirusAction();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// just in case the game "missed" the activation
		if (isClickable())
			antivirusAction();
	}

}
