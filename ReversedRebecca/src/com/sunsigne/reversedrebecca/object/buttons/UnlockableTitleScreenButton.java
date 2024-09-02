package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.images.SheetableImage;
import com.sunsigne.reversedrebecca.system.Size;

public class UnlockableTitleScreenButton extends TitleScreenButton implements SheetableImage {

	public UnlockableTitleScreenButton(String text, int x, int y, int w, int h, GenericListener onPress,
			GenericListener onRelease) {
		super(text, x, y, w, h, onPress, onRelease);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

	}

	////////// TEXTURE ////////////

	private BufferedImage lockImage;

	@Override
	public Color getTextColor() {
		if (text_color == null)
			text_color = new Color(102, 102, 102);
		return text_color;
	}

	@Override
	public Color getShadowColor() {
		if (shadow_color == null)
			shadow_color = new Color(63, 63, 63, 180);
		return shadow_color;
	}

	@Override
	public int getSheetColCriterion() {
		return 1;
	}

	@Override
	public int getSheetRowCriterion() {
		return 1;
	}

	private BufferedImage getImage() {
		if (lockImage == null) {
			BufferedImage sheet = new ImageTask().loadImage("textures/animation/" + "door_unlock");
			lockImage = getSheetSubImage(sheet);
		}
		return lockImage;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(getImage(), getX() + 40, getY() + 10, Size.S, Size.S, null);
		g.drawImage(getImage(), getX() + 310, getY() + 10, Size.S, Size.S, null);

	}

	////////// MOUSE ////////////

	@Override
	public boolean isClickable() {
		return false;
	}

}
