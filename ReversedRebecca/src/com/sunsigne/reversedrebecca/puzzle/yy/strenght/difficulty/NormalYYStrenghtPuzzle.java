package com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.LauncherYYObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.YYStrenghtPuzzle;

public class NormalYYStrenghtPuzzle extends YYStrenghtPuzzle {

	public NormalYYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public LauncherYYObject getLauncherYY() {
		return new LauncherYYObject(this);
	}

	@Override
	public void createPuzzle() {
		createLauncherYY(2);
	}

}
