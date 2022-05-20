package com.sunsigne.reversedrebecca.ressources;

import java.util.Set;
import java.util.TreeSet;

import com.sunsigne.reversedrebecca.piranha.condition.global.SavedCondition;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveEraserList;
import com.sunsigne.reversedrebecca.piranha.request.memory.SaveList;

public class Save {

	private String file = "userdata/save.csv";

	////////// READ ////////////

	public void loadSave() {

		// if nothing to load
		if (file.isEmpty())
			return;

		String[] data = new FileTask().read(file).split(System.getProperty("line.separator"));

		for (String tempDatum : data) {
			new SavedCondition().registerValue(tempDatum);
		}
	}

	////////// WRITE ////////////

	public void registerSave() {

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

	////////// DELETE ////////////

	public void resetProgression() {
		new FileTask().write(file, "");
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
