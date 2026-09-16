package com.sunsigne.reversedrebecca.object.puzzle.cookie.upgrade;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public enum COOKIE_UPGRADE implements SheetableImage, PhysicFree, TickFree, RenderFree {
	COOKIE("cookie"), CURSOR("cursor"), GRANDPA("granpa");

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
		case "cookie":
			return 3 * 16;
		}
		return 2 * 16;
	}

	@Override
	public int getSheetColCriterion() {
		switch (upgrade) {
		case "cookie":
			return 1;
		case "cursor":
			return 1;
		case "grandpa":
			return 2;
		}
		return 0;
	}

	@Override
	public int getSheetRowCriterion() {
		switch (upgrade) {
		case "cookie":
			return 1;
		}
		return 5;
	}

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/puzzle/" + "cookie");
			image = getSheetSubImage(sheet);
		}
		return image;
	}

}
