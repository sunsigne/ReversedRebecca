package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GameTimer implements Updatable, RenderFree {

	public GameTimer(int timeInTicks) {
		this(timeInTicks, null);
	}

	public GameTimer(int timeInTicks, GenericListener listener) {
		if (World.get() == null)
			return;

		World.get().getHandler().addObject(this);
		this.time = timeInTicks;
		this.listener = listener;
	}

	////////// TIMER ////////////

	private boolean ready;

	public boolean isReady() {
		return ready;
	}

	////////// TICK ////////////

	private int time;

	private GenericListener listener;

	@Override
	public void tick() {
		time--;

		if (time <= 0) {
			getHandler().removeObject(this);
			ready = true;
			if (listener != null)
				listener.doAction();
		}
	}

}
