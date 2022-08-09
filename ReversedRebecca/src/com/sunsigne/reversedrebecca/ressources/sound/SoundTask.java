package com.sunsigne.reversedrebecca.ressources.sound;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.sunsigne.reversedrebecca.Infos;
import com.sunsigne.reversedrebecca.ressources.FileTask;

public class SoundTask {

	////////// SOUNDTYPE ////////////

	public enum SOUNDTYPE {
		MUSIC, SOUND, VOICE, ERROR;

		private String getPath() {
			switch (this) {
			case MUSIC:
				return "music/";
			case SOUND:
				return "sound/";
			case VOICE:
				return "voice/";
			default:
				return "";
			}
		}
	}

	////////// SOUND ////////////

	public void play(SOUNDTYPE soundType, String path) {
		play(soundType, getVolume(soundType), path);
	}

	// prefere the method right above this one : the user must decide the volume,
	// not you (unless you know what you're doing)
	public void play(SOUNDTYPE soundType, double volume, String path) {
		if (path == null)
			return;

		String path0 = "/" + new FileTask().getRessourcesPath() + "audio/" + soundType.getPath() + path + ".wav";
		Clip soundclip;

		try {
			// File file = new FileTask().getFile((new File(Infos.LOC.toURI())).getParent()
			// + path0);
			// URL url = file.toURI().toURL();
			URL url = new File((new File(Infos.LOC.toURI())).getParent() + path0).toURI().toURL();
			AudioInputStream clip = AudioSystem.getAudioInputStream(url);

			if (soundType == SOUNDTYPE.MUSIC) {
				musicclip = AudioSystem.getClip();
				musicclip.open(clip);
				setVol(volume, musicclip, true);
				musicclip.loop(Clip.LOOP_CONTINUOUSLY);
			}

			else {
				soundclip = AudioSystem.getClip();
				soundclip.open(clip);
				setVol(volume, soundclip, false);
				soundclip.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (soundType == SOUNDTYPE.VOICE)
				play(SOUNDTYPE.VOICE, "error");
			else
				play(SOUNDTYPE.ERROR, "nope");
		}
	}

	private double getVolume(SOUNDTYPE soundType) {
		switch (soundType) {
		case MUSIC:
			return VolumeMusic.getVolume();
		case SOUND:
			return VolumeSound.getVolume();
		case VOICE:
			return VolumeVoice.getVolume();
		case ERROR:
			return 1;
		}

		return 1;
	}

	// volume between 0.0 and 2.0 (goes up to 4.0 because of VolumeMain)
	private void setVol(double volume, Clip clip, boolean delay) {
		try {
			int pos = clip.getFramePosition();
			FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float dB = (float) (Math.log((volume * VolumeMain.getVolume()) / 2) / Math.log(10) * 20);
			gain.setValue(dB);
			if (!delay)
				clip.setFramePosition(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	////////// MUSIC ////////////

	private static Clip musicclip;

	public void changeMusicVol(double newvolume) {
		setVol(newvolume, musicclip, true);
	}

	public void stopMusic() {
		if (musicclip != null)
			musicclip.close();
	}

}