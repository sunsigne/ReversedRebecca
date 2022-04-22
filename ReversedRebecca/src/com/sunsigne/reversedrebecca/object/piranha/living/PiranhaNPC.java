package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.living.characteristics.PlayerAvoider;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class PiranhaNPC extends LivingObject implements PlayerAvoider {

	public PiranhaNPC(String name, int x, int y) {
		super(name, x, y);
		// TEMP CODE //
		initX = getX();
		initY = getY();
		setGoal(new PlayerFinder().getPiranhaPlayer());
		setPlayerAvoiderType(AVOIDERTYPE.STOP);
		// TEMP CODE //
	}

	// TEMP CODE //
	private int time;
	private int initX;
	private int initY;
	
	@Override
	public void tick() {
		super.tick();

		if(time < 0)
		{
			time = 200;
			setX(initX);
			setY(initY);
			setGoal(new GoalObject(25, 45, false));
		}
	}
	// TEMP CODE //
	
}
