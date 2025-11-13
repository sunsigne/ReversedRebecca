package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class StrenghtProjectileObject extends StrenghPuzzleObject implements SheetableImage, CollisionReactor {

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
		BARREL(1), SARAH(2), SARAH_CRY(3), WALL(4), MILITARYMEN(5), CHAIR(6), TRASH(7), OVEN(8), COUCH_LEFT(9),
		COUCH_RIGHT(10), CACTUS(11), ROCK(12), U(13);

		PROJECTILE_TYPE(int num) {
			this.num = num;
		}

		private int num;

		public int getNum() {
			return num;
		}
	}

	////////// PROJECTILE ////////////

	public void throwing(int puzzleSpeed) {
		int speed = (int) (15 * Math.sqrt(puzzleSpeed));
		setVelX(speed);
		setVelY(-speed);
		accY = puzzleSpeed;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TICK ////////////

	private final int ymax = getPuzzle().getRow(4) + 3 * Size.XS;
	private int accY;

	@Override
	public void tick() {
		if (new PlayerFinder().getPlayer().isDead()) {
			makeSarahCry();
			setMotionless();
			return;
		}

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

	private void makeSarahCry() {
		if (projectileType != PROJECTILE_TYPE.SARAH)
			return;

		GenericListener listener = () -> {
			projectileType = PROJECTILE_TYPE.SARAH_CRY;
			image = null;
		};

		new GameTimer(2 * Game.SEC, true, listener);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		if (detectorObject instanceof StrenghtPlayerObject == false)
			return;

		StrenghtPlayerObject player = (StrenghtPlayerObject) detectorObject;
		collidingReaction(detectorObject, false, () -> player.colliding());
	}

}
