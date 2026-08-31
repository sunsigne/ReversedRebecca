package com.sunsigne.reversedrebecca.characteristics.tools;

import com.sunsigne.reversedrebecca.object.characteristics.Difficulty;
import com.sunsigne.reversedrebecca.object.hud.HUD;
import com.sunsigne.reversedrebecca.object.hud.HUDList;
import com.sunsigne.reversedrebecca.object.hud.HUDTools;
import com.sunsigne.reversedrebecca.pattern.DifficultyComparator;
import com.sunsigne.reversedrebecca.pattern.FormattedString;
import com.sunsigne.reversedrebecca.piranha.condition.global.UnlockedToolCondition;
import com.sunsigne.reversedrebecca.piranha.condition.global.UnlockedToolMaxLevelCondition;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public abstract class ToolPlayer implements Difficulty {

	private String file = "characteristics.csv";
	private boolean userData = true;

	public ToolPlayer() {
		ToolList.getList().addObject(this);
		loadMaxDifficulty();
		loadStartDifficulty();
		loadCriticalChance();
	}

	////////// USEFUL ////////////

	public ToolPlayer getTool() {
		return ToolList.getList().getObject(this);
	}

	protected abstract ToolPlayer getInstance();

	private void registerDefaultCharacteristic(String tool) {
		String content = new FileTask().read(userData, file);
		String br = System.getProperty("line.separator");

		String maxLine = tool + "MaxLvl=" + getDefaultMaxDifficulty();
		String startLine = tool + "StartLvl=" + getDefaultStartDifficulty();
		String criticalLine = tool + "CriticalChance=" + getDefaultCriticalChance() + "%";

		String new_content = content + br + br + maxLine + br + startLine + br + criticalLine;
		new FileTask().write(file, new_content);
	}

	////////// NAME ////////////

	public abstract int getNum();

	public abstract String getName();

	////////// DIFFICULTY ////////////

	///// max /////

	private LVL max_difficulty = LVL.NULL;

	protected String getDefaultMaxDifficulty() {
		return LVL.CYAN.getName().toUpperCase();
	}

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
			updateGUITools(false);

		new UnlockedToolMaxLevelCondition().registerValue(this, getMaxDifficulty());
	}

	private void loadMaxDifficulty() {
		if (getMaxDifficulty() != LVL.NULL)
			return;

		String txtDifficulty = new FileTask().read(userData, getName() + "MaxLvl", file);

		// if the file "characteristics" has no value for the tool, create one
		if (txtDifficulty.isEmpty()) {
			registerDefaultCharacteristic(new FormattedString().capitalize(getName()));
			txtDifficulty = getDefaultMaxDifficulty();
		}

		getTool().max_difficulty = LVL.valueOf(txtDifficulty);
		updateGUITools(false);
	}

	///// start /////

	private LVL start_difficulty;

	protected String getDefaultStartDifficulty() {
		return LVL.NULL.getName().toUpperCase();
	}

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
			updateGUITools(false);
	}

	private void loadStartDifficulty() {
		if (getStartDifficulty() != null)
			return;

		String txtDifficulty = new FileTask().read(userData, getName() + "StartLvl", file);

		// if the file "characteristics" has no value for the tool, create one
		if (txtDifficulty.isEmpty()) {
			registerDefaultCharacteristic(new FormattedString().capitalize(getName()));
			txtDifficulty = getDefaultStartDifficulty();
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
		setDifficulty(difficulty, false);
	}
	
	public void setDifficulty(LVL difficulty, boolean forbiddenUpgradeAllowed) {
		boolean blinking = getTool().difficulty == LVL.NULL;

		if (new DifficultyComparator().isForbiddenUpgrade(getMaxDifficulty(), difficulty) && forbiddenUpgradeAllowed == false)
			getTool().difficulty = getMaxDifficulty();
		else
			getTool().difficulty = difficulty;

		new UnlockedToolCondition().registerValue(this, getDifficulty());
		updateGUITools(blinking);
	}

	private void updateGUITools(boolean blinking) {
		HUDTools guiTool = null;

		for (HUD tempGUI : HUDList.getList().getList()) {
			if (tempGUI instanceof HUDTools == false)
				continue;

			guiTool = (HUDTools) tempGUI;
			break;
		}

		if (guiTool == null)
			return;

		ToolPlayer tool = blinking ? this : null;
		guiTool.loadImages(tool);
	}

	////////// CRITICAL ////////////

	private int criticalChance = getDefaultCriticalChance();

	protected int getDefaultCriticalChance() {
		return 15;
	}
	
	public int getCriticalChance() {
		return getTool().criticalChance;
	}

	public void setCriticalChance(int chance) {
		getTool().criticalChance = chance;
	}

	private void loadCriticalChance() {
		if (getCriticalChance() != getDefaultCriticalChance())
			return;

		String txtCriticalChance = new FileTask().read(userData, getName() + "CriticalChance", file);

		// if the file "characteristics" has no value for the tool, create one
		if (txtCriticalChance.isEmpty()) {
			registerDefaultCharacteristic(new FormattedString().capitalize(getName()));
			txtCriticalChance = getDefaultCriticalChance() + "%";
		}

		getTool().criticalChance = Integer.valueOf(txtCriticalChance.replace("%", ""));
	}

}
