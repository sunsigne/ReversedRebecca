package com.sunsigne.reversedrebecca.puzzle.chest;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.characteristics.Facing.DIRECTION;
import com.sunsigne.reversedrebecca.object.puzzle.chest.ChestCard;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TextDecoration;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.font.FontTask;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.Window;
import com.sunsigne.reversedrebecca.system.controllers.ControllerManager;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadController;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.GamepadEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.MousePreseting;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;

public class ChestPuzzle extends Puzzle implements GamepadEvent, MousePreseting {

	private boolean userData = false;

	public ChestPuzzle(String lootFile, GenericListener actionOnWinning) {
		super(null, actionOnWinning);

		this.lootFile = lootFile;
		loadSize(); // 1, 2 or 3;
		this.chestCard = new ChestCard[size];

		loadGamepadSetup();
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "chest";
	}

	////////// FACTORY ////////////

	@Override
	public PuzzleFactory getFactory() {
		return new ChestPuzzleFactory();
	}

	////////// LOOTFILE ////////////

	private String lootFile;
	private int size;

	public String getLootFile() {
		return lootFile;
	}

	private void loadSize() {
		String content = new FileTask().read(userData, lootFile).toUpperCase();

		if (content.contains("LOOT1"))
			size = 1;
		if (content.contains("LOOT2"))
			size = 2;
		if (content.contains("LOOT3"))
			size = 3;
	}

	////////// PUZZLE ////////////

	private ChestCard[] chestCard;

	@Override
	public void createPuzzle() {

		// check if file exist
		if (new FileTask().doesExist(userData, lootFile) == false)
			stopApp();

		createChestCards();
	}

	private void stopApp() {
		new SoundTask().playSound(SOUNDTYPE.ERROR, "error");
		String wrong_method = lootFile.contains("method") ? "" : " is missing";
		JOptionPane.showMessageDialog(null, "An error has occurred : " + lootFile.concat(wrong_method));
		new Conductor().stopApp();
	}

	private void createChestCards() {

		int gap = 0;
		switch (size) {
		case 1:
			createCard(0, Window.WIDHT / 2);
			break;
		case 2:
			gap = 375;
			createCard(0, Window.WIDHT / 2 - gap);
			createCard(1, Window.WIDHT / 2 + gap);
			break;
		case 3:
			gap = 550;
			createCard(0, Window.WIDHT / 2 - gap);
			createCard(1, Window.WIDHT / 2);
			createCard(2, Window.WIDHT / 2 + gap);
			break;
		}
	}

	private void createCard(int num, int x) {
		try {
			boolean searching = true;
			int index = 0;

			do {
				String lootDatas = new FileTask().read(userData, "LOOT" + (num + 1), lootFile);
				String lootData = lootDatas.contains("/") ? lootDatas.split("/")[index] : lootDatas;
				chestCard[num] = new ChestCard(this, lootData, x, 0);

				index++;
				if (chestCard[num].isValid())
					searching = false;
				else if (lootDatas.contains("/") == false)
					throw new ArrayIndexOutOfBoundsException();

			} while (searching);

		} catch (ArrayIndexOutOfBoundsException e) {
			chestCard[num] = new ChestCard(this, null, x, 0);
		}

		chestCard[num].setX(chestCard[num].getX() - chestCard[num].getWidth() / 2);
		chestCard[num].setY(-chestCard[num].getHeight());

		LAYER.PUZZLE.addObject(chestCard[num]);
	}

	////////// OPEN ////////////

	@Override
	protected BufferedImage getWallTexture() {
		return null;
	}

	@Override
	protected void createWallBorder() {
	}

	////////// TEXTURE ////////////

	@Override
	public int getSheetColCriterion() {
		return -1;
	}

	////////// CLOSE ////////////

	private boolean closing;
	private final int CLOSING_TIME = 90;
	private int time = CLOSING_TIME;

	public boolean isClosing() {
		return closing;
	}

	private void closeWithDelay() {
		time--;

		if (time == 25)
			for (int index = 0; index < size; index++)
				chestCard[index].setLeaving(true);

		if (time <= 0)
			closePuzzle(true);
	}

	////////// TICK ////////////

	@Override
	public void tick() {

		// close puzzle when looted
		if (closing == true) {
			closeWithDelay();
			return;
		}

		// one loot case
		if (size == 1) {
			if (chestCard[0] != null && chestCard[0].isPickedUp())
				closing = true;
			return;
		}

		// two or three loots case
		boolean flag = false;

		for (int index = 0; index < size; index++) {
			if (chestCard[index] != null && chestCard[index].isPickedUp()) {

				// normal or hard mode case
				if (DifficultyOption.getDifficulty() != GAME_DIFFICULTY.EASY) {
					closing = true;
					return;
				}

				// easy mode allow a second loot
				if (flag) {
					closing = true;
					return;
				}

				else
					flag = true;
			}
		}
	}

	////////// RENDER ////////////

	private int text_time = 20;

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);

		if (text_time > 0)
			text_time--;
		else
			displayText(g);
	}

	private String text;

	private String getText() {
		if (text == null)
			text = new Translatable().getTranslatedText("REWARD" + getNumberOfRewards() + getMaxAmountOfRewards(),
					FilePath.TECHTREE);
		return text;
	}

	private int getNumberOfRewards() {
		// normal of hard difficulty
		if (DifficultyOption.getDifficulty() != GAME_DIFFICULTY.EASY)
			return 1;

		// one loot case
		else if (size == 1)
			return 1;

		// easy difficulty
		else
			return 2;
	}

	private String getMaxAmountOfRewards() {
		if (size == getNumberOfRewards())
			return "ONLY";
		else
			return "";
	}

	private Font font = new FontTask().createNewFont("AGENCYB.ttf", 56f);

	private void displayText(Graphics g) {
		int[] rect = new int[] { 0, 0, Window.WIDHT, Window.HEIGHT / 4 };
		new TextDecoration().drawOutlinesString(g, font, getText(), DIRECTION.NULL, rect);
	}

	////////// PRESET MOUSE POS ////////////

	private static final int GAP = 0;

	public static final PresetMousePos LEFTMAX = new PresetMousePos(Window.WIDHT / 2 + GAP - 550,
			Window.HEIGHT / 2 + GAP);
	public static final PresetMousePos LEFT = new PresetMousePos(Window.WIDHT / 2 + GAP - 375, Window.HEIGHT / 2 + GAP);
	public static final PresetMousePos MIDDLE = new PresetMousePos(Window.WIDHT / 2 + GAP, Window.HEIGHT / 2 + GAP);
	public static final PresetMousePos RIGHT = new PresetMousePos(Window.WIDHT / 2 + GAP + 375,
			Window.HEIGHT / 2 + GAP);
	public static final PresetMousePos RIGHTMAX = new PresetMousePos(Window.WIDHT / 2 + GAP + 550,
			Window.HEIGHT / 2 + GAP);

	private PresetMousePos preset;

	@Override
	public PresetMousePos getPreset() {
		return preset;
	}

	@Override
	public PresetMousePos getDefaultPreset() {
		return size == 2 ? LEFT : MIDDLE;
	}

	@Override
	public void setPreset(PresetMousePos preset) {
		this.setPreset(preset, true);
	}

	public void setPreset(PresetMousePos preset, boolean sound) {
		this.preset = preset;
		preset.moveMouse();

		if (isPresetNull() == false && sound)
			new SoundTask().playSound(SOUNDTYPE.SOUND, "gamepad");
	}

	protected void loadGamepadSetup() {
		if (ControllerManager.getInstance().isUsingGamepad())
			setPreset(getDefaultPreset(), false);
	}

	////////// GAMEPAD ////////////

	private GamepadController gamepadController = new GamepadController(this);

	@Override
	public GamepadController getGamepadController() {
		return gamepadController;
	}

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (isClosing())
			return;

		if (isPresetNull())
			setPreset(getDefaultPreset());

		if (size == 1)
			return;

		else if (getPreset() == LEFT) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(RIGHT);
		} else if (getPreset() == RIGHT) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(LEFT);
		}

		else if (getPreset() == LEFTMAX) {
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(MIDDLE);
		} else if (getPreset() == MIDDLE) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(LEFTMAX);
			if (e.getKey() == ButtonEvent.RIGHT)
				setPreset(RIGHTMAX);
		} else if (getPreset() == RIGHTMAX) {
			if (e.getKey() == ButtonEvent.LEFT)
				setPreset(MIDDLE);
		}
	}

	@Override
	public void buttonReleased(ButtonEvent e) {

	}

}
