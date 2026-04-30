package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;

public class DoubleYPoseOnePattern extends BossPattern {

	protected DoubleYPoseOnePattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYPoseOnePattern(BossObject boss) {
		this(boss, 8, 110);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// MOUVEMENT ////////////

	private void movingToPosePos(int xGap, int Ygap) {
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.GOOD);

		getBoss().setMustFollowPath(true);
		Position pos = new PlayerFinder().getPlayerClone();
		int x = Size.M * pos.getX() + (xGap * Size.M);
		int y = Size.M * (pos.getY() - Size.M) + (xGap * Size.M);
		GoalObject goal = new GoalObject(x, y, true);
		getBoss().setGoal(goal);

		if (goal.getX() - Size.M / 2 == getBoss().getX() && goal.getY() - Size.M / 2 == getBoss().getY())
			getBoss().setFacing(DIRECTION.DOWN);
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		super.tick();
		time++;

		if (time == getDelayBetweenTwoAttacks()) {
			movingToPosePos(3, -2);
			return;
		}

		if (time == 2 * getDelayBetweenTwoAttacks())
			takingPose();
	}

	private void takingPose() {
		DoubleYBoss boss = getBoss();

		boss.setFacing(DIRECTION.DOWN);
		boss.setMotionless();
	}

}
