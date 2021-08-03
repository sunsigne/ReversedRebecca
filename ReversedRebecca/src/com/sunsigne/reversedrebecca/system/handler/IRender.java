package com.sunsigne.reversedrebecca.system.handler;

import java.awt.Graphics;

public interface IRender {
	
	public default void activateR() {
		HandlerRender.getInstance().addObject(this);
	}

	void render(Graphics g);
}
