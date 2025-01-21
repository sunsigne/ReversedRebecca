package com.sunsigne.reversedrebecca.ressources.sound;

import javax.sound.sampled.Clip;

import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class MusicTransition implements Updatable, PhysicFree, RenderFree {

	protected static Clip fadingClip;
	protected static Clip currentClip;
	private boolean transition;

	protected MusicTransition(Clip newClip, boolean transition) {
		destroy();
		MusicTransition.fadingClip = currentClip;
		MusicTransition.currentClip = newClip;
		this.transition = transition;

		LAYER.DEBUG.addObject(this);
	}

	////////// VOLUME ////////////

	private final double MAX_VOLUME = VolumeMusic.getVolume();
	private double fadingVolume = VolumeMusic.getVolume();
	private double growingVolume = 0;

	private void fadeVolume() {
		if(transition == false)
			fadingVolume = 0;
			
		fadingVolume = fadingVolume - 0.015d;
		if (fadingVolume < 0)
			fadingVolume = 0;
	}

	private void growVolume() {
		if (transition == false)
			growingVolume = MAX_VOLUME;

		growingVolume = growingVolume + 0.03d;
		if (growingVolume > MAX_VOLUME)
			growingVolume = MAX_VOLUME;
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		fadeVolume();
		growVolume();

		new SoundTask().setVol(fadingVolume, fadingClip, true);
		new SoundTask().changeMusicVol(growingVolume);

		destroyWhenDone();
	}

	private final int STOP_TIME = 60;
	private int time;
	private boolean stop;

	private void destroyWhenDone() {
		if (fadingVolume == 0)
			stop = true;

		if (stop == false)
			return;

		time++;

		if (time >= STOP_TIME)
			destroy();
	}

	public void destroy() {
		LAYER.DEBUG.getHandler().removeObject(this);

		if (fadingClip != null)
			fadingClip.close();
	}

}
