package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootCritTool extends ChestLoot implements Difficulty {

	protected ChestLootCritTool(ChestCard card, ToolPlayer tool) {
		super(card);
		this.tool = tool;
	}

	@Override
	public boolean isValid() {
		return getCriticalChance() < 100;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT CRIT";
		return clazz + " : " + tool.getName().toUpperCase() + " = " + getCriticalChance();
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		tool.setCriticalChance(getNextCriticalChance());
	}

	private int getNextCriticalChance() {
		switch (getCriticalChance()) {
		case 15:
			return 25;
		case 25:
			return 50;
		case 50:
			return 100;
		}

		return 15;
	}

	////////// TOOL////////////

	private ToolPlayer tool;

	public int getCriticalChance() {
		return tool.getCriticalChance();
	}

	////////// DIFFICULTY ////////////

	@Override
	public LVL getDifficulty() {
		return LVL.NULL;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		// defined by the current player lvl : shouldn't be changed.
	}

	////////// TEXTURE ////////////

	private BufferedImage tool_img;
	private BufferedImage upgrade_img;
	private BufferedImage upgrade_gold_img;
	private String firstLine;
	private String secondLine;

	@Override
	public int getSheetColCriterion() {
		switch (getCriticalChance()) {
		case 15:
			return 1;
		case 25:
			return 2;
		case 50:
			return 3;
		}

		return 1;
	}

	@Override
	public void refresh() {
		tool_img = null;
		upgrade_img = null;
		upgrade_gold_img = null;
		secondLine = null;
	}

	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "percentage");
			tool_img = getSheetSubImage(sheet, getSheetColCriterion(), 1, 64, 32);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			String number = isNumberSettings() ? "_number" : "";
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool" + number);
			upgrade_img = getSheetSubImage(sheet, 1, tool.getNum(), 16, 16);
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			String number = isNumberSettings() ? "_number" : "";
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool" + number);
			upgrade_gold_img = getSheetSubImage(sheet, 8, tool.getNum(), 16, 16);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null) {
			String toolName = new Translatable().getTranslatedText(tool.getName() + "Plural", FilePath.TOOL);
			String verb = new Translatable().getTranslatedText("DEAL_CRITICAL_HIT_MORE_OFTEN_ONE", FilePath.TECHTREE);

			firstLine = toolName + " " + verb;
		}

		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null)
			secondLine = new Translatable().getTranslatedText("DEAL_CRITICAL_HIT_MORE_OFTEN_TWO", FilePath.TECHTREE);
		return secondLine;
	}

	////////// RENDER ////////////

	@Override
	public int[] cutsomizedDimensions() {
		int u = 8;
		int[] dim = { - 10 * u, 18 * u, 20 * u, 0, 10 * u, -18 * u, -20 * u, 0 };
		return dim;
	}

}
