package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.Player;
import com.sunsigne.reversedrebecca.object.debug.FastPlayerObject;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.LAYER;

public class FastPlayerMode extends DebugMode {

	private static FastPlayerMode debugMode = new FastPlayerMode();

	static {
		PhysicList.getList().addObject(debugMode);
		LAYER.DEBUG.addObject(new FastPlayerObject(debugMode));
	}

	////////// DEBUG MODE ////////////

	@Override
	public int getIndex() {
		return 1;
	}

	@Override
	protected void actionWhenTurnedOn() {
		Player player = new PlayerFinder().getPlayer();
		if(player != null) player.speed = 3 * player.speed;
	}

	@Override
	protected void actionWhenTurnedOff() {
		Player player = new PlayerFinder().getPlayer();
		if(player != null) player.speed = player.speed / 3;
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
