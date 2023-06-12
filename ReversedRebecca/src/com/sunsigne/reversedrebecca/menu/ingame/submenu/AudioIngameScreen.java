package com.sunsigne.reversedrebecca.menu.ingame.submenu;

import java.util.HashMap;

import com.sunsigne.reversedrebecca.menu.ingame.MenuIngameScreen;
import com.sunsigne.reversedrebecca.object.buttons.TitleScreenText;
import com.sunsigne.reversedrebecca.object.buttons.VolumeScaleButton;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMain;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeMusic;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeSound;
import com.sunsigne.reversedrebecca.ressources.sound.VolumeVoice;
import com.sunsigne.reversedrebecca.system.controllers.gamepad.ButtonEvent;
import com.sunsigne.reversedrebecca.system.controllers.mouse.PresetMousePos;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class AudioIngameScreen extends MenuIngameSubMenuScreen {

	public AudioIngameScreen() {
		super(MAIN);
		loadText();
		createScaleButtons();
	}

	////////// USEFUL ////////////

	protected void add(Updatable object) {
		LAYER.MENU.addObject(object);
	}

	////////// NAME ////////////

	@Override
	public String getName() {
		return "audio";
	}

	////////// SUB MENU ////////////

	@Override
	protected MenuIngameScreen getPreviousMenu() {
		return new OptionsIngameScreen(BACK);
	}

	////////// TEXT ////////////

	private TitleScreenText volumeMainPct;
	private TitleScreenText volumeMusicPct;
	private TitleScreenText volumeSoundPct;
	private TitleScreenText volumeVoicePct;

	private void loadText() {

		int x = 325 + 90;
		int y = 503 + y_gap;

		createVolumes(x, y);
		createVolumePct(x + 790, y);
	}

	private void createVolumes(int x, int y) {
		add(new TitleScreenText(translate("VolumeMain"), x, y));
		add(new TitleScreenText(translate("VolumeMusic"), x, y + 104));
		add(new TitleScreenText(translate("VolumeSound"), x, y + 208));
		add(new TitleScreenText(translate("VolumeVoice"), x, y + 312));
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
		VolumeScaleButton button = null;

		button = new VolumeScaleButton(514 + y_gap, new VolumeMain());
		scale_buttons.put(MAIN, button);
		add(button);

		button = new VolumeScaleButton(618 + y_gap, new VolumeMusic());
		scale_buttons.put(MUSIC, button);
		add(button);

		button = new VolumeScaleButton(722 + y_gap, new VolumeSound());
		scale_buttons.put(SOUND, button);
		add(button);

		button = new VolumeScaleButton(826 + y_gap, new VolumeVoice());
		scale_buttons.put(VOICE, button);
		add(button);
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

	////////// PRESET MOUSE POS ////////////

	protected HashMap<PresetMousePos, VolumeScaleButton> scale_buttons = new HashMap<>();

	public static final PresetMousePos MAIN = new PresetMousePos(1090, 540 + y_gap);
	public static final PresetMousePos MUSIC = new PresetMousePos(1090, 640 + y_gap);
	public static final PresetMousePos SOUND = new PresetMousePos(1090, 740 + y_gap);
	public static final PresetMousePos VOICE = new PresetMousePos(1090, 840 + y_gap);

	////////// GAMEPAD ////////////

	@Override
	public void buttonPressed(ButtonEvent e) {
		if (pressingButton())
			return;

		if (isPresetNull())
			setPreset(MAIN);
		else if (e.getKey() == ButtonEvent.B) {
			setPreset(BACK, false);
			buttons.get(BACK).mousePressed(null);
		}

		else if (getPreset() == MAIN)
			mainPressed(e);
		else if (getPreset() == MUSIC)
			musicPressed(e);
		else if (getPreset() == SOUND)
			soundPressed(e);
		else if (getPreset() == VOICE)
			voicePressed(e);
		else if (getPreset() == BACK)
			backPressed(e);
	}

	@Override
	public void buttonReleased(ButtonEvent e) {
		if (e.getKey() != ButtonEvent.NULL_X)
			return;

		scale_buttons.get(MAIN).updateRequest(e);
		scale_buttons.get(MUSIC).updateRequest(e);
		scale_buttons.get(SOUND).updateRequest(e);
		scale_buttons.get(VOICE).updateRequest(e);
	}

	private void mainPressed(ButtonEvent e) {
		scale_buttons.get(MAIN).updateRequest(e);
		if (e.getKey() == ButtonEvent.DOWN)
			setPreset(MUSIC);
	}

	private void musicPressed(ButtonEvent e) {
		scale_buttons.get(MUSIC).updateRequest(e);
		if (e.getKey() == ButtonEvent.UP)
			setPreset(MAIN);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(SOUND);
	}

	private void soundPressed(ButtonEvent e) {
		scale_buttons.get(SOUND).updateRequest(e);
		if (e.getKey() == ButtonEvent.UP)
			setPreset(MUSIC);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(VOICE);
	}

	private void voicePressed(ButtonEvent e) {
		scale_buttons.get(VOICE).updateRequest(e);
		if (e.getKey() == ButtonEvent.UP)
			setPreset(SOUND);
		else if (e.getKey() == ButtonEvent.DOWN)
			setPreset(BACK);
	}

	private void backPressed(ButtonEvent e) {
		if (e.getKey() == ButtonEvent.UP)
			setPreset(VOICE);
		else if (e.getKey() == ButtonEvent.A)
			buttons.get(BACK).mousePressed(null);
	}

}
