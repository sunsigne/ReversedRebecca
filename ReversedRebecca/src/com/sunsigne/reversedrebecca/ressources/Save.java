package com.sunsigne.reversedrebecca.ressources;

import java.util.Set;
import java.util.TreeSet;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.piranha.condition.global.SavedCondition;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveEraserList;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveList;

public class Save {

	private String file = "userdata/save.csv";
	private String char_file = "userdata/characteristics.csv";

	////////// LEVEL ////////////

	private String lvl0 = "rebeccas_room_0";

	public String getLevel(boolean menu) {
		if (menu)
			return new FileTask().read("currentlvlmenu", file);
		else
			return new FileTask().read("currentlvl", file);
	}

	public void registerNextLevel(String lvlmenu, String lvl) {
		new FileTask().write("currentlvlmenu", file, lvlmenu);
		new FileTask().write("currentlvl", file, lvl);
	}

	////////// READ ////////////

	public void loadSave() {

		String[] data = new FileTask().read(file).split(System.getProperty("line.separator"));

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

		String[] oldData = new FileTask().read(file).split(System.getProperty("line.separator"));

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

	private void updateCharacteristics() {
		
		FileTask task = new FileTask();
		
		// tools
		var list = ToolList.getList();
		for (ToolPlayer tempTool : list.getList()) {
			// update the max lvl
			task.write(tempTool.getName() + "MaxLvl", char_file, tempTool.getMaxDifficulty().getName().toUpperCase());
			// update the start lvl
			task.write(tempTool.getName() + "StartLvl", char_file, tempTool.getStartDifficulty().getName().toUpperCase());
		}
	}

	////////// DELETE ////////////

	public void resetProgression() {
		String nextLine = System.getProperty("line.separator");
		String lvlmenu = "currentlvlmenu=" + lvl0;
		String lvl = "currentlvl=" + lvl0;

		new FileTask().write(file, lvlmenu + nextLine + lvl);
		new FileTask().write(char_file, "");
	}

	// the name is alarming but it just erase some specific intended data
	public void eraseSave() {

		// if nothing to erase
		var list = SaveEraserList.getList();
		if (list.getList().isEmpty())
			return;

		String data = new FileTask().read(file);

		// removing targeted data
		for (String tempString : list.getList()) {
			data = data.replace(System.getProperty("line.separator") + tempString, "");
		}

		// registering the result
		new FileTask().write(file, data);
	}

}
