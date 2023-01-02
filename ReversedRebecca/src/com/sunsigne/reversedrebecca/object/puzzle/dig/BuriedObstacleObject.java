package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class BuriedObstacleObject extends BuriedObject implements TickFree {

	public BuriedObstacleObject(Puzzle puzzle, int w, int h) {
		super(puzzle, w, h);

		setBuriedObject(null, w, h);
	}

	public abstract DIG_STATE getState();
	
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

	protected abstract String getName();

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

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (deleting)
			return;

		super.render(g);
	}

	////////// MOUSE ////////////

	private boolean deleting;
	
	protected abstract String getSuccessSound();
	
	protected abstract String getFailSound();
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		if (puzzle.getState() != getState()) {
			new SoundTask().playSound(SOUNDTYPE.SOUND, getFailSound());
			return;
		}

		new SoundTask().playSound(SOUNDTYPE.SOUND, getSuccessSound());
		LAYER.PUZZLE.getHandler().addObject(buriedObject);
		deleting = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (deleting == false)
			return;

		buriedObject.setClickable(true);
		LAYER.PUZZLE.getHandler().removeObject(this);
	}

}
