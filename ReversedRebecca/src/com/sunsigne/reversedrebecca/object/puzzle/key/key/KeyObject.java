package com.sunsigne.reversedrebecca.object.puzzle.key.key;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
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

public class KeyObject extends PuzzleObject implements SheetableImage, MouseUserEvent, CollisionDetector, GamepadEvent {

	public KeyObject(Puzzle puzzle) {
		super(puzzle, false, 0, 0);
		multiplySpeedBy(1);
		new GameTimer(20, true, () -> sound = true);
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

	////////// PHYSICS ////////////
	
	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}
	
	////////// TICK ////////////

	private final int ymin = getPuzzle().getRow(1);
	private final int ymax = getPuzzle().getRow(6);
	private boolean sound;

	@Override
	public void tick() {

		// goes up and down
		if (getY() >= ymax) {
			setVelY(-speed);
			if ((getVelX() == 0) && sound)
				new SoundTask().playSound(SOUNDTYPE.SOUND, "bip_short");
		}

		if (getY() <= ymin) {
			setVelY(speed);
			if ((getVelX() == 0) && sound)
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

	@Override
	public int getSheetSize() {
		return 2*16;
	}
	
	@Override
	public int getSheetColCriterion() {
		return 2;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
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
		if (isInPauseMenu())
			return;

		if (getVelX() == 0) {
			setVelX(-1);
			new SoundTask().playSound(SOUNDTYPE.SOUND, "button_validate");
		}
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

	////////// COLLISION ////////////

	private CollisionReactor lastCollidedObject;
		
	@Override
	public void setLastCollidedObject(CollisionReactor lastCollidedObject) {
		this.lastCollidedObject = lastCollidedObject;
	}

	@Override
	public CollisionReactor getLastCollidedObject() {
		return lastCollidedObject;
	}
	
}
