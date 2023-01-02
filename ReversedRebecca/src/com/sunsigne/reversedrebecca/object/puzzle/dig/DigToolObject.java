package com.sunsigne.reversedrebecca.object.puzzle.dig;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.dig.DIG_STATE;
import com.sunsigne.reversedrebecca.puzzle.dig.DigPuzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class DigToolObject extends BuriedObject implements TickFree, MouseUserEvent {

	public DigToolObject(Puzzle puzzle, int x_pos_in_menu, int y_pos_in_menu, int w, int h, boolean selectable) {
		super(puzzle, selectable ? 2 * Size.L : w, selectable ? 2 * Size.L : h);
		
		this.x_pos_in_menu = x_pos_in_menu;
		this.y_pos_in_menu = y_pos_in_menu;
		setSelectable(selectable);
	}

	public abstract DIG_STATE getState();

	////////// SELECTABLE ////////////

	private boolean selectable;

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
		
		if (selectable)
			setClickable(true);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "TOOL" + " " + getState();
	}

	@Override
	public String toString() {
		var clazz = "PUZZLE : DIG ";
		var pos = getRow(getX()) + "-" + getCol(getY());
		var selectable = this.selectable ? "SELECTABLE" : "BURIED";
		return clazz + getName() + " : " + selectable + " : " + pos;
	}

	////////// TEXTURE ////////////

	public BufferedImage getImage() {
		return getState().getImage();
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (selectable)
			g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		else
			super.render(g);
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

		if (selectable)
			selectTool();
		else
			pickupTool();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private void selectTool() {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "select_tool");
		DigPuzzle puzzle = (DigPuzzle) getPuzzle();
		puzzle.setState(getState());
	}
	
	private int x_pos_in_menu, y_pos_in_menu;
	
	private void pickupTool() {
		new SoundTask().playSound(SOUNDTYPE.SOUND, "select_tool");	
		
		DigPuzzle puzzle = (DigPuzzle) getPuzzle();		
		DigToolObject selectable = puzzle.getTool(getState(), x_pos_in_menu, y_pos_in_menu, true);
		selectable.setX(x_pos_in_menu);
		selectable.setY(y_pos_in_menu);
		
		LAYER.PUZZLE.addObject(selectable);
		LAYER.PUZZLE.getHandler().removeObject(this);
	}

}
