package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.loot.HeartLoot;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker;
import com.sunsigne.reversedrebecca.physic.natural.correlated.CameraShaker.SHAKE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.Size;

public class DoubleYEarthquakePattern extends BossPattern {

	protected DoubleYEarthquakePattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYEarthquakePattern(BossObject boss) {
		this(boss, 13, 120);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// TICK ////////////

	private int y0;
	private int time;
	private boolean flag;

	@Override
	public void tick() {
		super.tick();
		earthquake();

		time++;
		if (time < getDelayBetweenTwoAttacks())
			return;

		time = 0;
		jump();
	}

	private boolean init;

	private void jump() {
		if (init == false) {
			init = true;
			y0 = getBoss().getY();
		}

		getBoss().setFacing(DIRECTION.DOWN);
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.GOOD);
		getBoss().setVelY(-15);
		getBoss().setY(getBoss().getY() + getBoss().getVelY());
	}

	private void earthquake() {

		// up then down
		if (y0 > getBoss().getY()) {
			getBoss().setVelY(getBoss().getVelY() + 1);
			getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.BALL);
			flag = true;
			return;
		}

		if (flag && y0 < getBoss().getY()) {
			flag = false;
			new CameraShaker().shaking(SHAKE.STRONG);
			getBoss().setY(y0);
			getBoss().setMotionless();
			new GameTimer(30, () -> getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.GOOD));
			createHeartLoot(2);
		}
	}

	private void createHeartLoot(int number) {
		Position pos = new PlayerFinder().getPlayerClone();
		int x = pos.getX() + Size.M;
		int y = pos.getY() + Size.M;

		for (int index = 0; index < number; index++) {
			int radX;
			int radY;
			boolean flag = false;

			do {
				radX = new RandomGenerator().getIntBetween(-9, 7);
				radY = new RandomGenerator().getIntBetween(-5, 3);
				flag = false;

				// on top left wall
				if (radX <= -5 && radY <= -5)
					flag = true;
				// on right down tank
				if (radX >= 4 && radY >= 1)
					flag = true;

				radX = Size.M * radX;
				radY = Size.M * radY;
			} while (flag);

			HeartLoot heart = new HeartLoot(x + radX, y + radY, isBonusHeart(index));
			heart.bouncing();
			getBoss().getHandler().addObject(heart);
		}
	}

	private boolean isBonusHeart(int index) {
		switch (DifficultyOption.getDifficulty()) {
		case EASY:
			return true;
		case NORMAL:
			return index % 2 != 0;
		case HARD:
			return false;
		}

		return index % 2 != 0;
	}

}
