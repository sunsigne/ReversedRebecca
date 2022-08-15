package com.sunsigne.reversedrebecca.object.puzzle.hack.antivirus;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class AntivirusParalyzer extends AntivirusObject {

	public AntivirusParalyzer(Puzzle puzzle) {
		super(puzzle, new Translatable().getTranslatedText("AntivirusParalyzer", file));
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return super.getName() + "_" + "paralyzer";
	}

	////////// ANTIVIRUS ////////////

	@Override
	public void antivirusAction() {
		// I have absolutly NO IDEA why i have to multiply by Window.SCALE_X
		// on x and not on y ... But that's how I get the good result
		new MousePos().setX((int) (Window.SCALE_X * (Window.WIDHT / 2)));
		new MousePos().setY(Window.HEIGHT / 2);
		playSound("laser_little");
	}

	@Override
	public void destroyAntivirus() {

	}

	////////// TICK ////////////

	private final int PARALYZING_TIME = 20;
	private int time = 0;

	@Override
	public void tick() {
		time--;
		if (time < 0) {
			time = PARALYZING_TIME;
			antivirusAction();
		}
	}

}
