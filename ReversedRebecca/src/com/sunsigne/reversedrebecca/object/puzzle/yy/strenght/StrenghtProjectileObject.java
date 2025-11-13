package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class StrenghtProjectileObject extends StrenghPuzzleObject implements SheetableImage {

	public StrenghtProjectileObject(Puzzle puzzle, int puzzleSpeed, PROJECTILE_TYPE projectileType) {
		super(puzzle, puzzleSpeed, 0, 0);
		this.projectileType = projectileType;
		image = null;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "PROJECTILE : " + projectileType;
	}

	////////// TYPE ////////////

	private PROJECTILE_TYPE projectileType;

	public enum PROJECTILE_TYPE {
		BARREL(1), SARAH(2), WALL(3), MILITARYMEN(4);

		PROJECTILE_TYPE(int num) {
			this.num = num;
		}

		private int num;

		public int getNum() {
			return num;
		}

	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// PROJECTILE ////////////

	public void throwing(int puzzleSpeed) {
		int speed = 10;
		setVelX(speed * puzzleSpeed);
		setVelY(-speed * puzzleSpeed);
		accY = puzzleSpeed;
	}

	////////// TICK ////////////

	private final int ymax = getPuzzle().getRow(4) + 3 * Size.XS;
	private int accY;

	@Override
	public void tick() {
		setVelY(getVelY() + accY);

		if (getY() + getVelY() + accY >= ymax) {
			accY = 0;
			setVelY(0);
			setY(ymax);
		}
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetColCriterion() {
		if (projectileType != null)
			return projectileType.getNum();
		return 1;
	}

	private BufferedImage image;

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "yy");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

}
