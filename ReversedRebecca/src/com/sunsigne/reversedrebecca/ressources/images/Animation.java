package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class Animation {

	private GameList<BufferedImage> list = new GameList<>(LISTTYPE.ARRAY);

	public Animation(BufferedImage image) {
		this(image, 16, 16);
	}

	public Animation(BufferedImage image, int width, int height) {
		boolean hasNextImage = true;
		int col = 0;

		while (hasNextImage) {
			try {
				col++;
				BufferedImage img = image.getSubimage((col * width) - width, 0, width, height);
				list.addObject(img);
			} catch (RasterFormatException e) {
				hasNextImage = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImage[] getImages() {
		BufferedImage[] images = new BufferedImage[list.getList().size()];
		return list.getList().toArray(images);
	}

}
