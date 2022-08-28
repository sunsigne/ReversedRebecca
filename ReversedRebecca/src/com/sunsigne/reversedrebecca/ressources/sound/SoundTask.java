package com.sunsigne.reversedrebecca.ressources.sound;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import com.sunsigne.reversedrebecca.Infos;

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
		play(soundType, getVolume(soundType), path, false);
	}

	// prefere the method right above this one : the user must decide the volume,
	// not you (unless you know what you're doing)
	public void play(SOUNDTYPE soundType, double volume, String path, boolean transition) {
		if (path == null)
			return;

		String path0 = "/" + Infos.RESSOURCES_PATH + "audio/" + soundType.getPath() + path + ".wav";
		Clip soundclip;

		try {
			URL url = new File((new File(Infos.LOC.toURI())).getParent() + path0).toURI().toURL();
			AudioInputStream clip = AudioSystem.getAudioInputStream(url);

			if (soundType == SOUNDTYPE.MUSIC) {
				if (musicName == path)
					return;

				musicName = path;
				Clip musicClip = AudioSystem.getClip();
				musicClip.open(clip);
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
				setVol(0, musicClip, false);

				new MusicTransition(musicClip, transition);
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
	protected void setVol(double volume, Clip clip, boolean delay) {
		if (clip == null)
			return;

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

	protected static String musicName;

	public void changeMusicVol(double newvolume) {
		setVol(newvolume, MusicTransition.currentClip, true);
	}

	public void stopMusic(boolean transition) {
		new MusicTransition(null, transition);
	}

}