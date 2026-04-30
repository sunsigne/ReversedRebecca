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

	private int x0, y0;

	protected void movingToPosePos(int xGap, int yGap) {

		// the devil itself decided it was the correct formula, for no reason
		x0 = (((xGap % 3) + 3) % 3 - 1) * (Size.M / 2);
		y0 = (((yGap % 3) + 3) % 3 - 1) * (Size.M / 2);

		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.GOOD);

		getBoss().setMustFollowPath(true);
		Position pos = new PlayerFinder().getPlayerClone();
		int x = Size.M * (pos.getX() + xGap * Size.M);
		int y = Size.M * (pos.getY() + (yGap * Size.M) - Size.M);
		GoalObject goal = new GoalObject(x, y, true);
		getBoss().setGoal(goal);

		if (goal.getX() + x0 == getBoss().getX() && goal.getY() + y0 == getBoss().getY())
			getBoss().setFacing(DIRECTION.DOWN);
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		super.tick();
		time++;

		if (time >= getDelayBetweenTwoAttacks() && time <= 2 * getDelayBetweenTwoAttacks()) {
			movingToPosePos(2, -2);
			return;
		}

		if (time == 2 * getDelayBetweenTwoAttacks())
			takingPose();
	}

	private void takingPose() {
		DoubleYBoss boss = getBoss();

		boss.setX(boss.getGoal().getX() + x0);
		boss.setY(boss.getGoal().getY() + y0);
		boss.setFacing(DIRECTION.DOWN);
		boss.setMustFollowPath(false);
		boss.setMotionless();
	}

}
