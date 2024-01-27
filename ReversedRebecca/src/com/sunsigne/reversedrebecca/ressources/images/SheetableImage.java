package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public interface SheetableImage extends Updatable {

	int getSheetColCriterion();

	int getSheetRowCriterion();

	default int getSheetWidth() {
		return getSheetSize();
	}

	default int getSheetHeight() {
		return getSheetSize();
	}

	default int getSheetSize() {
		return 16;
	}

	default BufferedImage getSheetSubImage(BufferedImage image) {
		return getSheetSubImage(image, getSheetColCriterion(), getSheetRowCriterion(), getSheetWidth(),
				getSheetHeight());
	}

	default BufferedImage getSheetSubImage(BufferedImage image, int col) {
		return getSheetSubImage(image, col, getSheetRowCriterion(), getSheetWidth(), getSheetHeight());
	}

	default BufferedImage getSheetSubImage(BufferedImage image, int col, int row, int width, int height) {

		BufferedImage img = null;
		try {
			img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		} catch (Exception e) {
			img = new ImageTask().drawMissingTexture(width, height);
		}

		return img;
	}

}
