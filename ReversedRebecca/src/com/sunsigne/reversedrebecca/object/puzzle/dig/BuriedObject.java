package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;

public abstract class BuriedObject extends DigPuzzleObject implements SheetableImage {

	public BuriedObject(Puzzle puzzle, int w, int h) {
		super(puzzle, false, 0, 0, w, h);
		setClickable(false);
	}

	////////// NAME ////////////

	public abstract String getName();

	@Override
	public String toString() {
		var clazz = "PUZZLE : BURRIED ";
		var pos = getFloatRow(getX()) + "-" + getFloatCol(getY());
		return clazz + getName() + " : " + pos;
	}

	////////// TICK ////////////
	
	@Override
	public void tick() {
		
	}
	
	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	protected BufferedImage image;

	private BufferedImage backgroundImage;

	public abstract BufferedImage getImage();

	public BufferedImage getBackgroundImage() {
		if (backgroundImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "dig_digable");
			backgroundImage = getSheetSubImage(sheet, 3, 1, 16, 16);
		}
		return backgroundImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		drawBackground(g);

		if (getImage() != null)
			g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		drawSelecting(g);
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
			return super.isClickable();
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
