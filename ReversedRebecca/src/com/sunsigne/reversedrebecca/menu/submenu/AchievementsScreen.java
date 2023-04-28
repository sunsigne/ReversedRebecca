package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;
import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.AchievementObject;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.RectDecoration.RECTSIZE;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class AchievementsScreen extends SubMenuScreen {

	public AchievementsScreen() {
		this(BACK, 0);
	}

	protected AchievementsScreen(PresetMousePos defaultPreset, int listStart) {
		super(defaultPreset);
		this.listStart = listStart;

		loadText();
		loadAchievements();

		customBackButton();
		createResetButton();
		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "achievements";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.ACHIEVEMENT);
	}

	////////// TEXT ////////////

	private void loadText() {
		TitleScreenText achievement = new TitleScreenText(translate("achievement"), Window.WIDHT / 2 - 208, 22);
		LAYER.MENU.addObject(achievement);
	}

	////////// ACHIEVEMENTS ////////////

	protected int listStart;

	private int getListEnd() {
		return listStart + Math.min(10, AchievementList.getList().getList().size() - listStart);
	}

	private void loadAchievements() {
		var list = AchievementList.getList();
		boolean newCol = false;
		int y = 160;

		for (int index = listStart; index < getListEnd(); index++) {
			int x = newCol ? Size.XS + Window.WIDHT / 2 : Size.L;

			AchievementObject achievement = new AchievementObject(list.getList().get(index), x, y);
			LAYER.MENU.addObject(achievement);

			y = y + 155;

			if (y > 900) {
				y = 160;
				newCol = true;
			}
		}
	}

	////////// BUTTONS ////////////

	private void customBackButton() {
		getBackButton().setRectsize(RECTSIZE.CUSTOM_BACK_BUTTON);
	}

	private void createResetButton() {
		GenericListener onPress = () -> new ResetAchievementsScreen(listStart);
		ButtonObject button = new TitleScreenButton(translate("Reset"), 1482, 940, 415, 140, onPress, null) {

			@Override
			public String getSound() {
				return "button_validate";
			}
		};

		((TitleScreenButton) button).setFontSize(40f);
		buttons.put(RESET, button);
		button.setRectsize(RECTSIZE.CUSTOM_RESET_BUTTON);
		LAYER.MENU.addObject(button);
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

		GenericListener onPress = () -> showPreviousAchivements();
		createArrowButton("<", direction, -210, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		if (getListEnd() >= AchievementList.getList().getList().size())
			return;

		GenericListener onPress = () -> showNextAchivements();
		createArrowButton(">", direction, 210 + 75, onPress);
	}

	////////// BUTTON ACTION ////////////

	private void showNextAchivements() {
		new AchievementsScreen(BACK, listStart + 5);
	}

	private void showPreviousAchivements() {
		new AchievementsScreen(BACK, listStart - 5);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);
	}

	////////// PRESET MOUSE POS ////////////

	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();

	public static final PresetMousePos RESET = new PresetMousePos(1685, 1010);

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

		else if (getPreset() == RESET)
			resetPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	private void resetPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(RESET).mousePressed(null);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			if (listStart != 0) {
				var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
				new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
				showPreviousAchivements();
			}
		}

		else if (e.getKey() == ButtonEvent.RIGHT) {
			if (getListEnd() < AchievementList.getList().getList().size()) {
				var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
				new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
				showNextAchivements();
			} else
				setPreset(RESET);
		}

		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
