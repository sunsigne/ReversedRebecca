package com.sunsigne.reversedrebecca.object.behaviors;

import com.sunsigne.reversedrebecca.object.characteristics.Facing;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface Behavior extends Updatable, KeyboardEvent {

	////////// BEHAVIOR ////////////
	
	ExtraBehaviorsObject getExtraBehaviorsObject();

	////////// KEYBOARD ////////////
	
	@Override
	default KeyboardController getKeyBoardController() {
		return getExtraBehaviorsObject().getKeyBoardController();
	}

}
