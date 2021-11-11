package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

public interface Updatable {

	default Handler getHandler() {
		for (Handler tempHandler : SuperHandler.getGameList().getList()) {
			if (tempHandler.cointainsObject(this))
				return tempHandler;
		}
		return null;
	}
	
	////////// TICK ////////////
	
	void tick();
	
	////////// RENDER ////////////
	
	void render(Graphics g);
}
