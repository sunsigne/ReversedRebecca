package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.Conductor;

public class UseCanKeyQuit extends WorldKeyboard {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE)
			new Conductor().stopApp();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
