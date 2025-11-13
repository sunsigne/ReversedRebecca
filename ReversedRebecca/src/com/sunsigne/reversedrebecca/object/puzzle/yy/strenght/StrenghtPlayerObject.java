package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class StrenghtPlayerObject extends StrenghPuzzleObject implements KeyboardEvent, MouseUserEvent, GamepadEvent {

	public StrenghtPlayerObject(Puzzle puzzle, int puzzleSpeed) {
		super(puzzle, puzzleSpeed, 0, 0);
		loadLiving();
		loadAnimations();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		return "PUZZLE : " + "PLAYER";
	}

	////////// POSITION ////////////

	@Override
	public void setX(int x) {
		super.setX(x);
		if (living != null)
			living.setX(x);
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		if (living != null)
			living.setY(y);
	}

	private int groundY;
	private int jumpY = getPuzzle().getRow(2);

	public void setGroudY(int groundY) {
		this.groundY = groundY;
	}

	////////// PLAYER ////////////

	private boolean jumping;

	private void setJumping(boolean jumping) {
		if (jumping == false)
			setY(groundY);
		else
			setY(jumpY);

		this.jumping = jumping;
	}

	////////// TICK ////////////

	private int time;
	private int JUMPING_TIME = 20 * getPuzzleSpeed();

	@Override
	public void tick() {
		getAnimation().run();

		if (jumping == false)
			return;

		time++;
		if (time == JUMPING_TIME) {
			time = 0;
			setJumping(false);
		}
	}

	////////// TEXTURE ////////////

	private LivingObject living;

	private void loadLiving() {
		living = new NPC("rebecca", 0, 0);
		living.setFacing(DIRECTION.LEFT);
		living.setBlockingPath(false);
	}

	private LivingAnimation standingAnimation;

	private void loadAnimations() {
		standingAnimation = new LivingAnimation(living, -1, true, 1);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		return standingAnimation;
	}

	@Override
	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setJumping(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setJumping(true);
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
		mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
