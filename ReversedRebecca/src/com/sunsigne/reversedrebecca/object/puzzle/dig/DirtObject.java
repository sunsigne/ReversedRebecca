package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class DirtObject extends PuzzleObject implements TickFree, MouseUserEvent {

	public DirtObject(Puzzle puzzle, int w, int h) {
		super(puzzle, false, 0, 0, w, h);

		setBuriedObject(null, w, h);
	}

	private BuriedObject buriedObject;

	public BuriedObject getBuriedObject() {
		return buriedObject;
	}

	public void setBuriedObject(BuriedObject buriedObject, int w, int h) {
		if (buriedObject == null)
			buriedObject = new BuriedNullObject(getPuzzle(), w, h);

		this.buriedObject = buriedObject;
	}

	////////// NAME ////////////

	protected String getName() {
		return "DIRT";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName() + " : " + "BURIED:" + buriedObject.getName() + " / " + getRow(getX()) + "-"
				+ getCol(getY());
	}

	////////// POSITION ////////////

	@Override
	public void setX(int x) {
		super.setX(x);
		buriedObject.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		buriedObject.setY(y);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			var punched = this.punched ? "_punched" : "";
			image = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName().toLowerCase() + punched);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (deleting)
			return;

		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		g.setColor(Color.BLACK);

		if (getX() != getPuzzle().getCol(7))
			g.drawLine(getX(), getY(), getX(), getY() + getHeight());

		if (getY() != getPuzzle().getRow(1))
			g.drawLine(getX(), getY(), getX() + getWidth(), getY());
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	private boolean punched, deleting;

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		boolean final_punch = false;

		if (puzzle.getState() == DIG_STATE.PUNCH) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");
			if (punched)
				final_punch = true;
			punched = true;
			image = null;
		}

		if (puzzle.getState() == DIG_STATE.DIG || final_punch) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");
			LAYER.PUZZLE.getHandler().addObject(buriedObject);
			deleting = true;
		}

		if (puzzle.getState() != DIG_STATE.PUNCH && puzzle.getState() != DIG_STATE.DIG)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig_fail");

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (deleting == false)
			return;

		buriedObject.setClickable(true);
		LAYER.PUZZLE.getHandler().removeObject(this);
	}

}
