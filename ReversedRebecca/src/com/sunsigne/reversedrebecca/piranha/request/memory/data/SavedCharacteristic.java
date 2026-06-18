package com.sunsigne.reversedrebecca.piranha.request.memory.data;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;

public class SavedCharacteristic {

	////////// DIFFICULTY ////////////

	public SavedCharacteristic(LVL start_difficulty, LVL max_difficulty, LVL difficulty, int critical_chance) {
		this.start_difficulty = start_difficulty;
		this.max_difficulty = max_difficulty;
		this.difficulty = difficulty;
		this.critical_chance = critical_chance;
	}

	private LVL start_difficulty = LVL.NULL;

	public LVL getStartDifficulty() {
		return start_difficulty;
	}

	private LVL max_difficulty = LVL.CYAN;

	public LVL getMaxDifficulty() {
		return max_difficulty;
	}

	private LVL difficulty = LVL.NULL;

	public LVL getDifficulty() {
		return difficulty;
	}

	private int critical_chance = 10;

	public int getCriticalChance() {
		return critical_chance;
	}

	////////// UPGRADE ////////////

	public SavedCharacteristic(boolean value) {
		this.value = value;
	}

	private boolean value;

	public boolean getValue() {
		return value;
	}

	////////// HP ////////////

	public SavedCharacteristic(int maxHp, int bonusHp) {
		this.maxHp = maxHp;
		this.bonusHp = bonusHp;
	}

	private int maxHp = 2;

	public int getMaxHp() {
		return maxHp;
	}

	private int bonusHp;

	public int getBonusHp() {
		return bonusHp;
	}

}
