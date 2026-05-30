package com.sunsigne.reversedrebecca.characteristics.tools;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDInventory;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.hud.InventoryOption;
import com.sunsigne.reversedrebecca.object.hud.InventoryOption.INVENTORY_TYPE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
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

	public static BufferedImage getHighlight() {
		return image_highlight;
	}

	public void removeItem(int index) {
		if (getSize() > index)
			InventoryList.list.getList().remove(index);
	}

	public void reset() {
		new InventoryList().reset();
	}

	////////// TEXTURE ////////////

	private static BufferedImage image_highlight;

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

		sheet = new ImageTask().loadImage("textures/hud/" + "inventory_highlight");
		image_highlight = getSheetSubImage(sheet, col, getSheetRowCriterion(), getSheetWidth() + 2,
				getSheetHeight() + 2);

		InventoryList.list.addObject(image);

		for (HUD tempHUD : HUDList.getList().getList()) {
			if (tempHUD instanceof HUDInventory == false)
				continue;

			HUDInventory tempInventory = (HUDInventory) tempHUD;
			tempInventory.setBlinking();

			if (col == 1)
				tempInventory.setGenericClue(true);
		}

		if (col != 1 && InventoryOption.getType() == INVENTORY_TYPE.VISIBLE)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "loot_spawn");
	}

}