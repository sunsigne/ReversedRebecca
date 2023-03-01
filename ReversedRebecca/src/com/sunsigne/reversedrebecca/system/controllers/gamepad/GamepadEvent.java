package com.sunsigne.reversedrebecca.system.controllers.gamepad;

public interface GamepadEvent {

	////////// GAMEPAD ////////////

	GamepadController getGamepadController();

	void buttonPressed(ButtonEvent e);

}
