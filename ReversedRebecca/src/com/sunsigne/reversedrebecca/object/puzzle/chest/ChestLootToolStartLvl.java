package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootToolStartLvl extends ChestLoot implements Difficulty {

	protected ChestLootToolStartLvl(ChestCard card, ToolPlayer tool) {
		super(card);
		this.tool = tool;
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {

	}

	////////// TOOL////////////

	private ToolPlayer tool;

	////////// DIFFICULTY ////////////

	@Override
	public LVL getDifficulty() {
		return tool.getDifficulty().getNext();
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
	public BufferedImage getToolImage() {
		if (tool_img == null)
			tool_img = new ImageTask()
					.loadImage("textures/" + "tools/" + tool.getName() + "_" + getDifficulty().getName());
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null)
			upgrade_img = new ImageTask()
					.loadImage("textures/" + "techtree/" + "infinity_" + getDifficulty().getName());
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null)
			upgrade_gold_img = new ImageTask().loadImage("textures/" + "techtree/" + "infinity_" + "gold");
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null)
			firstLine = new Translatable().getTranslatedText("TOOLLVL1", "techtree.csv");
		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null) {
			String line = new Translatable().getTranslatedText("TOOLLVL2", "techtree.csv");
			String card_tool = new Translatable().getTranslatedText(tool.getName() + getDifficulty().getName(),
					"tools.csv");
			secondLine = line + card_tool;
		}
		return secondLine;
	}

}
