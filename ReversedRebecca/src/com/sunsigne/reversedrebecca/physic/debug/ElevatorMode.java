package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ElevatorMode extends DebugMode {

	////////// DEBUG MODE ////////////

	private static DebugMode debugMode = new ElevatorMode();

	@Override
	public DebugMode getDebugMode() {
		return debugMode;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "debugmode_elevator";
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

		PiranhaPlayer player = new PlayerFinder().getPiranhaPlayer();
		if (player == null)
			return;

		if (key == KeyEvent.VK_NUMPAD8)
			new PlayerLayerChanger().goesUp();

		else if (key == KeyEvent.VK_NUMPAD2)
			new PlayerLayerChanger().goesDown();
	}

}
