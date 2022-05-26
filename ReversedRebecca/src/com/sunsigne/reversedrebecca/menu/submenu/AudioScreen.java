package com.sunsigne.reversedrebecca.menu.submenu;

import com.sunsigne.reversedrebecca.menu.MenuScreen;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.VolumeScaleButton;
import com.sunsigne.reversedrebecca.ressources.lang.Translatable;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMain;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMusic;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeSound;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeVoice;

public class AudioScreen extends SubMenuScreen {

	public AudioScreen() {
		super();
		loadText();
		createScaleButtons();
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

	private TitleScreenText volumeMainPct;
	private TitleScreenText volumeMusicPct;
	private TitleScreenText volumeSoundPct;
	private TitleScreenText volumeVoicePct;

	private void loadText() {

		int x = 325 + 90;
		int y = 503;

		createVolumes(x, y);
		createVolumePct(x + 790, y);
	}

	private void createVolumes(int x, int y) {
		String text = null;

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

	private void createVolumePct(int x, int y) {

		// volume main
		volumeMainPct = new TitleScreenText("100%", x, y);
		LAYER.MENU.addObject(volumeMainPct);

		// volume music
		volumeMusicPct = new TitleScreenText("100%", x, y + 104);
		LAYER.MENU.addObject(volumeMusicPct);

		// volume sound
		volumeSoundPct = new TitleScreenText("100%", x, y + 208);
		LAYER.MENU.addObject(volumeSoundPct);

		// volume voice
		volumeVoicePct = new TitleScreenText("100%", x, y + 312);
		LAYER.MENU.addObject(volumeVoicePct);
	}

	private int getPercentage(double volume) {
		return (int) (volume * 100);
	}

	////////// BUTTONS ////////////

	private void createScaleButtons() {
		VolumeScaleButton scale = null;

		// volume main
		scale = new VolumeScaleButton(514, new VolumeMain());
		LAYER.MENU.addObject(scale);

		// volume music
		scale = new VolumeScaleButton(618, new VolumeMusic());
		LAYER.MENU.addObject(scale);

		// volume sound
		scale = new VolumeScaleButton(722, new VolumeSound());
		LAYER.MENU.addObject(scale);

		// volume voice
		scale = new VolumeScaleButton(826, new VolumeVoice());
		LAYER.MENU.addObject(scale);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		String text = null;

		// volume main
		text = getPercentage(VolumeMain.getVolume()) + "%";
		volumeMainPct.setText(text);
				
		// volume music
		text = getPercentage(VolumeMusic.getVolume()) + "%";
		volumeMusicPct.setText(text);
				
		// volume sound
		text = getPercentage(VolumeSound.getVolume()) + "%";
		volumeSoundPct.setText(text);
		
		// volume voice
		text = getPercentage(VolumeVoice.getVolume()) + "%";
		volumeVoicePct.setText(text);
	}

}
