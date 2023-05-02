package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class StopWatch implements Updatable, RenderFree {

	////////// STOPWATCH ////////////

	private boolean running = true;

	public void resume() {
		running = true;
	}

	public void stop() {
		running = false;
	}

	////////// TICK ////////////

	private float time;

	@Override
	public void tick() {
		if (running == false)
			return;

		time++;
	}

	protected float getTime() {
		removeObject();
		return time / Game.SEC;
	}

}
