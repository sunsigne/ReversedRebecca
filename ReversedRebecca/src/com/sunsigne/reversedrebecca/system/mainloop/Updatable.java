package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public interface Updatable {

	default Handler getHandler() {
		for (Handler tempHandler : SuperHandler.getGameList().getList()) {
			if (tempHandler.cointainsObject(this))
				return tempHandler;
		}
		return null;
	}
	
	default void destroyControls() {
		
		if(this instanceof KeyboardEvent) {
			KeyboardEvent keyboard = (KeyboardEvent) this;
			keyboard.getKeyBoardController().removeKeyListener();
		}
		
		if(this instanceof MouseUserEvent) {
			MouseUserEvent mouse = (MouseUserEvent) this;
			mouse.getMouseController().removeMouseListener();
		}
	}
	
	////////// TICK ////////////
	
	void tick();
	
	////////// RENDER ////////////
	
	void render(Graphics g);
}
