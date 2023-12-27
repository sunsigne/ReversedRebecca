package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.system.Size;

public class ImageTask {

	public BufferedImage loadImage(String path) {
		return loadImage(path, false);
	}

	// use this very function only if you know what you're doing
	public BufferedImage loadImage(String path, boolean nullIfError) {

		BufferedImage image = null;
		String path0 = "/" + FilePath.RESSOURCES_PATH + path + ".png";

		image = ImageList.getImageFromPath(path0);
		if (image != null)
			return image;

		try {
			URL url = new File((new File(FilePath.LOC.toURI())).getParent() + path0).toURI().toURL();
			image = ImageIO.read(url);
		} catch (Exception e) {
			if (nullIfError)
				return null;
			e.printStackTrace();
			image = drawMissingTexture();
		}

		ImageList.registerImage(path0, image);
		return image;
	}

	public BufferedImage drawMissingTexture() {
		return drawMissingTexture(Size.XS, Size.XS);
	}

	public BufferedImage drawMissingTexture(int width, int height) {

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();

		g2d.setColor(Color.magenta);
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.black);
		g2d.fillRect(width / 2, 0, width / 2, height / 2);
		g2d.fillRect(0, height / 2, width / 2, height / 2);
		g2d.dispose();

		return img;
	}

	public BufferedImage drawCopyOf(BufferedImage image) {
		BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = copy.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return copy;
	}

}
