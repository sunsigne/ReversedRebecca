package com.sunsigne.reversedrebecca.object.piranha.living.player;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class GoUpAction extends Action {

	public GoUpAction(Interactive interactive, String soundPath) {
		super(interactive, null, null, null, ActionOneKey.getKey());

		setName(new Translatable().getTranslatedText("GoUp", FilePath.ACTION));
		setListener(() -> {
			Player player = new PlayerFinder().getPlayer();
			player.setX(interactive.getX());
			player.setY(interactive.getY());
			new SoundTask().playSound(SOUNDTYPE.SOUND, soundPath);
			new PlayerLayerChanger().goesUp();
		});
	}

}
