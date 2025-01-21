package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.chest.ChestPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class ChestCard extends PuzzleObject implements SheetableImage, MouseUserEvent, GamepadEvent {

	public ChestCard(ChestPuzzle puzzle, String lootData, int x, int y) {
		super(puzzle, false, x, y, 4 * Size.L, 5 * Size.L);
		this.lootData = lootData;
		loadImages();
	}

	public boolean isValid() {
		return new ChestLootFactory().createLoot(this, lootData).isValid();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST CARD";
		return clazz + " : " + lootData;
	}

	////////// LOOT ////////////

	private boolean lootCreated;
	private String lootData;

	private ChestLoot chestLoot;

	public ChestLoot getChestLoot() {
		return chestLoot;
	}

	// called by tick method
	private void createLoot() {
		if (lootCreated)
			return;

		lootCreated = true;
		chestLoot = new ChestLootFactory().createLoot(this, lootData);
		LAYER.PUZZLE.addObject(chestLoot);
	}

	////////// PICK UP ////////////

	private boolean pickedUp;

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void pickUp() {
		pickedUp = true;
		new SoundTask().playSound(SOUNDTYPE.SOUND, "loot_chest");
		chestLoot.pickUp();
	}

	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() + 60;
		rect[1] = getY() + 75;
		rect[2] = getWidth() - 120;
		rect[3] = getHeight() - 150;
		return rect;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}
	
	////////// TICK ////////////

	@Override
	public void tick() {
		createLoot();
		arravingAnimation();
		leavingAnimation();
	}

	private boolean arraving = true;
	private int ymid = Window.HEIGHT / 2 - getHeight() / 2;

	// arriving by the top to go to the center
	private void arravingAnimation() {
		if (arraving == false)
			return;

		setVelY(getSize() / 10); // go does

		if (getY() < ymid) // center limit
			return;

		arraving = false;
		setMotionless();
		setY(ymid);
	}

	private boolean leaving;
	private boolean ymaxReached;

	public boolean isLeaving() {
		return leaving;
	}

	public void setLeaving(boolean leaving) {
		this.leaving = leaving;
	}

	// going down a little then leaving to the top
	private void leavingAnimation() {
		if (isLeaving() == false)
			return;

		if (getY() > 250) // down limit
			ymaxReached = true;

		if (ymaxReached == false)
			setVelY(getVelY() + getSpeed() / 3); // goes down
		else
			setVelY(getVelY() - getSpeed() / 4); // goes up if down limit reached
	}

	////////// TEXTURE ////////////

	private BufferedImage normal_img;
	private BufferedImage gold_img;
	private BufferedImage selection_img;
	
	@Override
	public int getSheetWidth() {
		return 512;
	}
	
	@Override
	public int getSheetHeight() {
		return 640;
	}
	
	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "card");
		normal_img = getSheetSubImage(sheet, 1);
		gold_img = getSheetSubImage(sheet, 2);
		selection_img = getSheetSubImage(sheet, 3);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		g.drawImage(normal_img, getX(), getY(), getWidth(), getHeight(), null);

		if (isSelected())
			g.drawImage(selection_img, getX(), getY(), getWidth(), getHeight(), null);

		if (isPickedUp())
			g.drawImage(gold_img, getX(), getY(), getWidth(), getHeight(), null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public boolean isClickable() {
		boolean clickable = MouseUserEvent.super.isClickable();
		boolean available = isPickedUp() == false;
		boolean puzzleRunning = ((ChestPuzzle) getPuzzle()).isClosing() == false;

		return clickable && available && puzzleRunning;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected())
			pickUp();
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
