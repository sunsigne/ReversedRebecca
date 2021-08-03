package com.sunsigne.reversedrebecca.system.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.Conductor;

public class GameKeyboardInput extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		cheatKey(key);

		if (key == KeyEvent.VK_ESCAPE) {
			Conductor.stopApp();
		}
	}

	private void cheatKey(int key) {

		if (key == KeyEvent.VK_F1)
			Conductor.getDebugMode().getMultiToolMode().cycle();

		if (key == KeyEvent.VK_F2)
			Conductor.getDebugMode().getWallPassMode().cycle();

		if (key == KeyEvent.VK_F3)
			Conductor.getDebugMode().getSkipMode().cycle();

		if (key == KeyEvent.VK_F4)
			Conductor.getDebugMode().getHitboxMode().cycle();

		if (key == KeyEvent.VK_F5)
			Conductor.getDebugMode().getFastMode().cycle();
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
	}

}
