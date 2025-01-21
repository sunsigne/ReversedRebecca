package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.physic.PhysicLaw;
import com.sunsigne.reversedrebecca.physic.PhysicLinker;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementTask;
import com.sunsigne.reversedrebecca.ressources.images.ImageTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MouseUserEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.TickFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class ResetAchievementsScreen extends AchievementsScreen {

	public ResetAchievementsScreen(int listStart) {
		super(CONFIRM, listStart);
		disablePreviousButtons();

		loadText();

		createConfirmButton();
		createBackButton();
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new AchievementsScreen(AchievementsScreen.RESET, listStart);
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		TitleScreenText resetDetail;
		int x = 325 + 416;
		int y = 343;

		// your progress will be ...
		text = translate("ResetDetail" + "1");
		resetDetail = new TitleScreenText(text, x, y + 245);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);

		// ... permanently lost
		text = translate("ResetDetail" + "2");
		resetDetail = new TitleScreenText(text, x, y + 280);
		resetDetail.setFontSize(18f);
		LAYER.MENU.addObject(resetDetail);
	}

	////////// BUTTONS ////////////

	private void disablePreviousButtons() {
		var list = LAYER.MENU.getHandler().getList();
		for (Updatable tempUpdatable : list) {
			if (tempUpdatable instanceof MouseUserEvent)
				tempUpdatable.destroyControls();
		}

		createRender();
	}

	private void createButton(String text, PresetMousePos preset, int x, int y, GenericListener onPress, String sound) {
		ButtonObject button = new TitleScreenButton(text, 325 + x, 343 + y, 415, 80, onPress, null) {
			public String getSound() {
				return sound;
			}
		};

		LAYER.MENU.addObject(button);
		buttons.put(preset, button);
	}

	private void createConfirmButton() {
		GenericListener onPress = () -> resetAchievements();
		createButton(translate("Confirm"), CONFIRM, 416, 51, onPress, "button_validate");
	}

	private void createBackButton() {
		GenericListener onPress = () -> getPreviousMenu();
		createButton(translate("Cancel"), BACK, 416, 155, onPress, "button_back");
	}

	////////// BUTTON ACTION ////////////

	private void resetAchievements() {
		new AchievementTask().resetAchievements();
		new AchievementsScreen();
	}

	////////// TEXTURE ////////////

	private BufferedImage image;

	private BufferedImage getSubImage() {
		if (image == null)
			image = new ImageTask().loadImage("textures/menu/" + "quit", true);
		return image;
	}

	////////// RENDER ////////////

	private void createRender() {
		TickFree grayFilter = new TickFree() {

			@Override
			public PhysicLaw[] getPhysicLinker() {
				return PhysicLinker.MENU;
			}
			
			@Override
			public void render(Graphics g) {
				new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
				g.drawImage(getSubImage(), 289, 305, 1324, 474, null);
			}
		};

		LAYER.MENU.addObject(grayFilter);
	}

	////////// PRESET MOUSE POS ////////////

	public static final PresetMousePos CONFIRM = new PresetMousePos(925, 430);
	public static final PresetMousePos BACK = new PresetMousePos(925, 530);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(CONFIRM);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}
		
		else if (getPreset() == CONFIRM)
			confirmPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void confirmPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(CONFIRM).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(CONFIRM);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
