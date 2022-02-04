package com.sunsigne.reversedrebecca.ressources.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sunsigne.reversedrebecca.Infos;

public class ImageTask {

	public BufferedImage loadImage(String path) {
		return loadImage(path, null, true);
	}

	public BufferedImage loadImage(String path, boolean showError) {
		return loadImage(path, null, showError);
	}

	public BufferedImage loadImage(String path, String backupPath, boolean showError) {

		BufferedImage image = null;

		if (path == null)
			return drawMissingTexture();

		try {
			String path0 = "/ressources/" + path + ".png";
			path = null;
			URL url = new File((new File(Infos.LOC.toURI())).getParent() + path0).toURI().toURL();
			image = ImageIO.read(url);
		} catch (Exception e) {
			if (showError)
				e.printStackTrace();
			image = loadImage(backupPath, path, showError);
		}

		return image;
	}

	public BufferedImage drawMissingTexture() {
		return drawMissingTexture(32, 32);
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

}
