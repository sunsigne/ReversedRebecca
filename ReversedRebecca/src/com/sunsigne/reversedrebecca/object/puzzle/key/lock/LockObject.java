package com.sunsigne.reversedrebecca.object.puzzle.key.lock;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.MouseObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.GameCursor;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class LockObject extends PuzzleObject implements MouseObject, CollisionReactor, GamepadEvent {

	public LockObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0);

		createSpammable();

		if (isCritical())
			xmax = getPuzzle().getCol(11);
	}

	////////// NAME ////////////

	protected String getName() {
		return "LOCK";
	}

	@Override
	public String toString() {
		String critical = isCritical() ? " CRITICAL" : "";
		return "PUZZLE : " + getName() + critical + " : " + getRow(getX()) + "-" + getCol(getY());
	}

	////////// TICK ////////////

	protected final int xmin = getPuzzle().getCol(1);
	protected int xmax = getPuzzle().getCol(1);
	protected final int ymin = getPuzzle().getRow(1);
	protected final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {
		int mouseX = new MousePos().get()[0];
		int mouseY = new MousePos().get()[1];

		followMouse(mouseX, mouseY);
		keepWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);
		keepMouseWithinZone(mouseX, mouseY, xmin, xmax, ymin, ymax);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/puzzle/" + getPuzzle().getName() + "_lock");
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// COLLISION ////////////

	@Override
	public boolean isBlockingSight() {
		return false;
	}

	@Override
	public boolean isBlockingPath() {
		return false;
	}

	@Override
	public void collidingReaction(CollisionDetector detectorObject) {
		collidingReaction(detectorObject, false, () -> getPuzzle().closePuzzle(true));
	}

	////////// SPAMMABLE ////////////

	private SpammableGamepadEvent[] spammable;

	private void createSpammable() {
		spammable = new SpammableGamepadEvent[4];
		GenericListener onSpam = null;

		onSpam = () -> moveMouseFrom(-GameCursor.SPEED, 0);
		spammable[0] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.LEFT, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(GameCursor.SPEED, 0);
		spammable[1] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.RIGHT, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(0, -GameCursor.SPEED);
		spammable[2] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.UP, 1, 1, onSpam);
		onSpam = () -> moveMouseFrom(0, GameCursor.SPEED);
		spammable[3] = new SpammableGamepadEvent(getGamepadController(), ButtonEvent.DOWN, 1, 1, onSpam);
	}

	private void moveMouseFrom(int x, int y) {
		int[] pos = new MousePos().get();
		new PresetMousePos(pos[0] + x, pos[1] + y).moveMouse();
	}
	
	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if(spammable == null)
			return;
		
		if (isInPauseMenu())
			return;

		for (int index = 0; index < 4; index++)
			spammable[index].buttonPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if(spammable == null)
			return;
		
		for (int index = 0; index < 4; index++)
			spammable[index].buttonReleased(e);
	}

}
