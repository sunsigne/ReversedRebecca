package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class ToolPlayer implements Difficulty {

	public ToolPlayer() {
		ToolPlayerList.getList().addObject(this);
		loadMaxDifficulty();
		loadDifficulty();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// DIFFICULTY ////////////

	///// max /////

	private LVL max_difficulty = LVL.NULL;

	public LVL getMaxDifficulty() {
		return ToolPlayerList.getList().getObject(this).max_difficulty;
	}

	private void loadMaxDifficulty() {
		if (getMaxDifficulty() == LVL.NULL) {
			String txtDifficulty = new FileTask().read(1, "userdata/characteristics/" + getName() + ".csv");
			ToolPlayerList.getList().getObject(this).max_difficulty = LVL.valueOf(txtDifficulty);
		}
	}

	///// current /////

	private LVL difficulty = LVL.NULL;

	@Override
	public LVL getDifficulty() {
		return ToolPlayerList.getList().getObject(this).difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		if (new DifficultyComparator().isForbiddenUpgrade(getMaxDifficulty(), difficulty))
			ToolPlayerList.getList().getObject(this).difficulty = getMaxDifficulty();
		else
			ToolPlayerList.getList().getObject(this).difficulty = difficulty;
	}

	private void loadDifficulty() {
		if (getDifficulty() == LVL.NULL) {
			String txtDifficulty = new FileTask().read(2, "userdata/characteristics/" + getName() + ".csv");
			setDifficulty(LVL.valueOf(txtDifficulty));
		}
	}

}
