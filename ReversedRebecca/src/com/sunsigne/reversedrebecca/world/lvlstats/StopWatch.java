package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.system.Snitch;
import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
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
		new Snitch().registerEntry("STOPWATCH:" + time / Game.SEC);
		Handler handler = getHandler();
		if (handler != null)
			handler.removeObject(this);
		return time / Game.SEC;
	}

}
