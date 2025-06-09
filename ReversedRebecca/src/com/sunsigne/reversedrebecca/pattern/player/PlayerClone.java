package com.sunsigne.reversedrebecca.pattern.player;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;

public class PlayerClone extends GameObject implements RenderFree {

	public PlayerClone(Player player) {
		super(player.getX(), player.getY());

		this.player = player;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PLAYER CLONE";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// TICK ////////////

	private Player player;

	private boolean followingPlayer;

	public boolean isFollowingPlayer() {
		return followingPlayer;
	}

	public void setFollowingPlayer(boolean followingPlayer) {
		this.followingPlayer = followingPlayer;
	}

	@Override
	public void tick() {
		if (followingPlayer == false)
			return;

		setX(player.getX());
		setY(player.getY());
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PLAYER;
	}

}
