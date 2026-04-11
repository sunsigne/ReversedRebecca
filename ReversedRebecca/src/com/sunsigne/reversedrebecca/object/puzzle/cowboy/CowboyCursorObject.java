package com.sunsigne.reversedrebecca.object.puzzle.cowboy;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cowboy.CowboyPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class CowboyCursorObject extends PuzzleObject
		implements SheetableImage, MouseUserEvent, GamepadEvent, CollisionDetector {

	public CowboyCursorObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0);
	}

	private int speed = Size.XS / 3;

	////////// NAME ////////////

	protected String getName() {
		return "CURSOR";
	}

	@Override
	public String toString() {
		String pos = getRow(getX()) + "-" + getCol(getY());
		String erratic = radX != 0 ? "(erratic)" : "";

		return "PUZZLE : " + getName() + " : " + pos + " " + erratic;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TICK ////////////

	private final int xmin = getPuzzle().getCol(1) + getWidth() / 2;
	private final int xmax = getPuzzle().getCol(11) + getWidth() / 2;

	@Override
	public void tick() {

		// goes left and right
		if (getX() > xmax)
			setVelX(-speed);
		if (getX() < xmin)
			setVelX(speed);

		radX = radY = 0;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetSize() {
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "bomb_shoot");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	////////// RENDER ////////////

	private int radX;
	private int radY;

	public void erraticMovements() {
		int Xrange = 80;
		int Yrange = 200;
		radX = new RandomGenerator().getIntBetween(-Xrange, Xrange);
		radY = new RandomGenerator().getIntBetween(-Yrange, Yrange);
		while (Math.abs(radY) < 20)
			erraticMovements();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX() + radX, getY() + radY, getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	private boolean cursorPressed;

	public boolean isCursorPressed() {
		return cursorPressed;
	}

	private boolean hasFired;

	@Override
	public void mousePressed(MouseEvent e) {
		if (isInPauseMenu())
			return;

		if (hasFired == true)
			return;

		hasFired = true;
		new SoundTask().playSound(SOUNDTYPE.SOUND, "shoot");
		CowboyPuzzle puzzle = (CowboyPuzzle) getPuzzle();
		puzzle.createHoleObject(getX() + radX, getY() + radY);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.A)
			mouseReleased(null);
	}

}
