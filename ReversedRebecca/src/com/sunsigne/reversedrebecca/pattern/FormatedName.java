package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.object.extrabehaviors.ExtraBehaviorsObject;
import com.sunsigne.reversedrebecca.object.piranha.living.player.PiranhaPlayer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;

public class FormatedName {

	public String getName(ExtraBehaviorsObject object, String name) {

		String lower_name = name.toLowerCase();

		switch (lower_name) {

		case "player":
			PiranhaPlayer player = new PlayerFinder().getPlayer();
			if (player == null)
				return "error";
			else
				return player.getName();

		case "object":
			return object.getName();
		}

		return lower_name;
	}

}
