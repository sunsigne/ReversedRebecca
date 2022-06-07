package com.sunsigne.reversedrebecca.object.puzzle.hack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public abstract class ProcessorObject extends PuzzleObject implements TickFree, MouseUserEvent {

	public ProcessorObject(Puzzle puzzle, String text, int x, int y) {
		super(puzzle, x + Size.XS + Size.XS / 2, y + Size.XS + Size.XS / 2, 5 * Size.XS, 5 * Size.XS);
		this.text = text;
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_" + getName());
		return image;
	}

	////////// RENDER ////////////

	private String text;

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawText(g);
	}

	private void drawText(Graphics g) {
		Font font = new Font(Font.DIALOG_INPUT, 2, 45);
		int[] rect = { getRect()[0], getRect()[1] + Size.XS + Size.XS / 2, getRect()[2], getRect()[3] };
		new TextDecoration().drawCenteredString(g, font, text, Color.white, DIRECTION.DOWN, rect);
	}
	
	////////// MOUSE ////////////
	
	private  MouseController mouseController = new MouseController(this);
	
	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
}