package com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
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

public class BombLockObject extends PuzzleObject implements SheetableImage, MouseUserEvent, GamepadEvent {

	protected BombLockObject(Puzzle puzzle, boolean critical, int x, int y, int w, int h) {
		super(puzzle, critical, x, y, w, h);
	}

	public BombLockObject(Puzzle puzzle, boolean critical, int x, int y) {
		this(puzzle, critical, x, y, Size.L, Size.L);
	}

	private boolean opened;

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
		image = null;

		if (opened == false)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "door_key");
		setVelY(-2);
	}

	////////// NAME ////////////

	protected String getName() {
		return "BOMB";
	}

	@Override
	public String toString() {
		String critical = isCritical() ? " CRITICAL" : "";
		String pos = getRow(getX()) + "-" + getCol(getY());

		return "PUZZLE : " + getName() + critical + " : " + pos;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	private final int ANIMATION_TIME = 10;
	private int time = ANIMATION_TIME;

	@Override
	public void tick() {
		if (isOpened()) {
			time--;
			if (time < 0)
				removeObject();
		}
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return opened ? 2 : 1;
	}

	@Override
	public int getSheetSize() {
		return 32;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "key");
			image = getSheetSubImage(sheet);
		}
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
		if (isOpened())
			return;

		if (isSelected() == false)
			return;

		setOpened(true);
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

	}

}
