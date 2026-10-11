package com.sunsigne.reversedrebecca.object.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.menu.UnlockableList;
import com.sunsigne.reversedrebecca.ressources.menu.drawing.Drawing;

public class DrawingButtonObject extends TitleScreenButton {

	public DrawingButtonObject(String text, int x, int y, int x0, int y0) {
		super(new Translatable().getTranslatedText(text, FilePath.DRAWING).toUpperCase(), x, y, 415, 73, null, null);
		rectsize = RECTSIZE.CUSTOM_DRAWING_BUTTON;
		
		this.drawing = UnlockableList.getDrawingMap().get(text);
		this.name = text;
		this.x0 = x0;
		this.y0 = y0;
	}

	public DrawingButtonObject(String text, int x, int y) {
		this(text, x, y, 0, 0);
	}

	////////// TEXT ////////////

	private Font font;
	private String text;

	private String getLockedText() {
		if (text == null) {
			font = new FontTask().createNewFont("startedbyamouse.ttf", 85f);
			text = new Translatable().getTranslatedText(name + "_text", FilePath.DRAWING);
		}
		return text;
	}

	////////// DRAWING ////////////

	private String name;
	private Drawing drawing;

	public boolean isLocked() {
		return drawing != null && drawing.isLocked();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("bonus/drawings/" + name);
		return image;
	}

	@Override
	public Color getTextColor() {
		if (isLocked())
			return new Color(102, 102, 102);
		else
			return super.getTextColor();
	}

	@Override
	public Color getShadowColor() {
		if (isLocked())
			return new Color(63, 63, 63, 180);
		else
			return super.getShadowColor();
	}

	////////// RENDER ////////////

	private int x0, y0;

	@Override
	public void render(Graphics g) {
		super.render(g);

		if (isSelected() == false)
			return;

		if (isLocked()) {
			drawText(g);
			return;
		}

		var img = getImage();
		float factor = 3f / 7f;
		int w = (int) ((float) (img.getWidth()) * factor);
		int h = (int) ((float) (img.getHeight()) * factor);
		boolean horizontal = w > h;
		int xGap = horizontal ? 100 : 200;
		int yGap = horizontal ? 120 : 0;

		g.drawImage(img, x0 + 900 + xGap, y0 + 150 + yGap, w, h, null);
	}

	private void drawText(Graphics g) {
		if (getLockedText().contains("@") == false) {
			drawLine(g, getLockedText(), 0);
			return;
		}

		String[] content = getLockedText().split("@");
		for (int index = 0; index < content.length; index++) {
			drawLine(g, content[index], 120 * index);
		}
	}

	private void drawLine(Graphics g, String text, int h) {
		int[] rect = new int[] { 735, 290, 800, h };
		new TextDecoration().drawCenteredString(g, font, text, Color.BLACK, DIRECTION.NULL, rect);
	}

	////////// MOUSE ////////////

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
