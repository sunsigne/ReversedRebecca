package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.PuzzleObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.system.Size;

public abstract class ChestLoot extends PuzzleObject {

	protected ChestLoot(ChestCard card, boolean widerUpgradeImg) {
		super(card.getPuzzle(), false, card.getX(), card.getY(), card.getWidth(), card.getHeight());
		this.card = card;
		this.widerUpgradeImg = widerUpgradeImg;
	}

	public abstract boolean isValid();
	
	////////// CARD ////////////

	private ChestCard card;

	public ChestCard getCard() {
		return card;
	}

	////////// PICK UP////////////

	public abstract void pickUp();

	////////// TICK ////////////

	@Override
	public void tick() {
		setX(card.getX());
		setY(card.getY());
	}

	////////// TEXTURE ////////////

	public abstract BufferedImage getToolImage();

	public abstract BufferedImage getUpgradeImage();

	public abstract BufferedImage getUpgradeGoldImage();

	public abstract String getFirstLine();

	public abstract String getSecondLine();

	////////// RENDER ////////////

	private boolean widerUpgradeImg;
	
	@Override
	public void render(Graphics g) {
		int size = Size.XL;
		int[] tempRect = card.getRect();
		int[] rect = new int[] { tempRect[0] + tempRect[2] / 2 - size / 2, tempRect[1] + Size.XS, size, size };

		// tool
		g.drawImage(getToolImage(), rect[0], rect[1], rect[2], rect[3], null);

		// upgrade
		BufferedImage img = card.isPickedUp() ? getUpgradeGoldImage() : getUpgradeImage();
		int widerImg = widerUpgradeImg ? rect[2] : 0;
		g.drawImage(img, rect[0] - widerImg / 2, rect[1] + Size.L + Size.XS / 2, rect[2] + widerImg, rect[3], null);

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
