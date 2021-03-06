package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public abstract class SubMenuScreen extends MenuScreen {

	public SubMenuScreen() {
		super();
		createBackButton();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// SUB MENU ////////////

	protected abstract MenuScreen getPreviousMenu();

	////////// BUTTONS ////////////

	private void createBackButton() {
		GenericListener onPress = () -> getPreviousMenu();
		ButtonObject button = new TitleScreenButton(translate("BackButton"), 740, 940, 420, 140, onPress, null);
		button.setFacing(DIRECTION.NULL);
		((TitleScreenButton) button).setFontSize(45f);
		LAYER.MENU.getHandler().addObject(button);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/" + getName());
		return image;
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(getImage(), 289, 465, 1324, 474, null);
	}

}
