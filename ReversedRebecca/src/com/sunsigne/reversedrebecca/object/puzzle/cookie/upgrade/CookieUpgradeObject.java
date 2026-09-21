package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.Highlightable;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.object.puzzle.cookie.CookieCounting;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.cookie.CookiePuzzle;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseController;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public abstract class CookieUpgradeObject extends PuzzleObject
		implements CookieCounting, SheetableImage, Highlightable, MouseUserEvent, GamepadEvent {

	public CookieUpgradeObject(Puzzle puzzle, int x, int y, COOKIE_UPGRADE unlockingType, int unlockingAt,
			boolean nerfed) {
		super(puzzle, false, x, y, 4 * Size.L, Size.L);
		this.unlockingType = unlockingType;
		this.unlockingAt = unlockingAt;
		this.nerfed = nerfed;

	}

	////////// USEFULL ////////////

	protected CookieCounting getCounter(COOKIE_UPGRADE upgrade) {
		return ((CookiePuzzle) getPuzzle()).getUpgrade(upgrade);
	}

	////////// NAME ////////////

	protected String getName() {
		return "COOKIE UPGRADE";
	}

	////////// TEXT ////////////

	private Font text_font = new FontTask().createNewFont("square_sans_serif_7.ttf", 45f);
	private Font cost_font = new FontTask().createNewFont("square_sans_serif_7.ttf", 30f);
	private Font count_font = new FontTask().createNewFont("DigitalNumbers-Regular.ttf", 60f);
	private String text;

	private String getText() {
		if (text == null)
			text = new Translatable().getTranslatedText("Cookie" + getType().getName(), FilePath.PUZZLE);
		return text;
	}

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// UPGRADE ////////////

	protected boolean nerfed;

	public abstract COOKIE_UPGRADE getType();

	public abstract COOKIE_UPGRADE getAmountType();

	public abstract int getAmountBySecond();

	public abstract COOKIE_UPGRADE getCostType();

	public abstract int getInitialCost();

	private float purchased;

	public int getCost() {
		return (int) ((float) (getInitialCost() * Math.pow(1.1, purchased)));
	}

	protected boolean canBuy() {
		return unlocked && getCounter(getCostType()).getCount() >= getCost();
	}

	private float getAmountByTick() {
		return ((float) getAmountBySecond()) / ((float) Game.SEC);
	}

	public abstract GenericListener getUnlockingAction();

	////////// COUNT ////////////

	private boolean unlocked;

	@Override
	public boolean isUnlocked() {
		return unlocked;
	}

	private float count;

	@Override
	public float getCount() {
		return count;
	}

	@Override
	public void addToCount(float amount) {
		count = count + amount;
	}

	////////// TICK ////////////

	private COOKIE_UPGRADE unlockingType;
	private int unlockingAt;

	@Override
	public void tick() {
		getCounter(getAmountType()).addToCount(getCount() * getAmountByTick());

		if (unlocked)
			return;

		if (getCounter(unlockingType).getCount() >= unlockingAt) {
			unlocked = true;
			GenericListener unlockingAction = getUnlockingAction();
			if (unlockingAction != null)
				unlockingAction.doAction();
		}
	}

	////////// HIGHLIGHT ////////////

	@Override
	public boolean getHighlightCondition() {
		if (ControllerManager.getInstance().isUsingGamepad())
			return isSelected();
		else
			return isSelected() && canBuy();
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
	public int getSheetRowCriterion() {
		return 2;
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
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie_upgrade");
			image = getSheetSubImage(sheet);
			expensive_image = getSheetSubImage(sheet, 2, 2, getSheetWidth(), getSheetHeight());
			highlight = getSheetSubImage(sheet, 1, 3, getSheetWidth() + 4, getSheetHeight() + 4);
		}
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (unlocked == false)
			return;

		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawType(g);
		drawHighlight(g, highlight);
		drawCount(g);
		drawName(g);
		drawCost(g);

		if (canBuy() == false)
			g.drawImage(expensive_image, getX(), getY(), getWidth(), getHeight(), null);

		drawAmountBySecond(g);
	}

	protected void drawType(Graphics g) {
		g.drawImage(getType().getImage(), getX(), getY(), Size.L, Size.L, null);
	}

	private void drawCount(Graphics g) {
		int rect[] = new int[] { getX() - 25, getY() - 5, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, count_font, String.valueOf((int) getCount()), Color.GRAY,
				Color.BLACK, DIRECTION.RIGHT, rect);
	}

	private void drawName(Graphics g) {
		int rect[] = new int[] { getX() + 160, getY() - 20, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, text_font, getText(), DIRECTION.LEFT, rect);
	}

	private void drawCost(Graphics g) {
		g.drawImage(getCostType().getImage(), getX() + 178, getY() + 72, Size.XS, Size.XS, null);
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

	private void drawAmountBySecond(Graphics g) {
		int rect[] = new int[] { getX() + getWidth() / 2 + 40, getY() + 2, getWidth(), getHeight() };
		new TextDecoration().drawOutlinesString(g, cost_font, String.valueOf(getAmountBySecond()), DIRECTION.NULL,
				rect);
		g.drawImage(getAmountType().getImage(), getX() + getWidth() + 70, getY() + 50, Size.XS, Size.XS, null);
	}

	////////// MOUSE ////////////

	private MouseController mouseController = new MouseController(this);

	@Override
	public MouseController getMouseController() {
		return mouseController;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isSelected() == false)
			return;

		if (canBuy() == false)
			return;

		new SoundTask().playSound(SOUNDTYPE.SOUND, "button_validate");
		getCounter(getCostType()).addToCount(-getCost());
		addToCount(1);
		purchased++;
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
