package com.sunsigne.reversedrebecca.characteristics.tools;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.gui.GUI;
import com.sunsigne.reversedrebecca.object.gui.GUIList;
import com.sunsigne.reversedrebecca.object.gui.GUITools;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.piranha.condition.global.UnlockedToolCondition;
import com.sunsigne.reversedrebecca.ressources.FileTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;

public abstract class ToolPlayer implements Difficulty {

	private String file = "userdata/characteristics.csv";

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

	private void stopApp() {
		new SoundTask().play(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null,
				"An existing or required tool in this level is undefined in ressources/userdata/characteristics.csv");
		new Conductor().stopApp();
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
		if (txtDifficulty.isEmpty())
			stopApp();

		getTool().max_difficulty = LVL.valueOf(txtDifficulty);
		updateGUITools();
	}

	///// start /////

	private LVL start_difficulty = LVL.NULL;

	public LVL getStartDifficulty() {
		return getTool().start_difficulty;
	}

	public void setStartDifficulty(LVL difficulty) {
		getTool().start_difficulty = difficulty;

		if (difficulty == LVL.NULL)
			// reset case
			loadStartDifficulty();
		else
			// normal case
			updateGUITools();
	}

	private void loadStartDifficulty() {
		if (getStartDifficulty() != LVL.NULL)
			return;

		String txtDifficulty = new FileTask().read(getName() + "StartLvl", file);
		if (txtDifficulty.isEmpty())
			stopApp();

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
