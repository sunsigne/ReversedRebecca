package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.object.gui.GUIList;
import com.sunsigne.reversedrebecca.object.gui.GUITools;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.FormatedString;
import com.sunsigne.reversedrebecca.piranha.condition.global.UnlockedToolCondition;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class ToolPlayer implements Difficulty {

	private String file = new FileTask().getUserDataPath() + "characteristics.csv";

	public ToolPlayer() {
		ToolList.getList().addObject(this);
		loadMaxDifficulty();
		loadStartDifficulty();
	}

	////////// USEFUL ////////////

	public ToolPlayer getTool() {
		return ToolList.getList().getObject(this);
	}

	protected abstract ToolPlayer getInstance();

	private void registerDefaultCharacteristic(String tool) {
		String content = new FileTask().read(file);
		String br = System.getProperty("line.separator");

		String maxLine = tool + "MaxLvl=" + LVL.CYAN.getName().toUpperCase();
		String startLine = tool + "StartLvl=" + LVL.NULL.getName().toUpperCase();
		
		String new_content = content + br + br + maxLine + br + startLine;
		new FileTask().write(file, new_content);
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// DIFFICULTY ////////////

	///// max /////

	private LVL max_difficulty = LVL.NULL;

	public LVL getMaxDifficulty() {
		return getTool().max_difficulty;
	}

	public void setMaxDifficulty(LVL difficulty) {
		getTool().max_difficulty = difficulty;

		if (difficulty == LVL.NULL)
			// reset case
			loadMaxDifficulty();
		else
			// normal case
			updateGUITools();
	}

	private void loadMaxDifficulty() {
		if (getMaxDifficulty() != LVL.NULL)
			return;

		String txtDifficulty = new FileTask().read(getName() + "MaxLvl", file);

		// if the file "characteristics" has no value for the tool, create one
		if (txtDifficulty.isEmpty()) {
			registerDefaultCharacteristic(new FormatedString().capitalize(getName()));
			txtDifficulty = "CYAN";
		}

		getTool().max_difficulty = LVL.valueOf(txtDifficulty);
		updateGUITools();
	}

	///// start /////

	private LVL start_difficulty;

	public LVL getStartDifficulty() {
		return getTool().start_difficulty;
	}

	public void setStartDifficulty(LVL difficulty) {
		getTool().start_difficulty = difficulty;

		if (difficulty == null)
			// reset case
			loadStartDifficulty();
		else
			// normal case
			updateGUITools();
	}

	private void loadStartDifficulty() {
		if (getStartDifficulty() != null)
			return;

		String txtDifficulty = new FileTask().read(getName() + "StartLvl", file);

		// if the file "characteristics" has no value for the tool, create one
		if (txtDifficulty.isEmpty()) {
			registerDefaultCharacteristic(new FormatedString().capitalize(getName()));
			txtDifficulty = "NULL";
		}

		getTool().start_difficulty = LVL.valueOf(txtDifficulty);
		setDifficulty(LVL.valueOf(txtDifficulty));
	}

	///// current /////

	private LVL difficulty = LVL.NULL;

	@Override
	public LVL getDifficulty() {
		return getTool().difficulty;
	}

	@Override
	public void setDifficulty(LVL difficulty) {
		if (new DifficultyComparator().isForbiddenUpgrade(getMaxDifficulty(), difficulty))
			getTool().difficulty = getMaxDifficulty();
		else
			getTool().difficulty = difficulty;

		new UnlockedToolCondition().registerValue(this, getDifficulty());
		updateGUITools();
	}

	private void updateGUITools() {
		GUITools guiTool = null;

		for (GUI tempGUI : GUIList.getList().getList()) {
			if (tempGUI instanceof GUITools == false)
				continue;

			guiTool = (GUITools) tempGUI;
			break;
		}

		if (guiTool == null)
			return;

		guiTool.loadImages();
	}

}
