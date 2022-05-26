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
	}

	////////// SOUND ////////////

	public void play(SOUNDTYPE soundType, String path) {
		if (path == null)
			return;

		String path0 = "/ressources/audio/" + path + ".wav";
		Clip soundclip;
		double volume = getVolume(soundType);

		try {
			URL url = new File((new File(Infos.LOC.toURI())).getParent() + path0).toURI().toURL();
			AudioInputStream clip = AudioSystem.getAudioInputStream(url);
			soundclip = AudioSystem.getClip();
			soundclip.open(clip);
			setVol(volume, soundclip, false);
			soundclip.start();
		} catch (Exception e) {
			e.printStackTrace();
			if (soundType == SOUNDTYPE.VOICE)
				play(SOUNDTYPE.VOICE, "sound/voice/error");
			else
				play(SOUNDTYPE.ERROR, "sound/nope");
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

	// volume between 0.0 and 2.0
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
	/*
	 * ////////// MUSIC ////////////
	 * 
	 * private static Clip musicclip;
	 * 
	 * public void changeMusicVol(double newvolume) { setVol(newvolume, musicclip,
	 * true); }
	 * 
	 * public void stopMusic() { if (musicclip != null) musicclip.close(); }
	 * 
	 * public void playMusic(double volume, String path) {
	 * 
	 * stopMusic(); String path0 = "/ressources/audio/music/" + "" + path + ".wav";
	 * 
	 * try { URL url = new File((new File(Infos.LOC.toURI())).getParent() +
	 * path0).toURI().toURL(); AudioInputStream music =
	 * AudioSystem.getAudioInputStream(url); musicclip = AudioSystem.getClip();
	 * musicclip.open(music); setVol(volume, musicclip, true);
	 * musicclip.loop(Clip.LOOP_CONTINUOUSLY); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
}