package com.sunsigne.reversedrebecca.object.extrabehaviors.livings.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.extrabehaviors.behaviors.TickBehavior;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.LivingObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.system.Size;

public class MoveWhenPushed implements TickBehavior {

	public int speed = Size.S / 3;

	public MoveWhenPushed(LivingObject living) {
		this.living = living;
	}

	////////// BEHAVIOR ////////////

	private LivingObject living;

	@Override
	public LivingObject getExtraBehaviorsObject() {
		return living;
	}

	////////// PUSHING ////////////

	private boolean isPushed;

	public boolean isPushed() {
		return isPushed;
	}

	public void setPushed(boolean isPushed) {
		this.isPushed = isPushed;
	}

	public void pushToward(DIRECTION facing) {
		paralyse();
		pushed(facing);
		setPushed(true);
	}

	private void paralyse() {
		if (isPushed())
			living.setMotionless();

		if (living instanceof Player == false)
			return;

		Player player = (Player) living;
		living.addBehavior(new Stunned(player, PUSHING_TIME, player.canInteract));
	}

	private void pushed(DIRECTION facing) {
		if (isPushed())
			return;
		if (facing == DIRECTION.LEFT)
			living.setSurVelX(-speed);
		if (facing == DIRECTION.RIGHT)
			living.setSurVelX(speed);
		if (facing == DIRECTION.UP)
			living.setSurVelY(-speed);
		if (facing == DIRECTION.DOWN)
			living.setSurVelY(speed);
	}

	////////// TICK ////////////

	private final int PUSHING_TIME = 10;
	private int time = PUSHING_TIME;

	@Override
	public void tick() {
		if (isPushed())
			time--;
		if (time < 0)
			stabilize();
	}

	private void stabilize() {
		time = PUSHING_TIME;
		setPushed(false);
		living.setMotionless();
	}

}