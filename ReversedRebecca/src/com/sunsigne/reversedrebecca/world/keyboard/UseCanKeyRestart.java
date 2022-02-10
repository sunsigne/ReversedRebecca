package com.sunsigne.reversedrebecca.world.keyboard;

import java.awt.event.KeyEvent;

import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.world.World;

public class UseCanKeyRestart extends WorldKeyboard {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_R)
			new World("lvl001");
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
