package com.sunsigne.reversedrebecca.object.puzzler.chest;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.TripleAction;
import com.sunsigne.reversedrebecca.object.puzzler.OpenPuzzleAction;
import com.sunsigne.reversedrebecca.object.puzzler.PuzzlerObject;
import com.sunsigne.reversedrebecca.object.puzzler.RequirementBubbleObject;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.world.World;

public class ChestObject extends PuzzlerObject {

	public ChestObject(LVL lvl, int num, int x, int y, boolean little) {
		super(lvl, x, y);

		this.little = little;
		carvingHitbox(little);

		if (num < 0)
			return;

		if (World.get() != null)
			lootFile = ("maps/" + World.get().getMapName() + "/" + getName().toUpperCase() + "-0" + num + ".csv");
	}

	private boolean little;

	public boolean isLittle() {
		return little;
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "chest";
	}

	////////// LOOTFILE ////////////

	private String lootFile;

	public String getLootFile() {
		return lootFile;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetRowCriterion() {
		return little ? 4 : 2;
	}

	@Override
	public BufferedImage getImage() {
		if (image == null) {
			String number = isNumberSettings() ? "_number" : "";
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "chest" + number);
			image = getSheetSubImage(sheet);
		}
		return image;
	}

	@Override
	public BufferedImage getHighlightImage() {
		if (highlightImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzler/" + "puzzler" + "_" + "highlight");
			int col = isLittle() ? 1 : 0;
			highlightImage = getSheetSubImage(sheet, 1 + col, 2, getSheetWidth() + 2, getSheetHeight() + 2);
		}
		return highlightImage;
	}

	////////// INTERACTION ////////////

	private TripleAction tripleAction;

	@Override
	public TripleAction getTripleAction() {
		return tripleAction;
	}

	@Override
	protected void loadTripleAction() {
		OpenPuzzleAction unlockAction = new UnlockChestAction(this);

		RequirementBubbleObject requirementUnlock = new RequirementBubbleObject(getX(), getY(),
				unlockAction.getToolPlayer(), getDifficulty());

		tripleAction = new TripleAction(requirementUnlock, unlockAction, null, null);
	}

	////////// COLLISION ////////////

	private int hitboxX, hitboxY;
	private int hitboxW = getWidth();
	private int hitboxH = getHeight();

	private void carvingHitbox(boolean little) {
		if (little)
			setBounds(hitboxX + 1, hitboxY + 5, 13, 11);
	}

	// width and height should be between 0 and 16
	public void setBounds(int x, int y, int width, int height) {
		int pixel = 16;
		int ratio = Size.M / pixel;
		this.hitboxX = x * ratio;
		this.hitboxY = y * ratio;
		this.hitboxW = width * ratio;
		this.hitboxH = height * ratio;
	}

	@Override
	public Rectangle getBounds() {
		int x = getX() + hitboxX;
		int y = getY() + hitboxY;
		int w = hitboxW;
		int h = hitboxH;
		return new Rectangle(x, y, w, h);
	}

}