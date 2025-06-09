package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class PlayerClone extends GameObject implements TickFree, RenderFree {

	public PlayerClone(int x, int y) {
		super(x, y);
	}
	
	////////// POSITION ////////////

	public void resetPos() {
		Player player = new PlayerFinder().getPlayer();
		if(player == null)
			return;
		
		setX(player.getX());
		setY(player.getY());
	}
	
	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PLAYER CLONE";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PLAYER;
	}

}
