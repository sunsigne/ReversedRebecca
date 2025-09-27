package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.BombingDoorUpgrade;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootBombingDoor extends ChestLoot {

	protected ChestLootBombingDoor(ChestCard card, ToolPlayer tool) {
		super(card, true);
		this.tool = tool;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT";
		return clazz + " : " + "BOMBING DOOR";
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		new BombingDoorUpgrade().setValue(true);
	}

	////////// TOOL////////////

	private ToolPlayer tool;

	////////// TEXTURE ////////////

	private BufferedImage tool_img;
	private BufferedImage upgrade_img;
	private BufferedImage upgrade_gold_img;
	private String firstLine;
	private String secondLine;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool");
			tool_img = getSheetSubImage(sheet, 3, tool.getNum(), 16, 16);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "upgrade");
			upgrade_img = getSheetSubImage(sheet, 1, 1, 64, 32);
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "upgrade");
			upgrade_gold_img = getSheetSubImage(sheet, 1, 2, 64, 32);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null)
			firstLine = new Translatable().getTranslatedText("TOOLMAXLVL", FilePath.TECHTREE);
		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null) {
			secondLine = new Translatable().getTranslatedText(tool.getName() + "Plural", FilePath.TOOL);
			secondLine = secondLine
					.concat(" " + new Translatable().getTranslatedText("BOMBINGDOOR", FilePath.TECHTREE) + " ");
			secondLine = secondLine.concat(new Translatable().getTranslatedText("Door" + "Plural", FilePath.TOOL));
		}
		return secondLine;
	}

	////////// RENDER ////////////

	@Override
	public int[] cutsomizedDimensions() {
		int u = 8;
		int[] dim = { -12 * u, 0, u, u, -7 * u, -5 * u, 12 * u, 8 * u };
		return dim;
	}

}
