package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GamepadController implements GamepadAdapter {

	public GamepadController(GamepadEvent gamepadEvent) {
		this.gamepadEvent = gamepadEvent;
		Game.getInstance().addGamepadListener(this);
	}

	public void removeGamepadListener() {
		Game.getInstance().removeGamepadListener(this);
	}

	////////// GAMEPAD ////////////

	private GamepadEvent gamepadEvent;

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			gamepadEvent.buttonPressed(e);
	}
	
	@Override
	public void buttonReleased(ButtonEvent e) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			gamepadEvent.buttonReleased(e);
	}

}