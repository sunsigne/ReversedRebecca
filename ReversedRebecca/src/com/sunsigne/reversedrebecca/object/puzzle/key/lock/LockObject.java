package com.sunsigne.reversedrebecca.object.puzzle.key.lock;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.CollisionDetector;
import com.sunsigne.reversedrebecca.object.characteristics.CollisionReactor;
import com.sunsigne.reversedrebecca.object.characteristics.MouseSpammableGamepadObject;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.SpammableGamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePos;

public class LockObject extends PuzzleObject implements SheetableImage, CollisionReactor, MouseSpammableGamepadObject {

	public LockObject(Puzzle puzzle, boolean critical) {
		super(puzzle, critical, 0, 0);

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

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_COLLISION;
	}
	
	////////// TICK ////////////

	protected final int xmin = getPuzzle().getCol(1);
	protected int xmax = getPuzzle().getCol(1);
	protected final int ymin = getPuzzle().getRow(1);
	protected final int ymax = getPuzzle().getRow(6);

	@Override
	public void tick() {
		MousePos mousePos = new MousePos();

		followMouse(mousePos.getX(), mousePos.getY());
		keepWithinZone(mousePos.getX(), mousePos.getY(), xmin, xmax, ymin, ymax);

		if (isCritical())
			keepMouseWithinZone(mousePos.getX(), mousePos.getY(), xmin, xmax, ymin, ymax);
		else
			keepMouseWithinYZone(mousePos.getY(), ymin, ymax);
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

	@Override
	public SpammableGamepadEvent[] getSpammables() {
		if (spammable != null)
			return spammable;

		spammable = new SpammableGamepadEvent[4];
		createSpammable();
		return spammable;
	}

	@Override
	public void setSpammable(int index, SpammableGamepadEvent spammable) {
		this.spammable[index] = spammable;
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

}
