package com.sunsigne.reversedrebecca.object.puzzle.key;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class KeyPuzzleObject extends PuzzleObject implements MouseUserEvent, CollisionDetector {

	public int speed = Size.XS / 4;

	public KeyPuzzleObject(Puzzle puzzle, int x, int y) {
		super(puzzle, x, y);
		loadImage();
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	////////// TICK ////////////

	private final int ymin = getPuzzle().getRow(1);
	private final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {
		
		// goes up and down
		if (getY() >= ymax)
			setVelY(-speed);
		if (getY() <= ymin)
			setVelY(speed);

		// when is throwing, continue faster
		if (getVelX() != 0) {
			setVelX(getVelX() - 1);
			setVelY(0);
		}
	}

	////////// TEXTURE ////////////

	private BufferedImage img;

	private void loadImage() {
//		img = new ImageTask().loadImage("textures/puzzle/wall_" + getPuzzle().getName());
		img = new ImageTask().loadImage("textures/gui/heart_full");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (getVelX() == 0)
			setVelX(-1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
