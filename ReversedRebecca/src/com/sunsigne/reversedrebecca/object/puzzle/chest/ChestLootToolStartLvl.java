package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootToolStartLvl extends ChestLoot implements Difficulty {

	protected ChestLootToolStartLvl(ChestCard card, ToolPlayer tool) {
		super(card, false);
		this.tool = tool;
	}

	@Override
	public boolean isValid() {
		return getDifficulty() != LVL.PURPLE;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT START";
		return clazz + " : " + tool.getName().toUpperCase() + " = " + getDifficulty();
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		tool.setStartDifficulty(getDifficulty());

		Player player = new PlayerFinder().getPlayer();
		int x = player == null ? 0 : player.getX();
		int y = player == null ? 0 : player.getY();

		ToolObject object = new ToolObject(tool, tool.getStartDifficulty(), x, y);
		object.pickup(false);
	}

	////////// TOOL////////////

	private ToolPlayer tool;

	////////// DIFFICULTY ////////////

	@Override
	public LVL getDifficulty() {
		return tool.getStartDifficulty().getNext();
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
		return 1 + getDifficulty().ordinal();
	}

	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/tools/" + "tool");
			tool_img = getSheetSubImage(sheet, getSheetColCriterion(), tool.getNum(), 16, 16);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "infinity");
			upgrade_img = getSheetSubImage(sheet, getSheetColCriterion() - 1, 1, 32, 32);
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "infinity");
			upgrade_gold_img = getSheetSubImage(sheet, 1, 2, 32, 32);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null) {
			String line = new Translatable().getTranslatedText("TOOLSTARTLVL", FilePath.TECHTREE);
			String determiner = new Translatable().getTranslatedText(tool.getName() + "Permanent" + "Determiner",
					FilePath.TOOL);

			firstLine = line + " " + determiner;
		}

		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null)
			secondLine = new Translatable().getTranslatedText(tool.getName() + "Permanent" + getDifficulty().getName(),
					FilePath.TOOL);
		return secondLine;
	}

}
