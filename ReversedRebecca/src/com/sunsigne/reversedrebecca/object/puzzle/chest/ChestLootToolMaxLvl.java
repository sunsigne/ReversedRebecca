package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.loot.ToolObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootToolMaxLvl extends ChestLoot implements Difficulty {

	protected ChestLootToolMaxLvl(ChestCard card, ToolPlayer tool) {
		super(card);
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

		if (new DifficultyComparator().isPositiveUpgade(tool.getStartDifficulty(), tool.getMaxDifficulty()) == false)
			return;

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
	public void refresh() {
		upgrade_img = null;
		firstLine = null;
		secondLine = null;
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
			int row = isNumberSettings() ? 2 : 1;
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "battery");
			upgrade_img = getSheetSubImage(sheet, getSheetColCriterion(), row, 64, 32);
			getUpgradeGoldImage(); // ensure to load the same difficulty image
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "battery");
			upgrade_gold_img = getSheetSubImage(sheet, getSheetColCriterion(), 3, 64, 32);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null)
			firstLine = new Translatable().getTranslatedText("YOU_CAN_USE", FilePath.TECHTREE);
		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine != null)
			return secondLine;

		secondLine = new Translatable().getTranslatedText(tool.getName() + "Plural" + getDifficulty().getName(),
				FilePath.TOOL);

		if (isNumberSettings() == false)
			return secondLine;

		String number = " " + getDifficulty().ordinal();
		secondLine = new Translatable().getTranslatedText(tool.getName() + "Plural", FilePath.TOOL);
		secondLine = secondLine.concat(number);

		return secondLine;
	}

}
