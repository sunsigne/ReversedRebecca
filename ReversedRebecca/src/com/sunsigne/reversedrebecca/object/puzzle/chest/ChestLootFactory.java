package com.sunsigne.reversedrebecca.object.puzzle.chest;

import javax.swing.JOptionPane;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.Conductor;

public class ChestLootFactory {

	// shouldn't no be used directly, is called when a ChestCard is created
	public ChestLoot createLoot(ChestCard card, String lootData) {

		ToolPlayer tool = getTool(lootData);

		// loot is a tool
		if (tool != null) {

			// upgrading start lvl
			if (lootData.toLowerCase().contains("start"))
				return new ChestLootToolStartLvl(card, tool);

			// upgrading max lvl
			if (lootData.toLowerCase().contains("max"))
				return new ChestLootToolStartLvl(card, tool);

			// syntax error
			else
				stopApp(lootData);
		}

		stopApp(lootData);
		return null;
	}

	private void stopApp(String lootData) {
		new SoundTask().play(SOUNDTYPE.ERROR, "error");
		JOptionPane.showMessageDialog(null,
				"An error has occurred : " + lootData + " can't be resolved to an existing loot");
		new Conductor().stopApp();
	}

	private ToolPlayer getTool(String lootData) {

		var list = ToolList.getList();
		for (ToolPlayer tempTool : list.getList()) {
			if (lootData.toLowerCase().contains(tempTool.getName() + "_"))
				return tempTool;
		}
		return null;
	}

}
