package com.sunsigne.reversedrebecca.ressources.achievement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FilePath;

public class Achievements {

	public void loadRessources() {
		File file = new File(FilePath.RESSOURCES_PATH + "achievements");
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		var task = new AchievementTask();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false) // check if folder or file
				task.createAchivement(tempfile);
		});
	}

}
