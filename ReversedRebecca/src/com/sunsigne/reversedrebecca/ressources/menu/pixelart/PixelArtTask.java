package com.sunsigne.reversedrebecca.ressources.menu.pixelart;

import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableTask;
import com.sunsigne.reversedrebecca.ressources.menu.Unlockables;

public class PixelArtTask implements UnlockableTask {

	@Override
	public void create(String name) {
		String path = "bonus/pixelarts/" + name + "/";
		int location = Integer.parseInt(new FileTask().read(false, path + "location.txt"));
		DIRECTION facing = getFacing(new FileTask().read(false, path + "facing.txt"));
		BufferedImage image = new ImageTask().loadImage(path + "texture");
		BufferedImage image_locked = new ImageTask().loadImage(path + "texture" + "_locked");

		PixelArt pixelArt = new PixelArt(name, location, facing, image, image_locked);
		var list = UnlockableList.getPixelArtList();
		list.addObject(pixelArt);
		list.getList().sort(Comparator.comparing(PixelArt::getLocation));

		boolean unlocked_by_default = Boolean.parseBoolean(new FileTask().read(false, path + "unlocked.txt"));
		if (unlocked_by_default)
			pixelArt.unlocked();
	}

	private DIRECTION getFacing(String facing) {
		for (DIRECTION tempFacing : DIRECTION.values()) {
			if (tempFacing.getName().equalsIgnoreCase(facing))
				return tempFacing;
		}

		return DIRECTION.LEFT;
	}

	@Override
	public void unlock(String name) {
		var list = UnlockableList.getPixelArtList();
		list.getList().forEach(tempPixelArt -> {
			if (tempPixelArt.getName().equalsIgnoreCase(name)) {
				if (tempPixelArt.isLocked() == false)
					return;

				tempPixelArt.unlocked();
				System.out.println("PIXEL ART : " + tempPixelArt.getName().toUpperCase());
			}
		});
	}

	@Override
	public void reset() {
		new FileTask().write(PixelArt.file, "");
		UnlockableList.getPixelArtList().clear();
		new Unlockables().loadRessources();
	}

}
