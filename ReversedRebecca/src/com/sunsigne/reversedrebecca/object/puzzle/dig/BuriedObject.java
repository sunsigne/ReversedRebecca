package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class BuriedObject extends PuzzleObject implements TickFree, MouseUserEvent {

	public BuriedObject(Puzzle puzzle, int w, int h) {
		super(puzzle, false, 0, 0, w, h);
		setClickable(false);
	}

	////////// NAME ////////////

	protected abstract String getName();

	@Override
	public String toString() {
		var clazz = "PUZZLE : BURRIED ";
		var pos = getRow(getX()) + "-" + getCol(getY());
		return clazz + getName() + " : " + pos;
	}

	////////// TEXTURE ////////////

	protected BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName().toLowerCase());
		return image;
	}

	private BufferedImage backgroundImage;

	public BufferedImage getBackgroundImage() {
		if (backgroundImage == null)
			backgroundImage = new ImageTask()
					.loadImage("textures/puzzle/" + getPuzzle().getName() + "_dirt_background");
		return backgroundImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		drawBackground(g);

		if (getImage() != null)
			g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	private void drawBackground(Graphics g) {
		g.drawImage(getBackgroundImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	private boolean clickable;

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	@Override
	public boolean isClickable() {
		if (clickable)
			return MouseUserEvent.super.isClickable();
		else
			return false;
	}

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
