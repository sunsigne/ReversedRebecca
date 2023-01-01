package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class DigToolObject extends PuzzleObject implements TickFree, MouseUserEvent {

	public DigToolObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0, 2 * Size.L, 2 * Size.L);
	}

	public abstract DIG_STATE getState();

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "PUZZLE : DIG ";
		var pos = getRow(getX()) + "-" + getCol(getY());
		return clazz + "TOOL " + getState() + " : " + pos;
	}

	////////// TEXTURE ////////////

	public BufferedImage getImage() {
		return getState().getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "select_tool");
		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		puzzle.setState(getState());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
