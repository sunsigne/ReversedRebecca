package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GameObject;
import com.sunsigne.reversedrebecca.object.Wall.COLOR;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.ActionOption.ACTION_HIGHLIGHT;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.chest.ChestObject;
import com.sunsigne.reversedrebecca.object.puzzler.door.DoorObject;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.font.TextsOption;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Window;

public class ActionOptionPreview extends GameObject implements SheetableImage {

	public ActionOptionPreview(int x, int y) {
		super(x, y);
		loadPuzzler();
		loadImages();
	}

	////////// NAME ////////////

	public String toString() {
		var clazz = "ACTION OPTION PREVIEW ";
		return clazz + " : " + getX() + "-" + getY();
	}

	////////// TICK ////////////

	private Font font;

	@Override
	public void tick() {
		float size = 24f / (float) Math.sqrt(Window.SCALE_X);
		font = new FontTask().createNewFont("square_sans_serif_7.ttf", size * TextsOption.getSize());
	}

	////////// TEXTURE ////////////

	private PuzzlerObject puzzler;

	private void loadPuzzler() {
		puzzler = new DoorObject(LVL.RED, COLOR.WHITE, getX(), getY()) {
			@Override
			public boolean getHighlightCondition() {
				return ActionOption.getHighlight() == ACTION_HIGHLIGHT.BRIGHT;
			};
		};
	}
	
	private BufferedImage rebecca_img;

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return -1;
	}

	private void loadImages() {
		BufferedImage sheet = null;

		sheet = new ImageTask().loadImage("textures/characters/rebecca/" + "rebecca");
		rebecca_img = getSheetSubImage(sheet, 1, 2, 16, 16);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (rebecca_img == null)
			return;

		g.drawImage(rebecca_img, getX(), getY(), getWidth(), getHeight(), null);
		
		int gap = 100;
		g.drawImage(puzzler.getImage(), getX() + gap, getY(), getWidth(), getHeight(), null);
		puzzler.drawHighlight(g, puzzler.getHighlightImage(), gap, 0, 0, 0);

		gap = (int) (TextsOption.getSize() * 100) - 130;
		int[] rect = new int[] { getX() - 160 - gap, getY(), getWidth(), getHeight() };
		String text = puzzler.getTripleAction().getAction(0).getDisplayedText();
		new TextDecoration().drawOutlinesString(g, font, text, DIRECTION.NULL, rect);
	}

}