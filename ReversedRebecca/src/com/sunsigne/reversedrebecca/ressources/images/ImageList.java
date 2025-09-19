package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageList {

	////////// MAP OR LIST ////////////

	private static HashMap<String, BufferedImage> map = new HashMap<>();

	public static BufferedImage getImageFromPath(String path) {
		return map.get(path);
	}

	public static void registerImage(String path, BufferedImage image) {
		map.put(path, image);
	}

}
