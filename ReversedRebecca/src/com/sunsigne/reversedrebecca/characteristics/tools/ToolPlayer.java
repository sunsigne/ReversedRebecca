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
		ToolPlayerList.getList().addObject(this);
		loadMaxDifficulty();
		loadDifficulty();
	}

	////////// NAME ////////////

	public abstract String getName();

	////////// DIFFICULTY ////////////

	private ToolPlayer getTool() {
		return ToolPlayerList.getList().getObject(this);
	}

	///// max /////

	private LVL max_difficulty = LVL.NULL;

	public LVL getMaxDifficulty() {
		return getTool().max_difficulty;
	}

	public void setMaxDifficulty(LVL max_difficulty) {
		getTool().max_difficulty = max_difficulty;
		updateGUITools();
	}

	private void loadMaxDifficulty() {
		if (getMaxDifficulty() == LVL.NULL) {
			String txtDifficulty = new FileTask().read(getName() + "MaxLvl", file);
			if (txtDifficulty.isEmpty())
				stopApp();

			getTool().max_difficulty = LVL.valueOf(txtDifficulty);
			updateGUITools();
		}
	}

	private void stopApp() {
		new SoundTask().play(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null,
				"An existing or required tool in this level is undefined in ressources/userdata/characteristics.csv");
		new Conductor().stopApp();
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

		new UnlockedToolCondition().registerValue(getTool(), getDifficulty());
		updateGUITools();
	}

	private void loadDifficulty() {
		if (getDifficulty() == LVL.NULL) {
			String txtDifficulty = new FileTask().read(getName() + "StartLvl", file);
			setDifficulty(LVL.valueOf(txtDifficulty));
		}
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
