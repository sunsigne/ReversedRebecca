package com.sunsigne.reversedrebecca.menu.lvlcomplete;

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

	private float time_in_tick;

	@Override
	public void tick() {
		if (running == false)
			return;

		time_in_tick++;
	}

	public String getTime() {
		float time_in_sec = time_in_tick / Game.SEC;
		return String.format("%.2f", time_in_sec);
	}

}
