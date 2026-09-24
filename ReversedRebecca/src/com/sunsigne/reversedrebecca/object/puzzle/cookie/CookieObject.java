package com.sunsigne.reversedrebecca.object.puzzle.cookie;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.menu.achievement.AchievementTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;

public class CookieObject extends PuzzleObject implements SheetableImage, Highlightable, MouseUserEvent, GamepadEvent {

	public CookieObject(Puzzle puzzle, int x, int y) {
		super(puzzle, false, x, y, 3 * Size.XL, 3 * Size.XL);
	}

	////////// NAME ////////////

	protected String getName() {
		return "COOKIE";
	}

	@Override
	public String toString() {
		return "PUZZLE : " + getName();
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE_MOVER;
	}

	////////// TICK ////////////

	private final int SHRINK_FACTOR = 9;
	private int shrink;
	private boolean ready;
	private int time;

	@Override
	public void tick() {
		updatePos();

		time++;
		if (time == ((CookiePuzzle) getPuzzle()).getDelayBeforeReady())
			ready = true;

		if (shrink > 0)
			shrink--;
	}

	private void updatePos() {
		var counter = ((CookiePuzzle) getPuzzle()).getCounter();
		if (counter == null)
			return;

		setVelX(counter.getVelX());
		setVelY(counter.getVelY());
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		return isSelected();
	}

	@Override
	public int getHighlightSize() {
		return 0;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage highlight;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetSize() {
		return 48;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie");
			image = getSheetSubImage(sheet);
			highlight = getSheetSubImage(sheet, 2);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		int x = getX() + shrink;
		int y = getY() + shrink;
		int w = getWidth() - 2 * (shrink);
		int h = getHeight() - 2 * (shrink);

		g.drawImage(getImage(), x, y, w, h, null);
		drawHighlight(g, highlight, shrink, shrink, -2 * shrink, -2 * shrink);
		
		reversedCookieAchievement();
	}

	private void reversedCookieAchievement() {
		if(getSize() - 2 * (shrink) < - 2 * Size.L)
			new AchievementTask().unlock("reversedcookie");
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public boolean isSelected() {
		return MouseUserEvent.super.isSelected() && isMotionless() && ready;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		shrink = shrink + SHRINK_FACTOR;
		new SoundTask().playSound(SOUNDTYPE.SOUND, "button");
		LAYER.PUZZLE.addObject(new LittleCookieObject(getPuzzle()));
		addToCounter(1);
	}

	private void addToCounter(int amount) {
		var counter = ((CookiePuzzle) getPuzzle()).getCounter();
		if (counter == null)
			return;

		counter.addToCount(amount);
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
		if (ButtonEvent.isActionButton(e))
			mousePressed(null);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
