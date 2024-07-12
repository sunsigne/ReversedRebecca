package com.sunsigne.reversedrebecca.ressources.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.sunsigne.reversedrebecca.ressources.FilePath;

public class Textures {

	public void loadRessources() {
		loadRessources("textures/animation");
		loadRessources("textures/characters");
		loadRessources("textures/hud");
		loadRessources("textures/other/animation");
		loadRessources("textures/other/expression");
		loadRessources("textures/puzzle");
		loadRessources("textures/puzzler");
		loadRessources("textures/techtree");
		loadRessources("textures/tools");
	}

	public void loadRessources(String path) {
		File file = new File(FilePath.RESSOURCES_PATH + path);
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".")) { // check if folder or file
				if (tempfile.contains(".png"))
					new ImageTask().loadImage(path + "/" + tempfile.split("\\.")[0]);
			} else
				loadRessources(path + "/" + tempfile);
		});
	}

}
