package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.Infos;

public class ImageList {

	////////// MAP OR LIST ////////////

	private static HashMap<String, BufferedImage> map = new HashMap<>();

	public static BufferedImage getImageFromPath(String path) {
		return map.get(path);
	}

	public static void registerImage(String path, BufferedImage image) {
		if (Infos.IS_DEV_VERSION == false)
			map.put(path, image);
	}

}
