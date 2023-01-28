package com.sunsigne.reversedrebecca.ressources.achievement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class Achievements {

	private String file = "achievements.csv";

	public void loadRessources() {
		File file = new File(FilePath.RESSOURCES_PATH + "achievements");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		var task = new AchievementTask();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
			{
				updateUserData(tempfile);
				task.createAchivement(tempfile);
			}

		});
	}

	private void updateUserData(String name) {
		String value = new FileTask().read(true, name, file);
		if (value.isEmpty() == false)
			return;

		String content = new FileTask().read(true, file);
		String new_content = content + System.getProperty("line.separator") + name + "=false";
		new FileTask().write(file, new_content);
	}

}
