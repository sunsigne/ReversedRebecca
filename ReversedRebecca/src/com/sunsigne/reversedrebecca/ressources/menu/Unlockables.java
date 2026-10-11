package com.sunsigne.reversedrebecca.ressources.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.menu.achievement.AchievementTask;
import com.sunsigne.reversedrebecca.ressources.menu.drawing.DrawingTask;
import com.sunsigne.reversedrebecca.ressources.menu.pixelart.PixelArtTask;

public class Unlockables {

	private String achievements = "achievements";
	private String drawings = "drawings";
	private String pixelarts = "pixelarts";

	public void loadRessources() {
		load(achievements, new AchievementTask(), true);
		load(drawings, new DrawingTask(), false);
		load(pixelarts, new PixelArtTask(), true);		
		new DrawingTask().unlock("dave_chair");
	}

	public void load(String unlockable, UnlockableTask task, boolean folderBased) {
		String bonus = unlockable.contentEquals(achievements) ? "" : "bonus/";

		File file = new File(FilePath.RESSOURCES_PATH + bonus + unlockable);
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == !folderBased) // check if folder or file
			{
				tempfile = tempfile.replace(".png", "");
				updateUserData(tempfile, unlockable);
				task.create(tempfile);
			}
		});

	}

	private void updateUserData(String name, String file) {
		file = file.concat(".csv");
		String value = new FileTask().read(true, name, file);
		if (value.isEmpty() == false)
			return;

		String formatted_name = name.replace(".png", "");	
		String content = new FileTask().read(true, file);
		String new_content = content + System.getProperty("line.separator") + formatted_name + "=false";
		new FileTask().write(file, new_content);
	}

}
