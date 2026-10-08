package com.sunsigne.reversedrebecca.object.menu;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;

public class DrawingButtonObject extends TitleScreenButton {

	public DrawingButtonObject(String text, int x, int y, int x0, int y0) {
		super(new Translatable().getTranslatedText(text, FilePath.DRAWING).toUpperCase(), x, y, 415, 73, null, null);
		this.name = text;
		this.x0 = x0;
		this.y0 = y0;
	}

	public DrawingButtonObject(String text, int x, int y) {
		this(text, x, y, 0, 0);
	}
	
	private String name;

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("bonus/drawings/" + name);
		return image;
	}

	////////// RENDER ////////////

	private int x0, y0;
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if (isSelected() == false)
			return;

		var img = getImage();
		float factor = 3f / 7f;
		int w = (int) ((float) (img.getWidth()) * factor);
		int h = (int) ((float) (img.getHeight()) * factor);
		boolean horizontal = w > h;
		int xGap = horizontal ? 100 : 200;
		int yGap = horizontal ? 120 : 0;

		g.drawImage(img, x0 + 900 + xGap, y0 + 150 + yGap, w, h, null);
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
