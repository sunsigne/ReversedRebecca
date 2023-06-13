package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public abstract class MenuIngameSubMenuScreen extends MenuIngameScreen {

	public MenuIngameSubMenuScreen(PresetMousePos defaultPreset) {
		super(defaultPreset);
		createBackButton();
	}

	protected static final int y_gap = -160;
	
	////////// SUB MENU ////////////

	protected abstract MenuIngameScreen getPreviousMenu();

	////////// BUTTONS ////////////

	private TitleScreenButton backButton;

	protected String getBackButtonText() {
		return translate("BackButton");
	}

	public TitleScreenButton getBackButton() {
		return backButton;
	}

	private void createBackButton() {
		GenericListener onPress = () -> getPreviousMenu();
		ButtonObject button = new TitleScreenButton(getBackButtonText(), 740, 950, 415, 80, onPress, null) {

			@Override
			public String getSound() {
				if (getBackButtonText().equalsIgnoreCase(translate("ApplyButton")))
					return "button_validate";
				else
					return "button_back";
			}
			
			private BufferedImage image;

			public BufferedImage getImage() {
				if (image == null)
					image = new ImageTask().loadImage("textures/menu/" + "back");
				return image;
			}
			
			@Override
			public void render(Graphics g) {
				g.drawImage(getImage(), 289, 758, 1324, 474, null);				
				super.render(g);
			}
			
		};

		button.setFacing(DIRECTION.NULL);
		((TitleScreenButton) button).setFontSize(45f);
		((TitleScreenButton) button).setRectsize(RECTSIZE.X_SMALL);
		LAYER.MENU.getHandler().addObject(button);
		backButton = (TitleScreenButton) button;
		buttons.put(BACK, button);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos BACK = new PresetMousePos(925, 1000);

}
