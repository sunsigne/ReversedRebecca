package com.sunsigne.reversedrebecca.system;

import com.sunsigne.reversedrebecca.object.DisabledPauseObject;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class PausePreventer {

	public static PAUSE_STATE state;

	public static void createDisabledPauseObject() {
		DisabledPauseObject noPause = new DisabledPauseObject();
		LAYER.DEBUG.addObject(noPause);
	}

	////////// PAUSE STATE ////////////

	public enum PAUSE_STATE {
		MUSIC("DisabledPauseMusic"), WIMP("DisabledPauseWimp");

		private String valueToRead;

		PAUSE_STATE(String valueToRead) {
			this.valueToRead = valueToRead;
		}

		public String getValueToRead() {
			return valueToRead;
		}
	}

}
