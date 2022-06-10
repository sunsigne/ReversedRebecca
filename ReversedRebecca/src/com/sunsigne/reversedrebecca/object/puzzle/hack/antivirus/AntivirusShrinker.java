package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.object.puzzle.hack.VirusObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class AntivirusShrinker extends AntivirusObject {

	public AntivirusShrinker(Puzzle puzzle) {
		super(puzzle, "Shrinker");
	}

	////////// ANTIVIRUS ////////////

	private VirusObject virus;
	private int attempt_before_death = 8;

	@Override
	public void antivirusAction() {
		if (virus == null)
			return;

		attempt_before_death--;
		if (attempt_before_death == 0) {
			getPuzzle().closePuzzle(false);
			return;
		}
		
		virus.shrink();
	}

	@Override
	public void destroyAntivirus() {
		if (virus == null)
			return;
		
		virus.cancelShrink();
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		super.tick();

		if (virus != null)
			return;

		for (Updatable tempUpdatable : LAYER.PUZZLE.getHandler().getList()) {
			if (tempUpdatable instanceof VirusObject == false)
				continue;

			virus = (VirusObject) tempUpdatable;
			break;
		}
	}

	////////// MOUSE ////////////

	@Override
	public void mouseReleased(MouseEvent e) {
		antivirusAction();
	}

}
