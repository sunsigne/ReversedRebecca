package com.sunsigne.reversedrebecca.physic.laws;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.physic.PhysicList;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class UpdateCursorLaw implements PhysicLaw {

	static {
		PhysicList.getList().addObject(new UpdateCursorLaw());
	}

	////////// TICK ////////////
	
	private final GameCursor CURSOR = new GameCursor();
	
	@Override
	public void tick(Updatable object) {
		CURSOR.tick();
	}

	////////// RENDER ////////////

	@Override
	public void beforeObjectRender(Graphics g, Updatable object) {

	}

	@Override
	public void afterObjectRender(Graphics g, Updatable object) {

	}

}
