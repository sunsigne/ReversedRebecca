package com.sunsigne.reversedrebecca.menu.submenu;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.AchievementObject;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.achievement.AchievementList;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.Size;
import com.sunsigne.reversedrebecca.system.Window;

public class AchievementsScreen extends SubMenuScreen {

	public AchievementsScreen() {
		this(0);
	}

	protected AchievementsScreen(int listStart) {
		super();
		this.listStart = listStart;

		loadText();
		loadAchievements();

		createResetButton();
		createLeftArrowButton();
		createRightArrowButton();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "achievements";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen();
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
	
	private void createResetButton() {
		GenericListener onPress = () -> new ResetAchievementsScreen(listStart);
		ButtonObject button = new TitleScreenButton(translate("Reset"), 1482, 940, 415, 140, onPress, null) {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		((TitleScreenButton) button).setFontSize(40f);
		LAYER.MENU.addObject(button);
	}
	
	private void createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, Window.WIDHT / 2 + x - 75, 980, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton() {
		if (listStart == 0)
			return;

		GenericListener onPress = () -> showPreviousAchivements();
		createArrowButton("<", -210, onPress);
	}

	private void createRightArrowButton() {
		if (getListEnd() >= AchievementList.getList().getList().size())
			return;

		GenericListener onPress = () -> showNextAchivements();
		createArrowButton(">", 210 + 75, onPress);
	}

	////////// BUTTON ACTION ////////////

	private void showNextAchivements() {
		new AchievementsScreen(listStart + 5);
	}

	private void showPreviousAchivements() {
		new AchievementsScreen(listStart - 5);
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), 0, 0, Window.WIDHT, Window.HEIGHT, null);
	}

}
