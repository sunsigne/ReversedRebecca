package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class CrashButton extends TitleScreenButton {

	public CrashButton(GenericListener onPress, GenericListener onRelease) {
		super("Signaler un bug", 6, 3, 385, 90, onPress, onRelease);
		rectsize = RECTSIZE.NORMAL;
	}

	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() + 1;
		rect[1] = getY() + 1;
		rect[2] = getWidth() - 2;
		rect[3] = getHeight() - 2;
		return rect;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/" + "crash");
		return image;
	}

	private Font font = new FontTask().createNewFont("dogicabold.ttf", 18f);

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		int[] rect = new int[4];

		if (isSelected()) {
			new RectDecoration().drawRoundRect(g, getRect(), rectsize);
			rect = new int[] { getX() + 26, getY() - 3, getWidth(), getHeight() };
		} else
			rect = new int[] { getX() + 26, getY(), getWidth(), getHeight() };

		new TextDecoration().drawOutlinesString(g, font, getText(), DIRECTION.NULL, rect);
	}

}
