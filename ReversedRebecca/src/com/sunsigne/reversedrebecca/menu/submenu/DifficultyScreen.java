package com.sunsigne.reversedrebecca.menu.submenu;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.menu.TitleScreen;
import com.sunsigne.reversedrebecca.object.buttons.ButtonObject;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenButton;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenTextSelectable;
import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class DifficultyScreen extends SubMenuScreen {

	public DifficultyScreen(GenericListener startWorld) {
		super(DIFFICULTY);
		new DifficultyOption().registerDifficulty(GAME_DIFFICULTY.NORMAL);

		loadText();

		createPlayButton(startWorld);
		createLeftArrowButton(DIRECTION.LEFT);
		createRightArrowButton(DIRECTION.RIGHT);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "difficulty";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuScreen getPreviousMenu() {
		return new TitleScreen(TitleScreen.PLAY);
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
		text = translate("Difficulty" + difficultyName);
		difficulty = new TitleScreenTextSelectable(text, x, y + 51);
		LAYER.MENU.addObject(difficulty);

		difficultyDetail = new TitleScreenText[2];

		// puzzle are ...
		text = translate(difficultyName + "Detail" + "1");
		difficultyDetail[0] = new TitleScreenText(text, x, y + 141);
		difficultyDetail[0].setFontSize(18f);
		LAYER.MENU.addObject(difficultyDetail[0]);

		// ... and XP boost is ...
		text = translate(difficultyName + "Detail" + "2");
		difficultyDetail[1] = new TitleScreenText(text, x, y + 176);
		difficultyDetail[1].setFontSize(18f);
		LAYER.MENU.addObject(difficultyDetail[1]);
	}

	////////// BUTTONS ////////////

	private void createPlayButton(GenericListener startWorld) {
		int x = 325 + 416;
		int y = 503;

		ButtonObject button = new TitleScreenButton(translate("PlayButton"), x, y + 259, 415, 80, startWorld, null)  {
			
			@Override
			public String getSound() {
				return "button_validate";
			}
		};
		
		buttons.put(PLAY, button);
		LAYER.MENU.addObject(button);
	}

	private void createArrowButton(String text, DIRECTION direction, int x, GenericListener onPress) {
		ButtonObject button = new TitleScreenButton(text, 710 + x, 566, 60, 60, onPress, null);
		((TitleScreenButton) button).setFontSize(40f);
		arrow_buttons.put(direction, button);
		LAYER.MENU.addObject(button);
	}

	private void createLeftArrowButton(DIRECTION direction) {
		GenericListener onPress = () -> choosePreviousGameDifficulty();
		createArrowButton("<", direction, 0, onPress);
	}

	private void createRightArrowButton(DIRECTION direction) {
		GenericListener onPress = () -> chooseNextGameDifficulty();
		createArrowButton(">", direction, 420, onPress);
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

		text = translate("Difficulty" + difficultyName);
		difficulty.setText(text);

		text = translate(difficultyName + "Detail" + "1");
		difficultyDetail[0].setText(text);

		text = translate(difficultyName + "Detail" + "2");
		difficultyDetail[1].setText(text);
	}

	////////// PRESET MOUSE POS ////////////
	
	private HashMap<DIRECTION, ButtonObject> arrow_buttons = new HashMap<>();
	
	public static final PresetMousePos DIFFICULTY = new PresetMousePos(925, 600);
	public static final PresetMousePos PLAY = new PresetMousePos(925, 800);
	
	////////// GAMEPAD ////////////
	
	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;
	
		if (isPresetNull())
			setPreset(DIFFICULTY);
		else if (getPreset() == DIFFICULTY)
			difficultyPressed(e);
		else if (getPreset() == PLAY)
			playPressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}
	
	private void difficultyPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.LEFT) {
			var sound = arrow_buttons.get(DIRECTION.LEFT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			choosePreviousGameDifficulty();
		}
			
		else if (e.getKey() == ButtonEvent.RIGHT) {
			var sound = arrow_buttons.get(DIRECTION.RIGHT).getSound();
			new SoundTask().playSound(SOUNDTYPE.SOUND, sound);
			chooseNextGameDifficulty();
		}
		
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(PLAY);
	}

	private void playPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(DIFFICULTY);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(PLAY).mousePressed(null);
	}	
	
	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(PLAY);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}
	
}
