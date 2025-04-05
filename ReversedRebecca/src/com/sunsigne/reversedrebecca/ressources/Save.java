package com.sunsigne.reversedrebecca.ressources;

import java.util.Set;
import java.util.TreeSet;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.pattern.ArrayCombiner;
import com.sunsigne.reversedrebecca.pattern.list.GameList;
import com.sunsigne.reversedrebecca.pattern.list.LISTTYPE;
import com.sunsigne.reversedrebecca.piranha.condition.global.SavedCondition;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveEraserList;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveList;
import com.sunsigne.reversedrebecca.world.World;
import com.sunsigne.reversedrebecca.world.lvlstats.Karma;
import com.sunsigne.reversedrebecca.world.lvlstats.LevelStats;

public class Save {

	private String file = "save.csv";
	private String dave_file = "dave.csv";
	private String char_file = "characteristics.csv";
	private boolean userData = true;

	////////// LEVEL ////////////

	private void createSaveFile() {
		var line = getSaveTemplate();
		String text = "";

		for (String tempLine : line.getList()) {
			text = text.concat(tempLine + System.getProperty("line.separator"));
		}

		new FileTask().write(file, text);
	}

	private GameList<String> getSaveTemplate() {
		var line = new GameList<String>(LISTTYPE.ARRAY);
		String surname = "surname";

		line.addObject("currentlvlmenu=" + FilePath.LVL000);
		line.addObject("currentlvl=" + FilePath.LVL000);
		line.addObject("surname_doug=" + surname);
		line.addObject("surname_custom1=" + surname);
		line.addObject("surname_custom2=" + surname);
		line.addObject("surname_custom3=" + surname);

		return line;
	}

	public String getLevel(boolean menu) {
		if (new FileTask().doesExist(userData, file) == false)
			createSaveFile();

		if (menu)
			return new FileTask().read(userData, "currentlvlmenu", file);
		else
			return new FileTask().read(userData, "currentlvl", file);
	}

	public void registerNextLevel(String lvlmenu, String lvl) {
		new FileTask().write("currentlvlmenu", file, lvlmenu);
		new FileTask().write("currentlvl", file, lvl);
	}

	////////// READ ////////////

	public void loadSave() {

		String[] saved_data = new FileTask().read(userData, file).split(System.getProperty("line.separator"));
		String[] dave_data = new FileTask().read(userData, dave_file).split(System.getProperty("line.separator"));
		String[] data = new ArrayCombiner<String>().combine(String.class, saved_data, dave_data);

		for (String tempDatum : data) {
			if (tempDatum.toLowerCase().contains("currentlvl"))
				continue;
			new SavedCondition().registerValue(tempDatum);
		}
	}

	////////// WRITE ////////////

	public void registerSave() {

		updateCharacteristics();

		// if nothing to save
		var list = SaveList.getList();
		if (list.getList().isEmpty())
			return;

		String[] oldData = new FileTask().read(userData, file).split(System.getProperty("line.separator"));

		// regroupd all data into a non-duplicated sorted alphabetically structure (set)
		Set<String> set = new TreeSet<>();

		// adding oldData into set
		for (String tempString : oldData) {
			set.add(tempString);
		}

		// adding newData into set
		for (String tempString : list.getList()) {
			set.add(tempString);
		}

		// registering the result
		String[] mergedData = new String[set.size()];
		mergedData = set.toArray(mergedData);
		String fileContent = String.join(System.getProperty("line.separator"), mergedData);
		new FileTask().write(file, fileContent);
	}

	public void registerDave(String data) {

		String[] oldData = new FileTask().read(userData, dave_file).split(System.getProperty("line.separator"));

		// regroupd all data into a non-duplicated sorted alphabetically structure (set)
		Set<String> set = new TreeSet<>();

		// adding oldData into set
		for (String tempString : oldData) {
			set.add(tempString);
		}

		// adding newData into set
		set.add(data);

		// registering the result
		String[] mergedData = new String[set.size()];
		mergedData = set.toArray(mergedData);
		String fileContent = String.join(System.getProperty("line.separator"), mergedData);
		new FileTask().write(dave_file, fileContent);
	}

	public void updateCharacteristics() {

		FileTask task = new FileTask();

		// karma
		LevelStats stats = World.get().getLevelStats();
		Karma karma = stats.getKarma();
		int value = karma.getValue() + getKarmaValueBasedOnYouAre(stats);
		if (karma.getValue() == -999 || getKarmaValueBasedOnYouAre(stats) == -999)
			value = -999;
		karma.setValue(value);
		karma.registerKarma();

		// tools
		var list = ToolList.getList();
		for (ToolPlayer tempTool : list.getList()) {
			// update the max lvl
			task.write(tempTool.getName() + "MaxLvl", char_file, tempTool.getMaxDifficulty().getName().toUpperCase());
			// update the start lvl
			task.write(tempTool.getName() + "StartLvl", char_file,
					tempTool.getStartDifficulty().getName().toUpperCase());
			// update critical chance
			task.write(tempTool.getName() + "CriticalChance", char_file,
					String.valueOf(tempTool.getCriticalChance() + "%"));
		}
	}

	private int getKarmaValueBasedOnYouAre(LevelStats stats) {
		switch (stats.getYouAre()) {
		case ANGELIC:
			return 2;
		case NICE:
			return 1;
		case NEUTRAL:
			return 0;
		case MEAN:
			return -1;
		case SADISTIC:
			return -2;
		case PSYCHOPATH:
			return -999;
		}

		return 0;
	}

	////////// DELETE ////////////

	public void resetProgression() {
		String nextLine = System.getProperty("line.separator");
		String lvlmenu = "currentlvlmenu=" + FilePath.LVL000;
		String lvl = "currentlvl=" + FilePath.LVL000;

		new FileTask().write(file, lvlmenu + nextLine + lvl);
		new FileTask().write(char_file, "");
	}

	// the name is alarming but it just erase some specific intended data
	public void eraseSave() {

		// if nothing to erase
		var list = SaveEraserList.getList();
		if (list.getList().isEmpty())
			return;

		String data = new FileTask().read(userData, file);

		// removing targeted data
		for (String tempString : list.getList()) {
			data = data.replace(System.getProperty("line.separator") + tempString, "");
		}

		// registering the result
		new FileTask().write(file, data);
	}

}
