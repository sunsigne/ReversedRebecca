package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class DigPuzzleObject extends PuzzleObject implements TickFree, MouseUserEvent, GamepadEvent {

	public DigPuzzleObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
	}

	////////// USEFULL ////////////

	protected float getFloatRow(int row) {
		return ((float) row / Size.L) - 0.5f;
	}

	protected float getFloatCol(int col) {
		return ((float) col / Size.L) - 0.25f;
	}

	////////// SIZE ////////////

	@Override
	public boolean isSelected() {
		if (isCritical() == false)
			return MouseUserEvent.super.isSelected();

		MousePos mousePos = new MousePos();
		boolean row_rect = mouseOver(mousePos.get(), getRowRect()) && isClickable();
		boolean col_rect = mouseOver(mousePos.get(), getColRect()) && isClickable();

		return row_rect || col_rect;
	}

	private int[] getRowRect() {
		int[] rect = new int[4];
		rect[0] = getX() - getWidth();
		rect[1] = getY();
		rect[2] = 3 * getWidth();
		rect[3] = getHeight();
		return rect;
	}

	private int[] getColRect() {
		int[] rect = new int[4];
		rect[0] = getX();
		rect[1] = getY() - getHeight();
		rect[2] = getWidth();
		rect[3] = 3 * getHeight();
		return rect;
	}

	////////// PUZZLE ////////////

	@Override
	public DigPuzzle getPuzzle() {
		return (DigPuzzle) super.getPuzzle();
	}

	public boolean isCritical() {
		return getPuzzle().getState() == DIG_STATE.CRITICAL;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}
	
	////////// RENDER ////////////

	public void drawSelecting(Graphics g) {
		if (ControllerManager.getInstance().isUsingGamepad() == false)
			return;

		g.setColor(Color.YELLOW);

		MousePos mousePos = new MousePos();
		if (isCritical()) {
			if (mouseOver(mousePos.get(), getRowRect()) == false && mouseOver(mousePos.get(), getColRect()) == false)
				return;

		} else if (mouseOver(mousePos.get(), getRect()) == false)
			return;

		g.drawRect(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
		g.drawRect(getX() + 2, getY() + 2, getWidth() - 4, getHeight() - 4);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			mouseReleased(null);
	}

}
