package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class AntivirusParalyzer extends AntivirusObject {

	public AntivirusParalyzer(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("AntivirusParalyzer", FilePath.PUZZLE));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "paralyzer";
	}

	////////// ANTIVIRUS ////////////

	@Override
	public void antivirusAction() {

		if (isCritical() == false) {
			new MousePos().setX(Window.WIDHT / 2);
			new MousePos().setY(Window.HEIGHT / 2);
		}

		playSound("laser_little");
	}

	@Override
	public void destroyAntivirus() {

	}

	////////// TICK ////////////

	private final int PARALYZING_TIME = 40;
	private int time = 0;

	@Override
	public void tick() {
		super.tick();

		time--;
		if (time < 0) {
			time = PARALYZING_TIME;
			antivirusAction();
		}
	}

}
