package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.Player;
import com.sunsigne.reversedrebecca.object.extrabehaviors.livings.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.pattern.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.laws.PhysicLaw;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ElevatorMode extends DebugMode {

	////////// PHYSIC LAW ////////////

	private static PhysicLaw physicLaw = new ElevatorMode();

	@Override
	public PhysicLaw getPhysicLaw() {
		return physicLaw;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debug_elevator_mode";
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
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == getKeyEvent()) {
			cycle();
		}
		elevatorKey(key);
	}

	private void elevatorKey(int key) {
		if (!getState())
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;
		
		if (key == KeyEvent.VK_NUMPAD8)
			new PlayerLayerChanger().goesUp();

		else if (key == KeyEvent.VK_NUMPAD2)
			new PlayerLayerChanger().goesDown();
	}

}
