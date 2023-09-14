package com.sunsigne.reversedrebecca.system.controllers.gamepad;

import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;

public class SpammableGamepadEvent {

	public SpammableGamepadEvent(GamepadController gamepad, int buttonEvent, int startDelayInTicks,
			int spamDelayInTicks, GenericListener listener) {
		this.gamepad = gamepad;
		this.buttonEvent = buttonEvent;
		this.startDelay = startDelayInTicks;
		this.spamDelay = spamDelayInTicks;
		this.listener = listener;
	}

	private GamepadController gamepad;
	private int buttonEvent;
	private int startDelay;
	private int spamDelay;
	private GenericListener listener;

	////////// GAMEPAD ////////////

	private GameTimer timer;
	private boolean pressed;

	public void buttonPressed(ButtonEvent e) {
		if (buttonEvent != e.getKey())
			return;

		if (timer != null)
			return;

		pressed = true;
		listener.doAction();
		timer = new GameTimer(startDelay, true, () -> spamButton());
	}

	public void buttonReleased(ButtonEvent e) {
		if (isValidButton(e) == false)
			return;

		timer = null;
		pressed = false;
	}

	private boolean isValidButton(ButtonEvent e) {
		if (buttonEvent == e.getKey())
			return true;

		if (buttonEvent == ButtonEvent.LEFT && e.getKey() == ButtonEvent.NULL_X)
			return true;
		if (buttonEvent == ButtonEvent.RIGHT && e.getKey() == ButtonEvent.NULL_X)
			return true;
		if (buttonEvent == ButtonEvent.UP && e.getKey() == ButtonEvent.NULL_Y)
			return true;
		if (buttonEvent == ButtonEvent.DOWN && e.getKey() == ButtonEvent.NULL_Y)
			return true;

		return false;
	}

	public void spamButton() {
		if (pressed == false)
			return;

		if (GamepadManager.getList().containsObject(gamepad) == false) {
			timer = null;
			pressed = false;
		}

		listener.doAction();
		timer = new GameTimer(spamDelay, true, () -> spamButton());
	}

}