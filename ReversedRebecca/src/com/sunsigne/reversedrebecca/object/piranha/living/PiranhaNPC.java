package com.sunsigne.reversedrebecca.object.piranha.living;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.extrabehaviors.interactive.characteristics.PlayerAvoider;

public class PiranhaNPC extends LivingObject implements PlayerAvoider {

	public PiranhaNPC(String name, int x, int y) {
		super(name, x, y);
		// TEMP CODE //
		initX = getX();
		initY = getY();
		setGoal(new GoalObject(25, 45, false));
		// TEMP CODE //
	}

	// TEMP CODE //
	private int time;
	private int initX;
	private int initY;
	
	@Override
	public void tick() {
		super.tick();
		
		time--;
		
		if(time <= 0)
		{
			time = 90;
			setX(initX);
			setY(initY);
			setGoal(new GoalObject(25, 45, false));
		}
	}
	// TEMP CODE //

	@Override
	public AVOIDERTYPE getPlayerAvoiderType() {
		return AVOIDERTYPE.AROUND;
	}

	@Override
	public void setPlayerAvoiderType(AVOIDERTYPE playerAvoiderType) {
		// TODO Auto-generated method stub
		
	}
	
}
