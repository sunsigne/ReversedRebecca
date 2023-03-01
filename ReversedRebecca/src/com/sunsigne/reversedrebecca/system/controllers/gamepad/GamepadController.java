package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

import net.java.games.input.Component.Identifier;

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
	public void buttonPressed(Identifier i) {
		if (LAYER.LOADING.getHandler().getList().isEmpty())
			gamepadEvent.buttonPressed(i);
	}

}