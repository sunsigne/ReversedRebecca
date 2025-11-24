package com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.object.characteristics.Pusher.PUSHING_DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossObject;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.BossPattern;
import com.sunsigne.reversedrebecca.object.piranha.living.bosses.doubley.DoubleYFeeling.DOUBLE_Y_CONDITION;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class DoubleYTornadoPattern extends BossPattern {

	protected DoubleYTornadoPattern(BossObject boss, int pattern_time_in_sec, int delay_between_two_attacks) {
		super(boss, pattern_time_in_sec, delay_between_two_attacks);
	}

	public DoubleYTornadoPattern(BossObject boss) {
		this(boss, 9, 4 * Game.SEC);
	}

	////////// PATTERN ////////////

	public DoubleYBoss getBoss() {
		return (DoubleYBoss) super.getBoss();
	}

	////////// MOUVEMENT ////////////

	private void movingToCenter() {
		getBoss().setMustFollowPath(true);
		Position pos = new PlayerFinder().getPlayerClone();
		int x = Size.M * pos.getX();
		int y = Size.M * (pos.getY() - Size.M);
		GoalObject goal = new GoalObject(x, y, true);
		getBoss().setGoal(goal);

		if (goal.getX() == getBoss().getX() && goal.getY() - Size.M / 2 == getBoss().getY())
			getBoss().setFacing(DIRECTION.DOWN);
	}

	private void movePlayerTowardTornado(Player player) {
		int xPlayer = player.getX();
		int yPlayer = player.getY();
		int xBoss = getBoss().getX();
		int yBoss = getBoss().getY();

		int speed = (int) Math.sqrt(time - getDelayBetweenTwoAttacks());
		int xSpeed = xBoss > xPlayer ? speed : -speed;
		int ySpeed = yBoss > yPlayer ? speed : -speed;

		player.setX(player.getX() + xSpeed);
		player.setY(player.getY() + ySpeed);
	}

	////////// TICK ////////////

	private int time;
	private boolean attacking;

	@Override
	public void tick() {
		super.tick();
		time++;

		if (time < getDelayBetweenTwoAttacks()) {
			movingToCenter();
			return;
		}

		if (attacking == false)
			startingTornado();

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		attack(player);
	}

	protected void attack(Player player) {
		movePlayerTowardTornado(player);
	}

	private void startingTornado() {
		attacking = true;
		getBoss().setPushingDirection(PUSHING_DIRECTION.OPPOSITE_OF_PUSHABLE);
		getBoss().setDoubleYCondition(DOUBLE_Y_CONDITION.TORNADO);
		getBoss().setMustFollowPath(true);
	}

}
