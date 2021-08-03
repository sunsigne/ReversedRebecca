package com.sunsigne.reversedrebecca.system.handler;

import java.awt.Graphics;

public interface IRender {
	
//	boolean isCameraDependant();

	/**
	 * WARNING ! To actually call this method, the object whose the class implements
	 * IRender must, somehow, be add to one of two lists in HandlerRender class
	 */
	void render(Graphics g);
}
