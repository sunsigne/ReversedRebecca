package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class DoubleYTornadoPattern extends DoubleYMovePattern {

	protected DoubleYTornadoPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYTornadoPattern(BossObject boss) {
		this(boss, 8, 110);
	}

	////////// TICK ////////////

	private int time;
	private boolean attacking;

	@Override
	public void tick() {
		super.tick();
		time++;

		if (time < getDelayBetweenTwoAttacks()) {
			movingToGoal(-1, -1);
			return;
		}

		if (attacking == false) {
			attacking = true;
			startActing(DOUBLE_Y_CONDITION.TORNADO);
		}

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		movePlayerTowardTornado(player);
	}

	private void movePlayerTowardTornado(Player player) {
		int xPlayer = player.getX();
		int yPlayer = player.getY();
		int xBoss = getBoss().getX();
		int yBoss = getBoss().getY();

		int speed = (int) (0.8f * Math.sqrt(time - getDelayBetweenTwoAttacks()));
		int xSpeed = xBoss > xPlayer ? speed : -speed;
		int ySpeed = yBoss > yPlayer ? speed : -speed;

		player.setX(player.getX() + xSpeed);
		player.setY(player.getY() + ySpeed);
	}

}
