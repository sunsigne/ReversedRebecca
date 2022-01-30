package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.GenericListener;
import com.sunsigne.reversedrebecca.pattern.MousePos;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.lang.Language;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class FlagLangageButton extends ButtonObject {

	public FlagLangageButton(GenericListener onPress, GenericListener onRelease) {
		super("", Window.WIDHT - Size.L, Window.HEIGHT - Size.M, Size.L, Size.M, onPress, onRelease);
		loadImage();
	}

	////////// TEXTURE ////////////

	private BufferedImage flag_img;

	private void loadImage() {
		flag_img = new ImageTask().loadImage("texts/" + Language.getInstance().getLang() + "/lang" + "/flag" + ".png");
	}

	public BufferedImage getImage() {
		return flag_img;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		int pix = 8;
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		if (mouseOver(new MousePos().get(), getRect()) && isClickable()) {
			Color light = new Color(192, 192, 192, 80);
			g.setColor(light);
			g.fillRect(getX() + pix, getY() + pix, getWidth() - 2*pix, getHeight() - 2*pix);
		}
	}


}
