package com.sunsigne.reversedrebecca.object.puzzle.chest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class ChestLootBonusHp extends ChestLootMaxHp {

	protected ChestLootBonusHp(ChestCard card) {
		super(card);
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "CHEST LOOT";
		return clazz + " : " + "BONUS HP";
	}

	////////// PICK UP////////////

	@Override
	public void pickUp() {
		Player player = new PlayerFinder().getPlayer();
		if (player != null)
			player.addBonusHp(6);
	}

	////////// TEXTURE ////////////

	private BufferedImage tool_img;
	private String secondLine;

	@Override
	public int getSheetRowCriterion() {
		return 2;
	}
	
	@Override
	public BufferedImage getToolImage() {
		if (tool_img == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "heart");
			BufferedImage heart = getSheetSubImage(sheet);

			tool_img = new BufferedImage(2 * heart.getWidth(), 2 * heart.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = tool_img.createGraphics();
			g2d.drawImage(heart, 0, 0, null);
			g2d.drawImage(heart, heart.getWidth(), 0, null);
			g2d.drawImage(heart, heart.getWidth() / 2, (3 * heart.getHeight()) / 4, null);
			g2d.dispose();

		}
		return tool_img;
	}

	@Override
	public String getSecondLine() {
		if (secondLine == null)
			secondLine = new Translatable().getTranslatedText("BONUSHP", FilePath.TECHTREE);
		return secondLine;
	}

	////////// RENDER ////////////

	@Override
	public int[] cutsomizedDimensions() {
		int u = 8;
		int[] dim = { -6 * u, 7 * u, 12 * u, 12 * u, 0, 0, 0, 0 };
		return dim;
	}

}
