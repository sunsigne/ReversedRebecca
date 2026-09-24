package com.sunsigne.reversedrebecca.ressources.menu.pixelart;

import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableTask;
import com.sunsigne.reversedrebecca.ressources.menu.Unlockables;

public class PixelArtTask implements UnlockableTask {

	@Override
	public void create(String name) {
		/*
		String path = "bonus/pixelart/" + name + "/";
		int location = Integer.parseInt(new FileTask().read(false, path + "location.txt"));
		boolean hidden = Boolean.parseBoolean(new FileTask().read(false, path + "hidden.txt"));
		BufferedImage image = new ImageTask().loadImage(path + "banner");
		BufferedImage image_locked = new ImageTask().loadImage(path + "banner" + "_locked");

		PixelArt pixelArt = new PixelArt(name, location, hidden, image, image_locked);
		var list = UnlockableList.getPixelArtList();
		list.addObject(pixelArt);
		list.getList().sort(Comparator.comparing(PixelArt::getLocation));
		*/
	}

	@Override
	public void unlock(String name) {
		var list = UnlockableList.getPixelArtList();
		list.getList().forEach(tempPixelArt -> {
			if (tempPixelArt.getName().equalsIgnoreCase(name)) {
				if (tempPixelArt.isUnlocked())
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
