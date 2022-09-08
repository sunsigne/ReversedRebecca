package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class AntivirusShrinker extends AntivirusObject {

	public AntivirusShrinker(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("Antivirus"+ "Shrinker", FilePath.PUZZLE));
	}
	
	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "shrinker";
	}

	////////// ANTIVIRUS ////////////

	private int attempt_before_death = 12;

	@Override
	public void antivirusAction() {
		attempt_before_death--;
		if (attempt_before_death == 0) {
			getPuzzle().closePuzzle(false);
			return;
		}

		getVirus().shrink();
		playSound("virus_shrink");
	}

	@Override
	public void destroyAntivirus() {
		getVirus().cancelShrink();
	}

	////////// MOUSE ////////////

	@Override
	public void mouseReleased(MouseEvent e) {
		antivirusAction();
	}

}
