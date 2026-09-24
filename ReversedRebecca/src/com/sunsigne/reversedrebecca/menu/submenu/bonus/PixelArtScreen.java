package com.sunsigne.reversedrebecca.menu.submenu.bonus;

import java.awt.Graphics;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.submenu.BonusScreen;
import com.sunsigne.reversedrebecca.menu.submenu.SubMenuScreen;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.menu.AchievementObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.menu.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.achievement.Achievement;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class PixelArtScreen extends SubMenuScreen {

	public PixelArtScreen() {
		this(BACK, 0);
	}

	protected PixelArtScreen(PresetMousePos defaultPreset, int listStart) {
		super(defaultPreset);
		this.listStart = listStart;

		loadText();
		loadPixelArt();

		customBackButton();
		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "pixelart";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new BonusScreen(BACK);
	}

	////////// TEXT ////////////

	private void loadText() {
		TitleScreenText pixelArt = new TitleScreenText(translate("PixelArtButton"), Window.WIDHT / 2 - 208, 22);
		LAYER.MENU.addObject(pixelArt);
	}

	////////// ACHIEVEMENTS ////////////

	protected int listStart;

	private int getListEnd() {
		return listStart + Math.min(10, AchievementList.getList().getList().size() - listStart);
	}

	private void loadPixelArt() {
		/*
		var list = AchievementList.getList();
		boolean newCol = false;
		int y = 160;

		for (int index = listStart; index < getListEnd(); index++) {
			int x = newCol ? Size.XS + Window.WIDHT / 2 : Size.L;

			Achievement achievement = list.getList().get(index);
			AchievementObject achievement_object = new AchievementObject(list.getList().get(index), x, y);
			LAYER.MENU.addObject(achievement_object);

			if (achievement.getName().equalsIgnoreCase("clickHere") && achievement.isUnlocked() == false)
				createClickHereButton(achievement_object);

			y = y + 155;

			if (y > 900) {
				y = 160;
				newCol = true;
			}
		}*/
	}

	////////// BUTTONS ////////////

	private void customBackButton() {
		getBackButton().setRectsize(RECTSIZE.CUSTOM_BACK_BUTTON);
	}

	private void createArrowButton(String text, DIRECTION direction, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, Window.WIDHT / 2 + x - 75, 980, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		if (listStart == 0)
			return;

		GenericListener onPress = () -> showPreviousAchievements();
		createArrowButton("<", direction, -210, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		if (getListEnd() >= AchievementList.getList().getList().size())
			return;

		GenericListener onPress = () -> showNextAchievements();
		createArrowButton(">", direction, 210 + 75, onPress);
	}

	////////// BUTTON ACTION ////////////

	private int step = 10;

	private void showNextAchievements() {
		new PixelArtScreen(BACK, listStart + step);
	}

	private void showPreviousAchievements() {
		new PixelArtScreen(BACK, listStart - step);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			if (listStart != 0) {
				var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
				new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
				showPreviousAchievements();
			}
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getListEnd() < AchievementList.getList().getList().size()) {
				var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
				new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
				showNextAchievements();
			}
		}

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
