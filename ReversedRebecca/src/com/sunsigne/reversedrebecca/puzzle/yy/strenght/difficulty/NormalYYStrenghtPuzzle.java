package com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtLauncherObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.YYStrenghtPuzzle;

public class NormalYYStrenghtPuzzle extends YYStrenghtPuzzle {

	public NormalYYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public int getSpeed() {
		return 2;
	}

	@Override
	public StrenghtPlayerObject getPlayer() {
		return new StrenghtPlayerObject(this, getSpeed());
	}

	@Override
	public StrenghtLauncherObject getLauncher() {
		return new StrenghtLauncherObject(this, getSpeed());
	}

	@Override
	public void createPuzzle() {
		createPlayer();
		createLauncher();
	}

}
