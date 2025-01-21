package com.sunsigne.reversedrebecca.system.mainloop;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.debug.FastWorldMode;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public interface Updatable {

	default void removeObject() {
		var handler = getHandler();
		if (handler != null)
			handler.removeObject(this);
	}

	default Handler getHandler() {
		return Handler.getHandler(this);
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

		if (this instanceof GamepadEvent) {
			GamepadEvent gamepad = (GamepadEvent) this;
			if (gamepad.getGamepadController() != null)
				gamepad.getGamepadController().removeGamepadListener();
		}
	}

	////////// PHYSICS ////////////
	/*
	 * default PhysicLaw[] getPhysicLinker() { return null; };
	 */
	PhysicLaw[] getPhysicLinker();

	default void applyPhysics(Graphics g, int type) {
		if (getPhysicLinker() == null)
			return;

		for (PhysicLaw tempPhysicLaw : getPhysicLinker()) {
			if (type == 1)
				tempPhysicLaw.tick(this);
			if (type == 2)
				tempPhysicLaw.beforeObjectRender(g, this);
			if (type == 3)
				tempPhysicLaw.afterObjectRender(g, this);
		}

		if (type == 1)
			new FastWorldMode().tick(this);
	}

	////////// TICK ////////////

	void tick();

	////////// RENDER ////////////

	void render(Graphics g);

}
