package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.chest.ChestPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class ChestCard extends PuzzleObject implements MouseUserEvent {

	public ChestCard(ChestPuzzle puzzle, String lootData, int x, int y) {
		super(puzzle, x, y, 4 * Size.L, 5 * Size.L);
		this.lootData = lootData;
		loadImages();
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
		new SoundTask().play(SOUNDTYPE.SOUND, "loot_chest");
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

	private void loadImages() {
		String path = "textures/techtree/";

		normal_img = new ImageTask().loadImage(path + "card_normal");
		gold_img = new ImageTask().loadImage(path + "card_gold");
		selection_img = new ImageTask().loadImage(path + "card_selection");
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

}