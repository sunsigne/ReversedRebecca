package com.sunsigne.reversedrebecca.object.loot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.GoalObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class HeartLoot extends LootObject {

	public HeartLoot(int x, int y) {
		super(x, y);

		loadImages();
	}

	////////// NAME ////////////

	@Override
	public String toString() {
		var clazz = "LOOT";
		var name = "HEART";
		var goal = new GoalObject(getX(), getY(), true);
		return clazz + " : " + name + " : " + goal.getX() + "-" + goal.getY();
	}

	////////// HIGHLIGHT ////////////

	@Override
	public int getHighlightSize() {
		return 0;
	}

	////////// TEXTURE ////////////

	protected BufferedImage image;
	protected BufferedImage blinking_image;

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public void refresh() {
		loadImages();
	}

	private void loadImages() {
		BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "heart");
		image = getSheetSubImage(sheet);
		blinking_image = getSheetSubImage(sheet, 3, 2, getSheetWidth(), getSheetHeight());
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		if (image == null)
			return;

		g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
		drawHighlight(g, blinking_image);
	}

	////////// COLLISION ////////////

	@Override
	public String getTextWhenLooted() {
		return "+ 1 " + new Translatable().getTranslatedText("HP", FilePath.TECHTREE);
	}

	@Override
	public void actionWhenLooted() {
		Player player = new PlayerFinder().getPlayer();
		if (player == null)
			return;

		player.addHp(2);
	}

}
