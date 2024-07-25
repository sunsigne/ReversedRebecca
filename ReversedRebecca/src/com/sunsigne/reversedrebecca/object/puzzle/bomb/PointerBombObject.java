package com.sunsigne.reversedrebecca.object.puzzle.bomb;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.MouseSpammableGamepadObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class PointerBombObject extends PuzzleObject implements MouseSpammableGamepadObject {

	public PointerBombObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0, Size.S, Size.S);
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
		int[] pos = new MousePos().get();

		int mouseX = pos[0] - getWidth() / 2;
		int mouseY = pos[1] - getHeight() / 2;

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

	@Override
	public SpammableGamepadEvent[] getSpammables() {
		if (spammable != null)
			return spammable;

		spammable = new SpammableGamepadEvent[4];
		createSpammable();
		return spammable;
	}

	@Override
	public void setSpammable(int index, SpammableGamepadEvent spammable) {
		this.spammable[index] = spammable;
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

}
