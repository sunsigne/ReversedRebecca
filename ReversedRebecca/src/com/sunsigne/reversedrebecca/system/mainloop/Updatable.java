package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public interface Updatable {

	default Handler getHandler() {
		for (Handler tempHandler : SuperHandler.getGameList().getList()) {
			if (tempHandler.containsObject(this))
				return tempHandler;
		}
		return null;
	}

	default void destroyControls() {

		if (this instanceof KeyboardEvent) {
			KeyboardEvent keyboard = (KeyboardEvent) this;
			if (keyboard.getKeyBoardController() != null)
				keyboard.getKeyBoardController().removeKeyListener();
		}

		if (this instanceof MouseUserEvent) {
			MouseUserEvent mouse = (MouseUserEvent) this;
			if (mouse.getMouseController() != null)
				mouse.getMouseController().removeMouseListener();
		}
	}

	////////// TICK ////////////

	void tick();

	////////// RENDER ////////////

	void render(Graphics g);
}
