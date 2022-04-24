package com.sunsigne.reversedrebecca.system.controllers.keyboard;

import java.awt.event.KeyEvent;

public interface KeyboardEvent {

	////////// KEYBOARD ////////////

	KeyboardController getKeyBoardController();

	void keyPressed(KeyEvent e);

	void keyReleased(KeyEvent e);

}
