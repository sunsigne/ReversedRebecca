package com.sunsigne.reversedrebecca.object.puzzle.yy.strenght;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.piranha.living.LivingObject;
import com.sunsigne.reversedrebecca.object.piranha.living.NPC;
import com.sunsigne.reversedrebecca.object.piranha.living.animation.LivingAnimation;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleTextObject;
import com.sunsigne.reversedrebecca.object.puzzle.disco.DiscoArrowObject.CASE;
import com.sunsigne.reversedrebecca.pattern.RandomGenerator;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.physic.natural.independant.LifeAndDeathLaw;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardController;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.KeyboardEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class StrenghtPlayerObject extends StrenghPuzzleObject
		implements KeyboardEvent, MouseObject, MouseUserEvent, GamepadEvent, CollisionDetector {

	public StrenghtPlayerObject(Puzzle puzzle, int puzzleSpeed) {
		super(puzzle, puzzleSpeed, 0, 0);
		loadLiving();
		loadAnimations();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		String jumping = isJumping() ? "jumping" : "on the ground";

		return "PUZZLE : " + "PLAYER : " + jumping;
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

	public void setGapY(int gapY) {
		this.gapY = gapY;
	}

	private int jumpY = getPuzzle().getRow(2);

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

	////////// PLAYER ////////////

	private boolean jumping;
	private boolean shouldBlink;
	private boolean isDead;

	public boolean isJumping() {
		return jumping;
	}

	private void setJumping(boolean jumping) {
		if (jumping == false)
			setGapY(0);
		else
			setGapY(-jumpY);

		this.jumping = jumping;
	}

	public void colliding() {
		if (recovering > 0 || isDead)
			return;

		Player player = new PlayerFinder().getPlayer();
		String path = "loot_chest";
		CASE caze = new RandomGenerator().getBoolean() ? CASE.PERFECT : CASE.GOOD;

		if (jumping == false) {
			shouldBlink = true;
			path = "hit_medium";
			caze = CASE.FAIL;
			player.removeHp();
			player.setRecovering(false);
		}

		recovering = 50 / getPuzzleSpeed();
		new SoundTask().playSound(SOUNDTYPE.SOUND, path);
		LAYER.PUZZLE.addObject(new PuzzleTextObject(getPuzzle(), getX() + Size.S, getY() - Size.XL + gapY, caze));

		if (player.isDead()) {
			LifeAndDeathLaw.kill(player);
			isDead = true;
		}
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}

	////////// TICK ////////////

	private int time;
	private int recovering;
	private int JUMPING_TIME = 60 / getPuzzleSpeed();

	@Override
	public void tick() {
		getAnimation().run();

		recovering--;

		if (recovering <= 0)
			shouldBlink = false;

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
	private LivingAnimation koAnimation;

	private void loadAnimations() {
		standingAnimation = new LivingAnimation(living, -1, true, 1);
		koAnimation = new LivingAnimation(living, -1, false, 5);
	}

	////////// RENDER ////////////

	private LivingAnimation getAnimation() {
		if (isDead == false)
			return standingAnimation;
		else
			return koAnimation;
	}

	@Override
	public BufferedImage getImage() {
		return getAnimation().getImage();
	}

	private int gapY;

	@Override
	public void render(Graphics g) {
		if (recovering > 0 && recovering % 4 == 0 && shouldBlink)
			return;

		g.drawImage(getImage(), getX(), getY() + gapY, getWidth(), getHeight(), null);
	}

	////////// KEYBOARD ////////////

	private KeyboardController keyboardController = new KeyboardController(this);

	@Override
	public KeyboardController getKeyBoardController() {
		return keyboardController;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (isDead == false)
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
		if (isDead == false)
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
