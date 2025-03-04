package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;

public class Animation {

	private GameList<BufferedImage> list = new GameList<>(LISTTYPE.ARRAY);

	public Animation(BufferedImage sheet) {
		this(sheet, 16, 16);
	}

	public Animation(BufferedImage sheet, int width, int height) {
		boolean hasNextImage = true;
		int col = 0;
		int h = sheet.getHeight();
		int w = height == h ? width : h;
		
		while (hasNextImage) {
			try {
				col++;
				BufferedImage img = sheet.getSubimage((col * w) - w, 0, w, h);
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
