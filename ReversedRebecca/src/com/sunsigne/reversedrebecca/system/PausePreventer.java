package com.sunsigne.reversedrebecca.system;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.object.DisabledPauseObject;
import com.sunsigne.reversedrebecca.pattern.GameTimer;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMusic;
import com.sunsigne.reversedrebecca.system.mainloop.Game;

public class PausePreventer {

	public static PAUSE_STATE state;
	private static HashMap<String, Integer> map = new HashMap<>();

	public void loadRessources() {
		map.put("dave_theme_gunshot", Game.SEC * (60 + 31));
		map.put("c_est_moi_qui_domine", Game.SEC * (120 + 46));
	}

	public void createDisabledPauseObject() {
		DisabledPauseObject noPause = new DisabledPauseObject();
		LAYER.DEBUG.addObject(noPause);
	}

	public void analyzeMusic(String musicName) {
		if (musicName == null)
			return;

		Integer timer = map.get(musicName);
		if (timer == null)
			return;

		removeDisabledPauseObject();
		state = PAUSE_STATE.MUSIC;
		VolumeMusic volume = new VolumeMusic();
		volume.preventNoMusicDuringScenarioMusic();

		PausePreventer.timer = new GameTimer(timer, true, () -> {
			state = null;
			volume.refreshVolume();
		});
	}

	private static GameTimer timer;

	public void removeDisabledPauseObject() {
		if (timer != null) {
			new VolumeMusic().refreshVolume();
			timer.destroy();
		}
		state = null;
	}

	////////// PAUSE STATE ////////////

	public enum PAUSE_STATE {
		MUSIC("DisabledPauseMusic"), WIMP("DisabledPauseWimp"), PUSHUPS("DisabledPausePushUps");

		private String valueToRead;

		PAUSE_STATE(String valueToRead) {
			this.valueToRead = valueToRead;
		}

		public String getValueToRead() {
			return valueToRead;
		}
	}

}
