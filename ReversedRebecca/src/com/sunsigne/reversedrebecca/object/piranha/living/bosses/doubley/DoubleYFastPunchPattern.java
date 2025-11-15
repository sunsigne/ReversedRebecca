package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher.PUSHING_DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;

public class DoubleYFastPunchPattern extends BossPattern {

	protected DoubleYFastPunchPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYFastPunchPattern(BossObject boss) {
		this(boss, 8, 77);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// MOUVEMENT ////////////

	public void movingtoPlayer(Player player) {
		float diffX = getBoss().getX() - player.getX();
		float diffY = getBoss().getY() - player.getY();
		float distance = (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) / 6;

		getBoss().setVelX(getBoss().getSpeed() * Math.round((-1 / distance) * diffX));
		getBoss().setVelY(getBoss().getSpeed() * Math.round((-1 / distance) * diffY));
	}

	public void reduceSpeed() {
		double reducer = Math.pow((double) time, 0.06d);

		if(reducer < 1d)
			return;
		
		double velX = (double) getBoss().getVelX() / reducer;
		double velY = (double) getBoss().getVelY() / reducer;

		getBoss().setVelX((int) velX);
		getBoss().setVelY((int) velY);
		
		if(getBoss().isMotionlessbyX() && getBoss().isMotionlessbyY())
			getBoss().setPushingDirection(null);
	}

	////////// TICK ////////////

	private int time;
	protected boolean attacking;

	@Override
	public void tick() {
		super.tick();
		reduceSpeed();

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		time++;
		if (time < getDelayBetweenTwoAttacks())
			return;

		time = 0;
		attack(player);
	}

	protected void attack(Player player) {
		attacking = true;

		// boss facing player
		SightFinder sightFinder = new SightFinder(getBoss(), player);
		DIRECTION facing = sightFinder.getDirectionOfGoalFromObserver();
		getBoss().setFacing(facing);

		movingtoPlayer(player);
		getBoss().setPushingDirection(PUSHING_DIRECTION.FACING_OF_PUSHER);
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.PUNCHING);
	}

}
