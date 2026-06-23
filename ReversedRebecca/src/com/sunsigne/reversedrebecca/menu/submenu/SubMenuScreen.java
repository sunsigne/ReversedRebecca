package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.LockedTitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public abstract class SubMenuScreen extends MenuScreen {

	public SubMenuScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		createBackButton();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// SUB MENU ////////////

	protected abstract MenuScreen getPreviousMenu();

	////////// BUTTONS ////////////

	private TitleScreenButton backButton;

	protected String getBackButtonText() {
		return translate("BackButton");
	}

	public TitleScreenButton getBackButton() {
		return backButton;
	}

	private void createBackButton() {
		createBackButton(false);
	}

	protected void createBackButton(boolean locked) {
		if (backButton != null)
			LAYER.MENU.getHandler().removeObject(backButton);

		GenericListener onPress = () -> getPreviousMenu();
		ButtonObject button = new TitleScreenButton(getBackButtonText(), 740, 940, 420, 140, onPress, null) {

			@Override
			public String getSound() {
				if (getBackButtonText().equalsIgnoreCase(translate("ApplyButton")))
					return "button_validate";
				else
					return "button_back";
			}
		};

		if (locked)
			button = new LockedTitleScreenButton(getBackButtonText(), 740, 940, 420, 140, 38, 42, onPress, null);

		button.setFacing(DIRECTION.NULL);
		((TitleScreenButton) button).setFontSize(45f);
		LAYER.MENU.getHandler().addObject(button);
		backButton = (TitleScreenButton) button;
		buttons.put(BACK, button);
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	@Override
	protected BufferedImage getImage() {
		if (image == null) {
			image = new ImageTask().loadImage("textures/menu/" + getName(), true);
			xl = getName().contains("_xl");
			xxl = getName().contains("_xxl");
		}
		return image;
	}

	////////// RENDER ////////////

	protected boolean xl;
	protected boolean xxl;

	@Override
	public void render(Graphics g) {
		super.render(g);
		int x = xxl ? 20 : 289;
		int y = xl || xxl ? 286 : 465;
		int width = xxl ? 1880 : 1324;
		int height = xl || xxl ? 600 : 474;
		g.drawImage(getImage(), x, y, width, height, null);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos BACK = new PresetMousePos(925, 1000);

}
