package com.sunsigne.reversedrebecca.object.puzzle.chest;

import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;

public class ChestLootFactory {

	////////// PUZZLE ////////////

	// shouldn't no be used directly, is called when a ChestCard is created
	public ChestLoot createLoot(ChestCard card, String lootData) {
		if (lootData == null)
			return new ChestLootError(card, lootData);

		ToolPlayer tool = getTool(lootData);
		if (tool != null)
			return getToolCard(card, lootData, tool);

		if (lootData.toLowerCase().contains("hp"))
			return getHpCard(card, lootData);

		if (lootData.toLowerCase().contains("upgrade"))
			return getUpgradeCard(card, lootData);

		return new ChestLootError(card, lootData);
	}

	////////// TOOL ////////////

	private ToolPlayer getTool(String lootData) {
		if (lootData == null)
			return null;

		var list = ToolList.getList();
		for (ToolPlayer tempTool : list.getList()) {
			if (lootData.toLowerCase().contains(tempTool.getName() + "_"))
				return tempTool;
		}
		return null;
	}

	private ChestLoot getToolCard(ChestCard card, String lootData, ToolPlayer tool) {
		if (lootData.toLowerCase().contains("start"))
			return new ChestLootToolStartLvl(card, tool);

		if (lootData.toLowerCase().contains("max"))
			return new ChestLootToolMaxLvl(card, tool);

		return new ChestLootError(card, lootData);
	}

	////////// HEALTH ////////////

	private ChestLoot getHpCard(ChestCard card, String lootData) {
		if (lootData.toLowerCase().contains("max"))
			return new ChestLootMaxHp(card);

		if (lootData.toLowerCase().contains("bonus"))
			return new ChestLootBonusHp(card);

		return new ChestLootError(card, lootData);
	}

	////////// UPGRADE ////////////

	private ChestLoot getUpgradeCard(ChestCard card, String lootData) {
		if (lootData.toLowerCase().contains("bombing_door"))
			return new ChestLootBombingDoor(card, getTool("bomb_"));

		return new ChestLootError(card, lootData);
	}

}
