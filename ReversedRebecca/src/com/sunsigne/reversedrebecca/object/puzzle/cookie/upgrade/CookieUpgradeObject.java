package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieCounterObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
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
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public abstract class CookieUpgradeObject extends PuzzleObject
		implements SheetableImage, Highlightable, MouseUserEvent, GamepadEvent {

	public CookieUpgradeObject(Puzzle puzzle, int x, int y) {
		super(puzzle, false, x, y, 4 * Size.L, Size.L);
	}

	////////// USEFULL ////////////

	protected CookieCounterObject getCounter() {
		return ((CookiePuzzle) getPuzzle()).getCounter();
	}

	////////// NAME ////////////

	protected String getName() {
		return "COOKIE UPGRADE";
	}

	@Override
	public String toString() {
		String count = "COUNT = " + getCount();
		String amount = "AMOUNT BY SECOND = " + getAmountBySecond();
		String cost = "COST = " + getCost();
		return "PUZZLE : " + getName() + " : " + count + " : " + amount + " : " + cost;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// UPGRADE ////////////

	public abstract COOKIE_UPGRADE getType();

	public abstract int getAmountBySecond();

	public abstract COOKIE_UPGRADE getCostType();

	public abstract int getCost();

	protected boolean canBuy() {
		return getCounter().getCount() >= getCost();
	}

	private float getAmountByTick() {
		return ((float) getAmountBySecond()) / ((float) Game.SEC);
	}

	////////// COUNT ////////////

	private int count;

	public int getCount() {
		return count;
	}

	public void addToCount(int amount) {
		count = count + amount;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		getCounter().addToCount(getCount() * getAmountByTick());
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		return isSelected();
	}

	@Override
	public int getHighlightSize() {
		return 8;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage expensive_image;
	private BufferedImage highlight;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetWidth() {
		return 128;
	}

	@Override
	public int getSheetHeight() {
		return 32;
	}

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie");
			image = getSheetSubImage(sheet);
			expensive_image = getSheetSubImage(sheet, 1, 3, getSheetWidth(), getSheetHeight());
			sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie_highlight");
			highlight = getSheetSubImage(sheet, 1, 1, getSheetWidth() + 4, getSheetHeight() + 4);
		}
		return image;
	}

	////////// RENDER ////////////

	private Font text_font = new FontTask().createNewFont("square_sans_serif_7.ttf", 45f);
	private Font cost_font = new FontTask().createNewFont("square_sans_serif_7.ttf", 30f);
	private Font count_font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 60f);

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawHighlight(g, highlight);
		drawName(g);
		drawCost(g);
		drawCount(g);

		if (canBuy() == false)
			g.drawImage(expensive_image, getX(), getY(), getWidth(), getHeight(), null);
	}

	private void drawName(Graphics g) {
		String text = "cursor";
		int rect[] = new int[] { getX() + 160, getY() - 20, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, text_font, text, DIRECTION.LEFT, rect);
	}

	private void drawCost(Graphics g) {
		int rect[] = new int[] { getX() + 220, getY() + 25, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, cost_font, String.valueOf(getCost()), getCostColor(), Color.BLACK,
				DIRECTION.LEFT, rect);
	}

	protected Color getCostColor() {
		if (canBuy() == false)
			// red
			return new Color(180, 50, 50);
		else
			return Color.WHITE;
	}

	private void drawCount(Graphics g) {
		int rect[] = new int[] { getX() - 25, getY() - 5, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, count_font, String.valueOf(count), DIRECTION.RIGHT, rect);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public boolean isSelected() {
		return MouseUserEvent.super.isSelected() && canBuy();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_validate");
		getCounter().addToCount(-getCost());
		addToCount(1);
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
