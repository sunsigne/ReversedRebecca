package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class StrenghtPlayerObject extends StrenghPuzzleObject implements KeyboardEvent {

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

	////////// TICK ////////////

	@Override
	public void tick() {
		getAnimation().run();
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
		int key = e.getKeyCode();

		if (key == LeftKey.getKey() || key == KeyEvent.VK_LEFT)
			living.setFacing(DIRECTION.LEFT);
		if (key == RightKey.getKey() || key == KeyEvent.VK_RIGHT)
			living.setFacing(DIRECTION.RIGHT);
		if (key == UpKey.getKey() || key == KeyEvent.VK_UP)
			living.setFacing(DIRECTION.UP);
		if (key == DownKey.getKey() || key == KeyEvent.VK_DOWN)
			living.setFacing(DIRECTION.DOWN);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
