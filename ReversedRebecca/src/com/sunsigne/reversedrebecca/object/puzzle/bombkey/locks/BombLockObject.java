package com.sunsigne.reversedrebecca.object.puzzle.bombkey.locks;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.bombkey.bombs.BombKeyObject;
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

	protected BombLockObject(Puzzle puzzle, boolean critical, BombKeyObject bomb, boolean fading, int x, int y, int w,
			int h) {
		super(puzzle, critical, x, y, w, h);
		this.bomb = bomb;
		this.fading = fading;
	}

	public BombLockObject(Puzzle puzzle, boolean critical, BombKeyObject bomb, boolean fading, int x, int y) {
		this(puzzle, critical, bomb, fading, x, y, Size.L, Size.L);
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
		return "BOMBLOCK";
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
	private BombKeyObject bomb;

	@Override
	public void tick() {
		if (isOpened() == false) {
			if (bomb != null)
				setVelY(bomb.getVelY());

			if (fading)
				fading();
			return;
		}

		alpha = 1;
		time--;
		if (time < 0)
			removeObject();

	}

	private boolean fading;
	private float alpha = 1f;
	private float alphaSpeed = 0.010f;

	public void fading() {
		alpha = alpha + alphaSpeed;

		if (alpha >= 1f) {
			alpha = 1f;
			alphaSpeed = -alphaSpeed;
		} else if (alpha <= -0.15f) {
			alphaSpeed = -alphaSpeed;
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
		Graphics2D g2d = (Graphics2D) g;

		if (fading)
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0f, alpha)));
		g2d.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
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
