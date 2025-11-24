package com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject.PROJECTILE_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class CyanYYStrenghtPuzzle extends YellowYYStrenghtPuzzle {

	public CyanYYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public int getPuzzleSpeed() {
		return 1;
	}

	@Override
	public void createPuzzle() {
		createPlayer();
		createLauncher();

		createProjectile(PROJECTILE_TYPE.BARREL);
		createProjectile(PROJECTILE_TYPE.CHAIR);
		createProjectile(PROJECTILE_TYPE.MILITARYMEN);
		createProjectile(PROJECTILE_TYPE.ROCK);
		createProjectile(PROJECTILE_TYPE.CACTUS);
		createProjectile(PROJECTILE_TYPE.SARAH);
		createProjectile(PROJECTILE_TYPE.WALL);
		createProjectile(PROJECTILE_TYPE.CHAIR);
		createProjectile(PROJECTILE_TYPE.ROCK);
	}

}
