package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.piranha.PiranhaObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.Player;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionThreeKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionTwoKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DialogueKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.DownKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.KeyAnalyzer;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.LeftKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.RightKey;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.UpKey;

public class FormattedString {

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
		String formatted_number = num < 10 ? "0" + num : String.valueOf(num);
		return formatted_number;
	}

	public String getNoSpecialCharacter(String text) {
		String no_accent = text.replace("�", "e").replace("�", "e").replace("�", "e").replace("�", "a");
		return no_accent.replace(",", ".").replace("�", "c");
	}

	public String capitalize(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

	public String replaceSpace(String text) {
		String no_space = text.replace(" ", "");
		return no_space.replace("#", " ");
	}

	public String replaceValues(String text) {
		if(text.contains("$$") == false)
			return text;
		
		String formatted_value = text;
		formatted_value = formatted_value.replace("$$up", getKeyText(UpKey.getKey()));
		formatted_value = formatted_value.replace("$$down", getKeyText(DownKey.getKey()));
		formatted_value = formatted_value.replace("$$left", getKeyText(LeftKey.getKey()));
		formatted_value = formatted_value.replace("$$right", getKeyText(RightKey.getKey()));
		formatted_value = formatted_value.replace("$$dialogue", getKeyText(DialogueKey.getKey()));
		formatted_value = formatted_value.replace("$$action1", getKeyText(ActionOneKey.getKey()));
		formatted_value = formatted_value.replace("$$action2", getKeyText(ActionTwoKey.getKey()));
		formatted_value = formatted_value.replace("$$action3", getKeyText(ActionThreeKey.getKey()));
		formatted_value = formatted_value.replace("$$user", getUser());
		
		return formatted_value;
	}

	private String getKeyText(int key) {
		String key_text = "[" + new KeyAnalyzer(key).getKeyText() + "]";
		return key_text;
	}

	private String getUser() {
		return System.getProperty("user.name");
	}

}