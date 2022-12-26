package com.sunsigne.reversedrebecca.puzzle.chest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.puzzle.chest.ChestCard;
import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.pattern.render.TransluantLayer;
import com.sunsigne.reversedrebecca.puzzle.Puzzle;
import com.sunsigne.reversedrebecca.puzzle.PuzzleFactory;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;
import com.sunsigne.reversedrebecca.system.DifficultyOption;
import com.sunsigne.reversedrebecca.system.DifficultyOption.GAME_DIFFICULTY;
import com.sunsigne.reversedrebecca.system.Window;

public class ChestPuzzle extends Puzzle {

	private boolean userData = false;
	
	public ChestPuzzle(String lootFile, GenericListener actionOnWinning) {
		super(-1, actionOnWinning);

		this.lootFile = lootFile;
		loadSize(); // 1, 2 or 3;
		this.chestCard = new ChestCard[size];
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
		String lootData = new FileTask().read(userData, "LOOT" + (num + 1), lootFile);

		chestCard[num] = new ChestCard(this, lootData, x, 0);
		chestCard[num].setX(chestCard[num].getX() - chestCard[num].getWidth() / 2);
		chestCard[num].setY(- chestCard[num].getHeight());

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
			if (chestCard[0].isPickedUp())
				closing = true;
			return;
		}

		// two or three loots case
		boolean flag = false;

		for (int index = 0; index < size; index++) {
			if (chestCard[index].isPickedUp()) {

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

	@Override
	public void render(Graphics g) {
		new TransluantLayer().drawGray(g, Window.WIDHT, Window.HEIGHT);
	}

}
