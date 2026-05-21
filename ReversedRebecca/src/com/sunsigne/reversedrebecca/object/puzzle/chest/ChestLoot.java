package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_DESIGN;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class ChestLoot extends PuzzleObject implements SheetableImage {

	protected ChestLoot(ChestCard card) {
		super(card.getPuzzle(), false, card.getX(), card.getY(), card.getWidth(), card.getHeight());
		this.card = card;
	}

	public abstract boolean isValid();

	protected boolean isNumberSettings() {
		return ActionOption.getDesign() == ACTION_DESIGN.NUMBER;
	}
	
	////////// CARD ////////////

	private ChestCard card;

	public ChestCard getCard() {
		return card;
	}

	////////// PICK UP////////////

	public abstract void pickUp();

	////////// PHYSICS ////////////

	@Override
	public PhysicLaw[] getPhysicLinker() {
		return PhysicLinker.PUZZLE;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		setX(card.getX());
		setY(card.getY());
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}
	
	public abstract void refresh();
	
	public abstract BufferedImage getToolImage();

	public abstract BufferedImage getUpgradeImage();

	public abstract BufferedImage getUpgradeGoldImage();

	public abstract String getFirstLine();

	public abstract String getSecondLine();

	////////// RENDER ////////////

	public int[] cutsomizedDimensions() {
		int[] dim = { 0, 0, 0, 0, 0, 0, 0, 0 };
		return dim;
	}

	@Override
	public void render(Graphics g) {
		int size = Size.XL;
		int[] dim = cutsomizedDimensions();
		int[] tempRect = card.getRect();
		int[] rect = new int[] { tempRect[0] + tempRect[2] / 2 - size / 2, tempRect[1] + Size.XS, size, size };

		// tool
		g.drawImage(getToolImage(), dim[0] + rect[0], dim[1] + rect[1], dim[2] + rect[2], dim[3] + rect[3], null);

		// upgrade
		BufferedImage img = card.isPickedUp() ? getUpgradeGoldImage() : getUpgradeImage();
		g.drawImage(img, dim[4] + rect[0] - rect[2] / 2, dim[5] + rect[1] + Size.L + Size.XS / 2,
				dim[6] + rect[2] + rect[2], dim[7] + rect[3], null);

		drawText(g, font, rect);
	}

	private Font font = new FontTask().createNewFont("AGENCYB.ttf", 38f);

	private void drawText(Graphics g, Font font, int[] rect) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawLine(g, getFirstLine(), rect[0] + rect[2] / 2, rect[1] + 2 * Size.XL + Size.XS / 2);
		drawLine(g, getSecondLine(), rect[0] + rect[2] / 2, rect[1] + 2 * Size.XL + Size.S);

		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	private void drawLine(Graphics g, String text, int x, int y) {
		int rect[] = new int[] { x, y, 0, 0 };
		new TextDecoration().drawCenteredString(g, font, text, Color.BLACK, DIRECTION.NULL, rect);
	}

}
