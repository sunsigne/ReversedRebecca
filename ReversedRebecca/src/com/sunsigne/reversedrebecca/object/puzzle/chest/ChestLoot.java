package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.chest.ChestPuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class ChestLoot extends PuzzleObject implements MouseUserEvent {

	public ChestLoot(Puzzle puzzle, String loot, int x, int y) {
		super(puzzle, x, y, 4 * Size.L, 5 * Size.L);
		loadImages();
	}

	////////// VALIDATE ////////////

	private boolean validated;

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
		if (validated)
			new SoundTask().play(SOUNDTYPE.SOUND, "explosion_medium");
	}

	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() - 2 * size + 60;
		rect[1] = getY() - 2 * size + 75;
		rect[2] = getWidth() + 4 * size - 120;
		rect[3] = getHeight() + 4 * size - 150;
		return rect;
	}

	////////// TICK ////////////

	private final int sizeMin = 0;
	private final int sizeMax = Size.S / 2;
	private int size;

	private void growingAnimation() {
		if (isSelected())
			size = size + 3;
		else
			size = size - 3;

		if (size > sizeMax)
			size = sizeMax;

		if (size < sizeMin)
			size = sizeMin;
	}

	private int blinking_time = 12;
	private boolean blinking = true;

	private void blinkingAnimation() {
		if (blinking == false)
			return;

		if (isValidated())
			blinking_time--;

		if (blinking_time <= 0) {
			blinking = false;
		}
	}

	@Override
	public void tick() {
		arravingAnimation();
		growingAnimation();
		blinkingAnimation();
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
	private BufferedImage blink_img;

	private void loadImages() {
		normal_img = new ImageTask().loadImage("textures/cards/" + "normal");
		gold_img = new ImageTask().loadImage("textures/cards/" + "gold");

		selection_img = new ImageTask().loadImage("textures/cards/" + "selection");
		blink_img = new ImageTask().loadImage("textures/cards/" + "blink");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		int[] rect = new int[] { getX() - 2 * size, getY() - 2 * size, getWidth() + 4 * size, getHeight() + 4 * size, };

		g.drawImage(normal_img, rect[0], rect[1], rect[2], rect[3], null);

		if (isSelected())
			g.drawImage(selection_img, rect[0], rect[1], rect[2], rect[3], null);

		if (isValidated()) {
			BufferedImage img = blinking ? blink_img : gold_img;
			g.drawImage(img, rect[0], rect[1], rect[2], rect[3], null);
		}

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
		boolean available = isValidated() == false;
		boolean puzzleRunning = ((ChestPuzzle) getPuzzle()).isClosing() == false;

		return clickable && available && puzzleRunning;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected())
			setValidated(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
