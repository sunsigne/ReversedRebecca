package com.sunsigne.reversedrebecca.pattern;

import java.awt.Graphics;

import com.sunsigne.reversedrebecca.system.mainloop.Game;
import com.sunsigne.reversedrebecca.system.mainloop.Updatable;
import com.sunsigne.reversedrebecca.world.World;

public class GameTimer implements Updatable {

	public GameTimer(int time) {
		if (World.get() == null)
			return;

		World.get().getHandler().addObject(this);
		this.time = time;
	}

	////////// TIMER ////////////

	private boolean ready;

	public boolean isReady() {
		return ready;
	}

	////////// TICK ////////////

	private int frame = Game.SEC;
	private int time;

	@Override
	public void tick() {
		frame--;

		if (frame <= 0) {
			frame = Game.SEC;
			time--;
		}

		if (time <= 0) {
			getHandler().removeObject(this);
			ready = true;
		}
	}

	////////// RENDER ////////////

	@Override
	public void render(Graphics g) {

	}

}
