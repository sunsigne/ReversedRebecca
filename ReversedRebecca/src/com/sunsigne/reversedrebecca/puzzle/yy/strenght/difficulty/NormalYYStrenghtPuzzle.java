package com.sunsigne.reversedrebecca.puzzle.yy.strenght.difficulty;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtLauncherObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtPlayerObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject;
import com.sunsigne.reversedrebecca.object.puzzle.yy.strenght.StrenghtProjectileObject.PROJECTILE_TYPE;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.yy.strenght.YYStrenghtPuzzle;
import com.sunsigne.reversedrebecca.system.Size;

public class NormalYYStrenghtPuzzle extends YYStrenghtPuzzle {

	public NormalYYStrenghtPuzzle(ToolPlayer toolPlayer, GenericListener actionOnWinning,
			GenericListener actionOnLosing) {
		super(toolPlayer, actionOnWinning, actionOnLosing);
	}

	////////// PUZZLE ////////////

	@Override
	public int getPuzzleSpeed() {
		return 2;
	}

	@Override
	public StrenghtPlayerObject getPlayer() {
		return new StrenghtPlayerObject(this, getPuzzleSpeed());
	}

	@Override
	public StrenghtLauncherObject getLauncher() {
		return new StrenghtLauncherObject(this, getPuzzleSpeed());
	}

	@Override
	public void createPuzzle() {
		createPlayer();
		createLauncher();
		
		createProjectile(PROJECTILE_TYPE.BARREL);
		createProjectile(PROJECTILE_TYPE.SARAH);
		createProjectile(PROJECTILE_TYPE.WALL);
	}

	private void createProjectile(PROJECTILE_TYPE projectileType) {
		StrenghtProjectileObject projectile = new StrenghtProjectileObject(this, getPuzzleSpeed(), projectileType);
		projectile.setX(getCol(2) + Size.XS / 2);
		projectile.setY(getRow(2) - Size.XS);
		addProjectile(projectile);
	}

}
