package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.dig.tool.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;

public class DirtObject extends DigPuzzleObject implements SheetableImage {

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
		return "PUZZLE : " + getName() + " : " + "BURIED:" + buriedObject.getName() + " / " + getFloatRow(getX()) + "-"
				+ getFloatCol(getY());
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

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetColCriterion() {
		return punched ? 2 : 1;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "dig_digable");
			image = getSheetSubImage(sheet);
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

		drawSelecting(g);
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

		boolean final_punch = false;

		if (getPuzzle().getState() == DIG_STATE.PUNCH) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");
			if (punched)
				final_punch = true;
			punched = true;
			image = null;
		}

		if (getPuzzle().getState() == DIG_STATE.DIG || isCritical() || final_punch) {
			if (final_punch == false)
				new SoundTask().playSound(SOUNDTYPE.SOUND, "dig");
			LAYER.PUZZLE.getHandler().addObject(buriedObject);
			deleting = true;
		}

		if (getPuzzle().getState() != DIG_STATE.PUNCH && getPuzzle().getState() != DIG_STATE.DIG)
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
