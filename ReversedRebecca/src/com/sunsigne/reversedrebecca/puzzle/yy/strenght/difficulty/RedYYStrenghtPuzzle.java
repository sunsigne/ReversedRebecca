package com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject.PROJECTILE_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListenerBoolean;

public class RedYYStrenghtPuzzle extends YellowYYStrenghtPuzzle {

	public RedYYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListenerBoolean actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public int getPuzzleSpeed() {
		return 4;
	}

	@Override
	public void createPuzzle() {
		createPlayer();
		createLauncher();

		createProjectile(PROJECTILE_TYPE.BARREL);
		createProjectile(PROJECTILE_TYPE.CHAIR);
		createProjectile(PROJECTILE_TYPE.TRASH);
		createProjectile(PROJECTILE_TYPE.MILITARYMEN);
		createProjectile(PROJECTILE_TYPE.CACTUS);
		createProjectile(PROJECTILE_TYPE.SARAH);
		createProjectile(PROJECTILE_TYPE.HEART);
		createProjectile(PROJECTILE_TYPE.WALL);
		createProjectile(PROJECTILE_TYPE.COUCH_RIGHT);
		createProjectile(PROJECTILE_TYPE.COUCH_LEFT);
		createProjectile(PROJECTILE_TYPE.MILITARYMEN);
		createProjectile(PROJECTILE_TYPE.HEART);
		createProjectile(PROJECTILE_TYPE.OVEN);
		createProjectile(PROJECTILE_TYPE.TRASH);
		createProjectile(PROJECTILE_TYPE.CHAIR);
		createProjectile(PROJECTILE_TYPE.CACTUS);

		createProjectile(PROJECTILE_TYPE.WALL);
		createProjectile(PROJECTILE_TYPE.MILITARYMEN);
		createProjectile(PROJECTILE_TYPE.BARREL);
		createProjectile(PROJECTILE_TYPE.OVEN);
		createProjectile(PROJECTILE_TYPE.TRASH);
		createProjectile(PROJECTILE_TYPE.BONUS_HEART);
		createProjectile(PROJECTILE_TYPE.CACTUS);
		createProjectile(PROJECTILE_TYPE.WALL);
		createProjectile(PROJECTILE_TYPE.U);
		createProjectile(PROJECTILE_TYPE.ROCK);
		createProjectile(PROJECTILE_TYPE.U);
		createProjectile(PROJECTILE_TYPE.ROCK);
		createProjectile(PROJECTILE_TYPE.U);
		createProjectile(PROJECTILE_TYPE.ROCK);
		createProjectile(PROJECTILE_TYPE.U);
		createProjectile(PROJECTILE_TYPE.ROCK);
	}

}
