package com.sunsigne.reversedrebecca.ressources.sound;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import com.sunsigne.reversedrebecca.object.characteristics.Position;
import com.sunsigne.reversedrebecca.ressources.FilePath;
import com.sunsigne.reversedrebecca.system.camera.CameraDependency;

public class SoundTask implements CameraDependency {

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

	public void playSoundIfCamera(Position position, String path) {
		if (CAMERA.isObjectVisible(position))
			play(SOUNDTYPE.SOUND, getVolume(SOUNDTYPE.SOUND), path, false, false);
	}

	public void playSound(SOUNDTYPE soundType, String path) {
		play(soundType, getVolume(soundType), path, false, false);
	}

	// avoid to use this method unless you know what you're doing
	public void play(SOUNDTYPE soundType, double volume, String path, boolean transition, boolean loop) {
		if (path == null)
			return;

		String path0 = "/" + FilePath.RESSOURCES_PATH + "audio/" + soundType.getPath() + path + ".wav";
		Clip soundclip;

		try {
			URL url = new File((new File(FilePath.LOC.toURI())).getParent() + path0).toURI().toURL();
			AudioInputStream clip = AudioSystem.getAudioInputStream(url);

			if (soundType == SOUNDTYPE.MUSIC) {
				if (musicName == path)
					return;

				musicName = path;
				Clip musicClip = AudioSystem.getClip();
				musicClip.open(clip);

				if (loop)
					musicClip.loop(Clip.LOOP_CONTINUOUSLY);
				else
					musicClip.start();

				setVol(0, musicClip, false);

				new MusicTransition(musicClip, transition);
			}

			else {
				soundclip = AudioSystem.getClip();
				soundclip.open(clip);
				setVol(volume, soundclip, false);
				soundclip.start();
			}

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (soundType == SOUNDTYPE.VOICE)
				playSound(SOUNDTYPE.VOICE, "error");
			else
				playSound(SOUNDTYPE.ERROR, "nope");
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

	public void playMusic(String path, boolean transition, boolean loop) {
		play(SOUNDTYPE.MUSIC, VolumeMusic.getVolume(), path, transition, loop);
	}

	public void changeMusicVol(double newvolume) {
		MusicTransition.currentClip.getMicrosecondLength();
		setVol(newvolume, MusicTransition.currentClip, true);
	}

	public void stopMusic(boolean transition) {
		musicName = null;
		new MusicTransition(null, transition);
	}

}