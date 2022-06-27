package com.sunsigne.reversedrebecca.world.lvlstats;

import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;

public class StopWatch implements Updatable, RenderFree {

	public StopWatch(int fast, int meticulous) {
		this.fast_time = fast;
		this.meticulous_time = meticulous;
	}
	
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
		getHandler().removeObject(this);
		return time / Game.SEC;
	}
	
	////////// YOU ARE ////////////

	private int fast_time;
	private int meticulous_time;

	public boolean isFast() {
		return (time / Game.SEC) < fast_time;
	}
	
	public boolean isMeticulous() {
		return (time / Game.SEC) >= meticulous_time;
	}
	
}
