package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class ToolPlayer implements Difficulty {

	public ToolPlayer() {
		ToolPlayerList.getList().addObject(this);
		loadDifficulty();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// DIFFICULTY ////////////

	private LVL difficulty = LVL.NULL;

	@Override
	public LVL getDifficulty() {
		return ToolPlayerList.getList().getObject(this).difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		ToolPlayerList.getList().getObject(this).difficulty = difficulty;
	}

	private void loadDifficulty() {
		if (getDifficulty() == LVL.NULL) {
			String txtDifficulty = new FileTask().read("userdata/characteristics/" + getName() + ".csv");
			setDifficulty(LVL.valueOf(txtDifficulty));
		}
	}

}
