package com.sunsigne.reversedrebecca.piranha.request.memory;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolList;
import com.sunsigne.reversedrebecca.characteristics.tools.ToolPlayer;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradeList;
import com.sunsigne.reversedrebecca.characteristics.upgrade.UpgradePlayer;
import com.sunsigne.reversedrebecca.object.characteristics.Difficulty.LVL;
import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.piranha.request.Request;
import com.sunsigne.reversedrebecca.piranha.request.RequestList;
import com.sunsigne.reversedrebecca.piranha.request.memory.data.SavedCharacteristic;
import com.sunsigne.reversedrebecca.piranha.request.memory.data.SavedCharacteristicsMap;

public class SavedCharacteristicsRequest implements Request {

	////////// REQUEST ////////////

	public SavedCharacteristicsRequest() {
		new RequestList().addRequest(this, getType());
	}

	private static Request request = new SavedCharacteristicsRequest();

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public String getType() {
		return "SAVED_CHARACTERISTICS";
	}

	@Override
	public boolean hasCompactWriting() {
		return true;
	}

	@Override
	public void doAction(PiranhaObject object, String target) {
		Player player = new PlayerFinder().getPlayer();

		switch (target.toUpperCase()) {

		case "SAVE":
			saveCharacteristics(player);
			break;
		case "LOAD":
			loadCharacteristics(player);
			break;
		case "RESET":
			SavedCharacteristicsMap.getMap().clear();
			break;
		}
	}

	private void saveCharacteristics(Player player) {
		if (player == null)
			return;

		var save = SavedCharacteristicsMap.getMap();
		var toolList = ToolList.getList();
		var upgradeList = UpgradeList.getList();

		// saving tool data
		for (ToolPlayer tempTool : toolList.getList()) {
			LVL sd = tempTool.getStartDifficulty();
			LVL md = tempTool.getMaxDifficulty();
			LVL d = tempTool.getDifficulty();
			int cc = tempTool.getCriticalChance();

			SavedCharacteristic data = new SavedCharacteristic(sd, md, d, cc);
			save.put(tempTool, data);
		}

		// saving upgrade data
		for (UpgradePlayer tempUpgrade : upgradeList.getList()) {
			SavedCharacteristic data = new SavedCharacteristic(tempUpgrade.getValue());
			save.put(tempUpgrade, data);
		}

		// saving hp data
		int maxHp = player.getMaxHp();
		int bonusHp = player.getBonusHp();
		SavedCharacteristic data = new SavedCharacteristic(maxHp, bonusHp);
		save.put(Infos.NAME, data);

	}

	private void loadCharacteristics(Player player) {
		if (player == null)
			return;

		var save = SavedCharacteristicsMap.getMap();
		var toolList = ToolList.getList();
		var upgradeList = UpgradeList.getList();

		// loading tool data
		for (ToolPlayer tempTool : toolList.getList()) {
			SavedCharacteristic savedTool = save.get(tempTool);
			if (savedTool == null)
				continue;

			tempTool.setStartDifficulty(savedTool.getStartDifficulty());
			tempTool.setMaxDifficulty(savedTool.getMaxDifficulty());
			tempTool.setDifficulty(savedTool.getDifficulty());
			tempTool.setCriticalChance(savedTool.getCriticalChance());
		}

		// loading upgrade data
		for (UpgradePlayer tempUpgrade : upgradeList.getList()) {
			SavedCharacteristic savedUpgrade = save.get(tempUpgrade);
			if (savedUpgrade == null)
				continue;

			tempUpgrade.setValue(savedUpgrade.getValue());
		}

		// loading hp data
		SavedCharacteristic savedHp = save.get(Infos.NAME);
		if (savedHp == null)
			return;

		player.setMaxHp(savedHp.getMaxHp());
		player.setBonusHp(savedHp.getBonusHp());
		player.setFullHp();
	}

}
