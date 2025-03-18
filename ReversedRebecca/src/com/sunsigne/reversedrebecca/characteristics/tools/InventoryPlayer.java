package com.sunsigne.reversedrebecca.characteristics.tools;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDInventory;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;

public class InventoryPlayer implements SheetableImage, PhysicFree, TickFree, RenderFree {

	////////// MAP OR LIST ////////////

	public static int getSize() {
		return InventoryList.list.getList().size();
	}

	public static BufferedImage get(int index) {
		return InventoryList.list.getList().get(index);
	}

	public static BufferedImage getHighlight(BufferedImage image) {
		return InventoryList.map.get(image);
	}

	public void removeItem(int index) {
		if (getSize() > index)
			InventoryList.list.getList().remove(index);
	}

	public void reset() {
		new InventoryList().reset();
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	public void addItem(int col) {
		BufferedImage sheet = new ImageTask().loadImage("textures/hud/" + "inventory");
		BufferedImage image = getSheetSubImage(sheet, col);

		BufferedImage sheet_highlight = new ImageTask().loadImage("textures/hud/" + "inventory_highlight");
		BufferedImage image_highlight = getSheetSubImage(sheet_highlight, col, getSheetRowCriterion(), getSheetWidth() + 2, getSheetHeight() + 2);

		InventoryList.list.addObject(image);
		InventoryList.map.put(image, image_highlight);

		for (HUD tempHUD : HUDList.getList().getList()) {
			if (tempHUD instanceof HUDInventory)
				((HUDInventory) tempHUD).setBlinking();
		}
	}

}