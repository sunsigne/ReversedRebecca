package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.animation.ZapAnimationObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher.PUSHING_DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.finder.InFrontOfFinder;
import com.sunsigne.reversedrebecca.physic.finder.SightFinder;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;

public class DoubleYSSJ2UppercutPattern extends BossPattern {

	protected DoubleYSSJ2UppercutPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYSSJ2UppercutPattern(BossObject boss) {
		this(boss, 30, 7 + Game.SEC + 50);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// TICK ////////////

	private int time;

	@Override
	public void tick() {
		super.tick();

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
		Handler.getHandler(player).addObject(new ZapAnimationObject(getBoss().getX(), getBoss().getY()));

		// tp boss
		int[] pos = new InFrontOfFinder().getPos(player);
		getBoss().setX(pos[0]);
		getBoss().setY(pos[1]);
		
		// boss facing player
		SightFinder sightFinder = new SightFinder(getBoss(), player);
		DIRECTION facing = sightFinder.getDirectionOfGoalFromObserver();
		getBoss().setFacing(facing);

		getBoss().setPushingDirection(PUSHING_DIRECTION.FACING_OF_PUSHER);
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.THROWING);
	}

}
