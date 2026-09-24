package com.sunsigne.reversedrebecca.ressources.menu.achievement;

import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.sunsigne.reversedrebecca.object.menu.AchievementObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableTask;
import com.sunsigne.reversedrebecca.ressources.menu.Unlockables;

public class AchievementTask implements UnlockableTask {

	@Override
	public void create(String name) {
		String path = "achievements/" + name + "/";
		int location = Integer.parseInt(new FileTask().read(false, path + "location.txt"));
		boolean hidden = Boolean.parseBoolean(new FileTask().read(false, path + "hidden.txt"));
		BufferedImage image = new ImageTask().loadImage(path + "banner");
		BufferedImage image_locked = new ImageTask().loadImage(path + "banner" + "_locked");

		Achievement achievement = new Achievement(name, location, hidden, image, image_locked);
		var list = UnlockableList.getAchievementList();
		list.addObject(achievement);
		list.getList().sort(Comparator.comparing(Achievement::getLocation));
	}

	@Override
	public void unlock(String name) {
		unlockAchievement(name, false);
	}

	public void unlockAchievement(String name, boolean reversed) {
		var list = UnlockableList.getAchievementList();
		list.getList().forEach(tempAchievement -> {
			if (tempAchievement.getName().equalsIgnoreCase(name)) {
				if (tempAchievement.isUnlocked())
					return;

				// register as unlocked
				tempAchievement.unlocked();
				System.out.println("ACHIEVEMENT : " + tempAchievement.getName().toUpperCase());

				// do the "popup" effect
				AchievementObject object = new AchievementObject(tempAchievement, reversed);
				LAYER.DEBUG.addObject(object);
				object.popup();
				return;
			}
		});
	}

	@Override
	public void reset() {
		new FileTask().write(Achievement.file, "");
		UnlockableList.getAchievementList().clear();
		new Unlockables().loadRessources();
	}

}
