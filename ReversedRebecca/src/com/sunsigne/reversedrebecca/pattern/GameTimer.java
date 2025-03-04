package com.sunsigne.reversedrebecca.pattern;

import com.sunsigne.reversedrebecca.pattern.listener.GenericListener;
import com.sunsigne.reversedrebecca.ressources.layers.LAYER;
import com.sunsigne.reversedrebecca.system.mainloop.Handler;
import com.sunsigne.reversedrebecca.system.mainloop.PhysicFree;
import com.sunsigne.reversedrebecca.system.mainloop.RenderFree;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GameTimer implements Updatable, PhysicFree, RenderFree {

	public GameTimer(int timeInTicks) {
		this(timeInTicks, false, null);
	}

	public GameTimer(int timeInTicks, GenericListener listener) {
		this(timeInTicks, false, listener);
	}

	public GameTimer(int timeInTicks, boolean absolute, GenericListener listener) {
		if (timeInTicks <= 0) {
			if (listener != null)
				listener.doAction();
			return;
		}

		if (absolute)
			this.handler = LAYER.DEBUG.getHandler();
		else if (World.get() != null)
			this.handler = World.get().getLayer(false).getHandler();
		else
			return;

		handler.addObject(this, false);

		this.time = timeInTicks;
		this.listener = listener;
	}

	private Handler handler;

	////////// TIMER ////////////

	private boolean ready;

	public boolean isReady() {
		return ready;
	}

	public void destroy() {
		handler.removeObject(this, false);
	}

	////////// TICK ////////////

	private int time;

	private GenericListener listener;

	@Override
	public void tick() {
		time--;

		if (time <= 0) {
			destroy();
			ready = true;
			if (listener != null)
				listener.doAction();
			listener = null;
		}
	}

}
