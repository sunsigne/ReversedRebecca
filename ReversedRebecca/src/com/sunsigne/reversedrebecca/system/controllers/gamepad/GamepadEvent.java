package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import net.java.games.input.Component.Identifier;

public interface GamepadEvent {

	////////// GAMEPAD ////////////

	GamepadController getGamepadController();

	void buttonPressed(Identifier i);

}
