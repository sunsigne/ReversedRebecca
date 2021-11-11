package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.behaviors.UserCanKeyMove;
import com.sunsigne.reversedrebecca.object.debug.FastPlayerObject;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.Layer;

public class FastPlayerMode extends DebugMode {

	private static FastPlayerMode debugMode = new FastPlayerMode();

	static {
		PhysicList.getList().addObject(debugMode);
		Layer.DEBUG.addObject(new FastPlayerObject(debugMode));
	}

	////////// DEBUG MODE ////////////

	@Override
	public int getIndex() {
		return 1;
	}
	
	private static int player_walking_speed = 0;

	private int getPlayerWalkingSpeed() {
		if (player_walking_speed == 0)
			player_walking_speed = UserCanKeyMove.speed;
		return player_walking_speed;
	}
	
	@Override
	protected void actionWhenTurnedOn() {
		int speed = getPlayerWalkingSpeed();
		UserCanKeyMove.speed = 3 * speed;
	}

	@Override
	protected void actionWhenTurnedOff() {
		int speed = getPlayerWalkingSpeed();
		UserCanKeyMove.speed = speed;
	}

	////////// TICK ////////////

	@Override
	public void tick(Updatable object) {

	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}
	
	////////// KEYBOARD ////////////

	@Override
	public int getKeyEvent() {
		return KeyEvent.VK_F1;
	}

}
