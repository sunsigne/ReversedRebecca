package com.sunsigne.reversedrebecca.ressources.achievement;

import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.sunsigne.reversedrebecca.object.AchievementObject;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class AchievementTask {

	public void createAchivement(String name) {
		String path = "achievements/" + name + "/";
		int location = Integer.parseInt(new FileTask().read(false, path + "location.txt"));
		boolean hidden = Boolean.parseBoolean(new FileTask().read(false, path + "hidden.txt"));
		BufferedImage image = new ImageTask().loadImage(path + "banner");
		BufferedImage image_locked = new ImageTask().loadImage(path + "banner" + "_locked");

		Achievement achievement = new Achievement(name, location, hidden, image, image_locked);
		var list = AchievementList.getList();
		list.addObject(achievement);
		list.getList().sort(Comparator.comparing(Achievement::getLocation));
	}

	public void unlockAchievement(String name) {
		var list = AchievementList.getList();
		list.getList().forEach(tempAchievement -> {
			if (tempAchievement.getName().equalsIgnoreCase(name)) {
				if (tempAchievement.isUnlocked())
					return;

				// register as unlocked
				tempAchievement.unlocked();
				System.out.println("ACHIEVEMENT : " + tempAchievement.getName().toUpperCase());

				// do the "popup" effect
				AchievementObject object = new AchievementObject(tempAchievement);
				LAYER.DEBUG.addObject(object);
				object.popup();
				return;
			}
		});
	}

	public void resetAchievements() {
		new FileTask().write("achievements.csv", "");
	}

}
