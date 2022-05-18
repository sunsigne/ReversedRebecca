package com.sunsigne.reversedrebecca.physic.debug;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ElevatorMode extends DebugMode implements KeyboardEvent {

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

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (getState() == false)
			return;
		
		int key = e.getKeyCode();		
		elevatorKey(key);
	}

	private void elevatorKey(int key) {
		if (getState() == false)
			return;

		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		if (key == KeyEvent.VK_NUMPAD8)
			new PlayerLayerChanger().goesUp();

		else if (key == KeyEvent.VK_NUMPAD2)
			new PlayerLayerChanger().goesDown();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}	

}
