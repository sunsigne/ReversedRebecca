package com.sunsigne.reversedrebecca.object.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;

public class AchievementButton extends TitleScreenButton {

	public AchievementButton(GenericListener onPress, GenericListener onRelease) {
		super("ACHIEVEMENT", Window.WIDHT - Size.L - 12, Size.L + 15, Size.L, Size.M, onPress, onRelease);
		loadImages();
		rectsize = RECTSIZE.LARGE;
	}

	////////// TEXTURE ////////////

	private BufferedImage image;
	private BufferedImage image_over;

	private void loadImages() {
		image = new ImageTask().loadImage("textures/menu/" + "medal");
		image_over = new ImageTask().loadImage("textures/menu/" + "medal" + "_over");
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		
		if (isSelected()) {
			if (ControllerManager.getInstance().isUsingGamepad())
				new RectDecoration().drawRoundRect(g, getRect(), rectsize);
			
			g.drawImage(image_over, getX(), getY() - 3, getWidth(), getHeight(), null);			
		}

		else {
			g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
		}

	}

}
