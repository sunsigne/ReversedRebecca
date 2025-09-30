package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootMaxHp extends ChestLoot {

	protected ChestLootMaxHp(ChestCard card) {
		super(card);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT";
		return clazz + " : " + "MAX HP";
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		Player player = new PlayerFinder().getPlayer();
		if (player != null)
			player.setMaxHp(player.getMaxHp() + 2);
	}

	////////// TEXTURE ////////////

	private BufferedImage tool_img;
	private BufferedImage upgrade_img;
	private BufferedImage upgrade_gold_img;
	private String firstLine;
	private String secondLine;

	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "heart");
			tool_img = getSheetSubImage(sheet);
		}
		return tool_img;
	}

	@Override
	public BufferedImage getUpgradeImage() {
		if (upgrade_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "infinity");
			upgrade_img = getSheetSubImage(sheet, 2, 2, 32, 32);
		}
		return upgrade_img;
	}

	@Override
	public BufferedImage getUpgradeGoldImage() {
		if (upgrade_gold_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/techtree/" + "infinity");
			upgrade_gold_img = getSheetSubImage(sheet, 2, 2, 32, 32);
		}
		return upgrade_gold_img;
	}

	@Override
	public String getFirstLine() {
		if (firstLine == null)
			firstLine = new Translatable().getTranslatedText("YOU_OBTAIN", FilePath.TECHTREE);
		return firstLine;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null)
			secondLine = new Translatable().getTranslatedText("MAXHP", FilePath.TECHTREE);
		return secondLine;
	}

	////////// RENDER ////////////

	@Override
	public int[] cutsomizedDimensions() {
		int u = 8;
		int[] dim = { -3 * u, 5 * u, 6 * u, 6 * u, 0, 0, 0, 0 };
		return dim;
	}

}
