package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class FlagLangageButton extends TitleScreenButton {

	public FlagLangageButton(GenericListener onPress, GenericListener onRelease) {
		super("FLAG", Window.WIDHT - Size.L - 10, 10, Size.L, Size.M, onPress, onRelease);
		rectsize = RECTSIZE.LARGE;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	public BufferedImage getImage() {
		if (image == null)
			image = new Translatable().getTranslatedFlagImage();
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

		int pix = Size.XS / 4;
		g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

		if (isSelected()) {
			if (ControllerManager.getInstance().isUsingGamepad())
				new RectDecoration().drawRoundRect(g, getRect(), rectsize);
			
			Color light = new Color(192, 192, 192, 80);
			g.setColor(light);
			g.fillRect(getX() + pix, getY() + pix, getWidth() - 2 * pix, getHeight() - 2 * pix);			
		}
	}

}
