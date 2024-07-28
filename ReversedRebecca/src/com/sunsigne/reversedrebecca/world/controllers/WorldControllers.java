package com.sunsigne.reversedrebecca.world.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.world.World;

public abstract class WorldControllers implements MouseUserEvent, KeyboardEvent, GamepadEvent, TickFree, RenderFree {

	public abstract WorldControllers getWorldControllers();

	////////// POSITION ////////////

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public void setX(int x) {

	}

	@Override
	public void setY(int y) {

	}

	@Override
	public int getWidth() {
		return Window.WIDHT;
	}

	@Override
	public int getHeight() {
		return Window.HEIGHT;
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (devOnly() && Infos.IS_DEV_VERSION == false)
			return;
		
		if (World.get() == null)
			return;

		if (pressed)
			return;

		inputPressed(e.getKeyCode(), -1);
		pressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (devOnly() && Infos.IS_DEV_VERSION == false)
			return;
		
		if (World.get() == null)
			return;

		inputReleased(e.getKeyCode(), -1);
		pressed = false;
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (World.get() == null)
			return;

		inputPressed(65535, e.getKey());
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (World.get() == null)
			return;

		inputReleased(65535, e.getKey());
	}

	////////// INPUT ////////////

	private boolean pressed;

	public abstract boolean devOnly();

	public abstract void inputPressed(int key, int button);

	public abstract void inputReleased(int key, int button);

}
