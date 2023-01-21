package com.sunsigne.reversedrebecca.object.piranha.living.player;

import com.sunsigne.reversedrebecca.object.characteristics.interactive.Action;
import com.sunsigne.reversedrebecca.object.characteristics.interactive.Interactive;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.pattern.player.PlayerFinder;
import com.sunsigne.reversedrebecca.pattern.player.PlayerLayerChanger;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask;
import com.sunsigne.reversedrebecca.ressources.sound.SoundTask.SOUNDTYPE;
import com.sunsigne.reversedrebecca.system.controllers.keyboard.keys.ActionOneKey;

public class GoDownAction extends Action {

	public GoDownAction(Interactive interactive, String soundPath) {
		super(interactive, null, null, null, ActionOneKey.getKey());

		setName(new Translatable().getTranslatedText("GoDown", FilePath.ACTION));
		setListener(() -> {
			Player player = new PlayerFinder().getPlayer();
			player.setX(interactive.getX());
			player.setY(interactive.getY());

			player.setCanInterract(false);
			new GameTimer(2, () -> player.setCanInterract(true));

			new SoundTask().playSound(SOUNDTYPE.SOUND, soundPath);
			new PlayerLayerChanger().goesDown();
		});
	}

}
