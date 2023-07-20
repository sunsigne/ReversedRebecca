package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;

public class SteamButton extends TitleScreenButton {

	public SteamButton(GenericListener onPress, GenericListener onRelease) {
		super("", 6, 103, 385, 90, onPress, onRelease);
		rectsize = RECTSIZE.NORMAL;
	}

	////////// SIZE ////////////

	@Override
	public int[] getRect() {
		int[] rect = new int[4];
		rect[0] = getX() + 1;
		rect[1] = getY() + 2;
		rect[2] = getWidth() - 2;
		rect[3] = getHeight() - 3;
		return rect;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/" + "steam");
		return image;
	}

	private Font font = new FontTask().createNewFont("dogicabold.ttf", 18f);

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
		int[] rect = new int[4];
		Color color = new Color(18, 57, 96);

		if (isSelected()) {
			new RectDecoration().drawRoundRect(g, getRect(), rectsize);
			rect = new int[] { getX() + 24, getY() - 3, getWidth(), getHeight() };
		} else
			rect = new int[] { getX() + 24, getY(), getWidth(), getHeight() };

		new TextDecoration().drawOutlinesString(g, font, "WISHLIST STEAM", Color.WHITE, color, DIRECTION.NULL, rect);
	}

}
