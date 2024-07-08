package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class AntivirusReverser extends AntivirusObject {

	public AntivirusReverser(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus" + "Reverser", FilePath.PUZZLE));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "reverser";
	}

	////////// ANTIVIRUS ////////////

	private boolean flag;

	@Override
	public void antivirusAction() {
		if (flag == false)
			reversingSound();

		if (isCritical() == false)
			getVirus().setReversed(true);
	}

	@Override
	public void destroyAntivirus() {
		getVirus().setReversed(false);
	}

	private void reversingSound() {
		flag = true;
		playSound("antivirus_reverser");
	}

	////////// TEXTURE ////////////
	
	@Override
	public int getSheetColCriterion() {
		return 2;
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
