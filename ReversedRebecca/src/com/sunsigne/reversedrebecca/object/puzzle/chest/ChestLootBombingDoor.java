package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootBombingDoor extends ChestLoot {

	protected ChestLootBombingDoor(ChestCard card, ToolPlayer tool, ToolPlayer replacedTool) {
		super(card, true);
		this.tool = tool;
		this.replacedTool = replacedTool;
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

	}

	////////// TOOL////////////

	private ToolPlayer tool;
	private ToolPlayer replacedTool;

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
			tool_img = getSheetSubImage(sheet, 1, tool.getNum(), 16, 16);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool");
			upgrade_img = getSheetSubImage(sheet, 1, replacedTool.getNum(), 16, 16);
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool");
			upgrade_gold_img = getSheetSubImage(sheet, 1, replacedTool.getNum(), 16, 16);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null) {
			firstLine = new Translatable().getTranslatedText(tool.getName() + "Plural", FilePath.TOOL);
			firstLine = new FormattedString().capitalize(firstLine);
			firstLine = firstLine.concat(new Translatable().getTranslatedText("BOMBINGDOOR1", FilePath.TECHTREE));
		}
		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null) {
			secondLine = new Translatable().getTranslatedText("BOMBINGDOOR2", FilePath.TECHTREE);
			secondLine = secondLine
					.concat(new Translatable().getTranslatedText(replacedTool.getName() + "Plural", FilePath.TOOL));
		}
		return secondLine;
	}

}
