package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;

public class DifficultyScreen extends SubMenuScreen {

	public DifficultyScreen(GenericListener startWorld) {
		super();
		new DifficultyOption().registerDifficulty(GAME_DIFFICULTY.NORMAL);
		
		loadText();

		createPlayButton(startWorld);
		createLeftArrowButton();
		createRightArrowButton();
	}

	////////// NAME ////////////

	public String getName() {
		return "difficulty";
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new TitleScreen();
	}

	////////// TEXT ////////////

	private TitleScreenText difficulty;
	private TitleScreenText[] difficultyDetail;

	private void loadText() {
		String text = null;
		int x = 325 + 416;
		int y = 503;

		// easy / normal / hard
		String difficultyName = DifficultyOption.getDifficulty().getName();
		text = new Translatable().getTranslatedText("Difficulty" + difficultyName, file);
		difficulty = new TitleScreenText(text, x, y + 51);
		LAYER.MENU.addObject(difficulty);

		difficultyDetail = new TitleScreenText[2];

		// puzzle are ...
		text = new Translatable().getTranslatedText(difficultyName + "Detail" + 1, file);
		difficultyDetail[0] = new TitleScreenText(text, x, y + 141);
		difficultyDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(difficultyDetail[0]);

		// ... and XP boost is ...
		text = new Translatable().getTranslatedText(difficultyName + "Detail" + 2, file);
		difficultyDetail[1] = new TitleScreenText(text, x, y + 176);
		difficultyDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(difficultyDetail[1]);
	}

	////////// BUTTONS ////////////

	private void createPlayButton(GenericListener startWorld) {
		int x = 325 + 416;
		int y = 503;

		String text = new Translatable().getTranslatedText("PlayButton", file);
		ButtonObject button = new TitleScreenButton(text, x, y + 259, 415, 80, startWorld, null);
		LAYER.MENU.addObject(button);
	}

	private void createArrowButton(String text, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 566, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton() {
		GenericListener onPress = () -> choosePreviousGameDifficulty();
		createArrowButton("<", 0, onPress);
	}

	private void createRightArrowButton() {
		GenericListener onPress = () -> chooseNextGameDifficulty();
		createArrowButton(">", 420, onPress);
	}

	////////// BUTTON ACTION ////////////

	private void choosePreviousGameDifficulty() {
		GAME_DIFFICULTY game_difficulty = DifficultyOption.getDifficulty().getPrevious();
		new DifficultyOption().registerDifficulty(game_difficulty);
		refresh();
	}

	private void chooseNextGameDifficulty() {
		GAME_DIFFICULTY game_difficulty = DifficultyOption.getDifficulty().getNext();
		new DifficultyOption().registerDifficulty(game_difficulty);
		refresh();
	}

	private void refresh() {
		String difficultyName = DifficultyOption.getDifficulty().getName();
		String text = null;

		text = new Translatable().getTranslatedText("Difficulty" + difficultyName, file);
		difficulty.setText(text);

		text = new Translatable().getTranslatedText(difficultyName + "Detail" + 1, file);
		difficultyDetail[0].setText(text);

		text = new Translatable().getTranslatedText(difficultyName + "Detail" + 2, file);
		difficultyDetail[1].setText(text);
	}

}
