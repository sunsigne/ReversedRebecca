package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.ressources.FileTask;

public class DifficultyOption {

	////////// DIFFICULTY OPTION ////////////

	String file = new FileTask().getUserDataPath() + "options.csv";

	private static GAME_DIFFICULTY difficulty;

	public static GAME_DIFFICULTY getDifficulty() {
		if (difficulty == null)
			difficulty = new DifficultyOption().getRegisteredDifficulty();
		return difficulty;
	}

	// WARNING ! Do not register difficulty ! Only apply it for the current thread
	public static void setDifficulty(GAME_DIFFICULTY difficulty) {
		DifficultyOption.difficulty = difficulty;
	}

	private GAME_DIFFICULTY getRegisteredDifficulty() {
		String registeredDifficulty = new FileTask().read(getValueToRead(), file);
		for (GAME_DIFFICULTY tempDifficulty : GAME_DIFFICULTY.values()) {
			if (tempDifficulty.getName().equalsIgnoreCase(registeredDifficulty))
				return tempDifficulty;
		}

		// should not occurs, exept if someone messed up with options.csv
		return GAME_DIFFICULTY.NORMAL;
	}

	private static String getValueToRead() {
		return "Difficulty";
	}

	public void registerDifficulty(GAME_DIFFICULTY gameDifficulty) {
		new FileTask().write(getValueToRead(), file, gameDifficulty.getName());
		difficulty = null;
	}

	////////// GAME DIFFICULTY ////////////

	public enum GAME_DIFFICULTY {
		EASY("easy"), NORMAL("normal"), HARD("hard");

		GAME_DIFFICULTY(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}

		public GAME_DIFFICULTY getPrevious() {
			switch (name) {
			case "easy":
				return GAME_DIFFICULTY.HARD;
			case "normal":
				return GAME_DIFFICULTY.EASY;
			case "hard":
				return GAME_DIFFICULTY.NORMAL;
			}
			return null;
		}

		public GAME_DIFFICULTY getNext() {
			switch (name) {
			case "easy":
				return GAME_DIFFICULTY.NORMAL;
			case "normal":
				return GAME_DIFFICULTY.HARD;
			case "hard":
				return GAME_DIFFICULTY.EASY;
			}
			return null;
		}
	}

}
