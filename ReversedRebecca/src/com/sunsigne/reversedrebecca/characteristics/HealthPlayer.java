package com.sunsigne.reversedrebecca.characteristics;

import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class HealthPlayer {

	private String file = "characteristics.csv";
	private boolean userData = true;
	private Player player;

	public HealthPlayer(Player player) {
		this.player = player;
	}

	private void createCharacteristic(String text, String value) {
		String content = new FileTask().read(userData, file);
		String new_content = text + "=" + value + System.getProperty("line.separator") + content;
		new FileTask().write(file, new_content);
	}

	public void loadHealth() {
		String txtMaxHp = new FileTask().read(userData, "MaxHp", file);

		// if the file "characteristics" has no value for the hp, create one
		if (txtMaxHp.isEmpty()) {
			txtMaxHp = "2";
			createCharacteristic(System.getProperty("line.separator") + "MaxHp", txtMaxHp);
		}

		player.setMaxHp(Integer.parseInt(txtMaxHp));
		player.setFullHp();
	}

	public void registerHealth() {
		new FileTask().write("MaxHp", file, String.valueOf(player.getMaxHp()));
	}

}
