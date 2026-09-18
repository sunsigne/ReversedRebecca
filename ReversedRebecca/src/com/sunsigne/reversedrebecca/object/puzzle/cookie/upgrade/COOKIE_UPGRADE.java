package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public enum COOKIE_UPGRADE implements SheetableImage, PhysicFree, TickFree, RenderFree {
	COOKIE("Cookie"), CURSOR("Cursor"), GRANDPA("Grandpa"), FACTORY("Factory"), STOCK("Stock"), ANTI_G("Anti_g");

	private String upgrade;

	COOKIE_UPGRADE(String upgrade) {
		this.upgrade = upgrade;
	}

	public String getName() {
		return upgrade;
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetSize() {
		switch (upgrade) {
		case "Cookie":
			return 3 * 16;
		}
		return 2 * 16;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	@Override
	public int getSheetColCriterion() {
		switch (upgrade) {
		case "Cookie":
		case "Cursor":
			return 1;
		case "Grandpa":
			return 2;
		case "Factory":
			return 3;
		case "Stock":
			return 4;
		case "Anti_g":
			return 5;
		}
		return 0;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			String upgrade = getName().contains("Cookie") ? "" : "_upgrade";
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie" + upgrade);
			image = getSheetSubImage(sheet);
		}
		return image;
	}

}
