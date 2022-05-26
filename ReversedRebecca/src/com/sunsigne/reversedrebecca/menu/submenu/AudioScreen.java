package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;

public class AudioScreen extends SubMenuScreen {

	public AudioScreen() {
		super();
		loadText();
	}

	////////// NAME ////////////

	public String getName() {
		return "audio";
	}

	////////// SUB MENU ////////////

	protected MenuScreen getPreviousMenu() {
		return new OptionsScreen();
	}

	////////// TEXT ////////////

	private void loadText() {
		String text = null;
		int x = 325 + 90;
		int y = 503;

		// volume main
		text = new Translatable().getTranslatedText("VolumeMain", file);
		TitleScreenText volumeMain = new TitleScreenText(text, x, y);
		LAYER.MENU.addObject(volumeMain);

		// volume music
		text = new Translatable().getTranslatedText("VolumeMusic", file);
		TitleScreenText volumeMusic = new TitleScreenText(text, x, y + 104);
		LAYER.MENU.addObject(volumeMusic);

		// volume sound
		text = new Translatable().getTranslatedText("VolumeSound", file);
		TitleScreenText volumeSound = new TitleScreenText(text, x, y + 208);
		LAYER.MENU.addObject(volumeSound);

		// volume voice
		text = new Translatable().getTranslatedText("VolumeVoice", file);
		TitleScreenText volumeVoice = new TitleScreenText(text, x, y + 312);
		LAYER.MENU.addObject(volumeVoice);
	}

}
