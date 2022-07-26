package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class FormatedString {

	public String getName(PiranhaObject object, String name) {
		String lower_name = name.toLowerCase();

		switch (lower_name) {
		case "player":
			Player player = new PlayerFinder().getPlayer();
			if (player == null)
				return "error";
			else
				return player.getName();

		case "object":
			return object.getName();
		}
		return lower_name;
	}

	// put a "0" in front of each number below 10
	public String getNumber(int num) {
		String formated_number = num < 10 ? "0" + num : String.valueOf(num);
		return formated_number;
	}

	public String getNoSpecialCharacter(String text) {
		String no_accent = text.replace("é", "e").replace("è", "e").replace("ê", "e");
		return no_accent.replace(",", ".");
	}

	public String capitalize(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

}
