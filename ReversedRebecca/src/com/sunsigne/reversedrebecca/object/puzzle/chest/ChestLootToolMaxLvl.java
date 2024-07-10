package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootToolMaxLvl extends ChestLoot implements Difficulty {

	protected ChestLootToolMaxLvl(ChestCard card, ToolPlayer tool) {
		super(card, true);
		this.tool = tool;
	}

	@Override
	public boolean isValid() {
		return getDifficulty() != LVL.PURPLE;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT MAX";
		return clazz + " : " + tool.getName().toUpperCase() + " = " + getDifficulty();
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		tool.setMaxDifficulty(getDifficulty());
	}

	////////// TOOL////////////

	private ToolPlayer tool;

	////////// DIFFICULTY ////////////

	@Override
	public LVL getDifficulty() {
		return tool.getMaxDifficulty().getNext();
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		// defined by the current player lvl : should't be changed.
	}

	////////// TEXTURE ////////////

	private BufferedImage tool_img;
	private BufferedImage upgrade_img;
	private BufferedImage upgrade_gold_img;
	private String firstLine;
	private String secondLine;

	@Override
	public int getSheetColCriterion() {
		return getDifficulty().ordinal() - 1;
	}

	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool");
			tool_img = getSheetSubImage(sheet, 1, tool.getNum(), 16, 16);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "battery");
			upgrade_img = getSheetSubImage(sheet, getSheetColCriterion(), 1, 64, 32);
			getUpgradeGoldImage(); // ensure to load the same difficulty image
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "battery");
			upgrade_gold_img = getSheetSubImage(sheet, getSheetColCriterion(), 2, 64, 32);
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
		if (secondLine == null)
			secondLine = new Translatable().getTranslatedText(tool.getName() + "Plural" + getDifficulty().getName(),
					FilePath.TOOL);
		return secondLine;
	}

}
