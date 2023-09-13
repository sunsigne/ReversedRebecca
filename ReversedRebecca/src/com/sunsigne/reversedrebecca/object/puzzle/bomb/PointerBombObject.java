package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class PointerBombObject extends PuzzleObject implements MouseObject, GamepadEvent {

	public PointerBombObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0, Size.S, Size.S);

		createSpammable();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : POINTER";
		var pos = getRow(Size.S + getX() / 2) + "-" + getCol(Size.S + getY() / 2);
		return clazz + " : " + pos + " / ";
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0] - getWidth() / 2;
		int mouseY = new MousePos().get()[1] - getHeight() / 2;

		followMouse(mouseX, mouseY);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_pointer");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (ControllerManager.getInstance().isUsingGamepad())
			g.drawImage(getImage(), getX() - getWidth() / 2, getY() - getHeight() / 2, 2 * getWidth(), 2 * getHeight(),
					null);
	}

	////////// SPAMMABLE ////////////

	private SpammableGamepadEvent[] spammable;

	private void createSpammable() {
		spammable = new SpammableGamepadEvent[4];
		GenericListener onSpam = null;

		onSpam = () -> moveMouseFrom(-SPEED, 0);
		spammable[0] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.LEFT, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(+SPEED, 0);
		spammable[1] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.RIGHT, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(0, -SPEED);
		spammable[2] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.UP, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(0, +SPEED);
		spammable[3] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.DOWN, 1, 1, onSpam);
	}

	private final int SPEED = 12;

	private void moveMouseFrom(int x, int y) {
		int[] pos = new MousePos().get();
		new PresetMousePos(pos[0] + x, pos[1] + y).moveMouse();
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (isInPauseMenu())
			return;

		for (int index = 0; index < 4; index++)
			spammable[index].buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		for (int index = 0; index < 4; index++)
			spammable[index].buttonReleased(e);
	}

}
