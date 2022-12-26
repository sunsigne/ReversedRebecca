package com.sunsigne.reversedrebecca.object.puzzle.key.key;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class KeyObject extends PuzzleObject implements MouseUserEvent, CollisionDetector {

	public KeyObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0);
		multiplySpeedBy(1);
	}

	////////// NAME ////////////

	protected String getName() {
		return "KEY";
	}
	
	@Override
	public String toString() {
		var moving = "LEFT";
		if ((getVelY() != 0))
			moving = getVelY() > 0 ? "DOWN" : "UP";

		return "PUZZLE : " + getName() + " : " + "MOVING:" + moving + " / " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// SIZE ////////////

	// smaller hitbox : the game is easier
	@Override
	public Rectangle getBounds(DIRECTION direction) {
		int x = getX() + getWidth() / 4;
		int y = getY() + getHeight() / 4;
		int w = getWidth() / 2;
		int h = getHeight() / 2;
		return new Rectangle(x, y, w, h);
	}

	////////// VELOCITY ////////////

	private int speed = Size.XS / 4;

	protected void multiplySpeedBy(int multiplier) {
		this.speed = speed * multiplier;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	protected void divideSpeedBy(int divisor) {
		this.speed = speed / divisor;
		setVelY(new RandomGenerator().getBoolean() ? speed : -speed);
	}

	////////// TICK ////////////

	private final int ymin = getPuzzle().getRow(1);
	private final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {

		// goes up and down
		if (getY() >= ymax) {
			setVelY(-speed);
			if ((getVelX() == 0))
				new SoundTask().playSound(SOUNDTYPE.SOUND, "bip_short");
		}

		if (getY() <= ymin) {
			setVelY(speed);
			if ((getVelX() == 0))
				new SoundTask().playSound(SOUNDTYPE.SOUND, "bip_short");
		}

		// when is throwing, continue faster
		if (getVelX() != 0) {
			setVelX(getVelX() - 1);
			setVelY(0);
		}
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_key");
		return image;
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
		if (getVelX() == 0) {
			setVelX(-1);
			new SoundTask().playSound(SOUNDTYPE.SOUND, "button_validate");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
