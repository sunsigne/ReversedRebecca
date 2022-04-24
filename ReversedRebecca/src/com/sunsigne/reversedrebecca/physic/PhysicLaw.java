package com.sunsigne.reversedrebecca.physic;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface PhysicLaw {

	////////// TICK ////////////
	
	void tick(Updatable object);
	
	////////// RENDER ////////////
	
	void beforeObjectRender(Graphics g, Updatable object);
	
	void afterObjectRender(Graphics g, Updatable object);
	
}
