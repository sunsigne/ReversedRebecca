package com.sunsigne.reversedrebecca.puzzle.yy.intelligence;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtLauncherObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject.PROJECTILE_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.YYStrenghtPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class AnyYYIntelligencePuzzle extends YYIntelligencePuzzle {

	public AnyYYIntelligencePuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public StrenghtPlayerObject getPlayer() {
		return new StrenghtPlayerObject(this, 0);
	}

	@Override
	public void createPuzzle() {
		createPlayer();
	}

}
