package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class GamepadController implements GamepadAdapter {

	public GamepadController(GamepadEvent gamepadEvent) {
		this.gamepadEvent = gamepadEvent;
		Game.getInstance().addGamepadListener(this);
	}

	public GamepadController(GameObject object, GamepadEvent gamepadEvent) {
		this.gamepadEvent = gamepadEvent;
		this.object = object;
		Game.getInstance().addGamepadListener(this);
	}

	private GameObject object;

	@Override
	public String toString() {
		if (object == null)
			return super.toString();
		return object.toString();
	}

	public void removeGamepadListener() {
		Game.getInstance().removeGamepadListener(this);
	}

	////////// GAMEPAD ////////////

	private GamepadEvent gamepadEvent;

	public GamepadEvent getGamepadEvent() {
		return gamepadEvent;
	}

	public static GameList<Integer> pressing = new GameList<>(LISTTYPE.ARRAY);

	@Override
	public boolean isPressed(int buttonEvent) {
		return pressing.containsObject(buttonEvent);
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		ControllerManager.getInstance().setUsingGamepad(true);
		gamepadEvent.buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		ControllerManager.getInstance().setUsingGamepad(true);
		gamepadEvent.buttonReleased(e);
	}

}