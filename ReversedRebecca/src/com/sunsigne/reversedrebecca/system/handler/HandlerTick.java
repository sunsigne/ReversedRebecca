package com.sunsigne.reversedrebecca.system.handler;

import java.util.LinkedList;

import com.sunsigne.reversedrebecca.util.AnnotationBank.Singleton;

@Singleton
public class HandlerTick implements ITick {

	////////// SIGNELTON ////////////

	private HandlerTick() {
	}

	private static HandlerTick instance = null;

	public static HandlerTick getInstance() {
		if (instance == null)
			instance = new HandlerTick();
		return instance;
	}
	
	////////// MAP OR LIST ////////////

	private LinkedList<ITick> handler_tick_list = new LinkedList<>();

	public void addObject(ITick tickable) {
		if (tickable != null)
			handler_tick_list.add(tickable);
	}

	public void removeObject(ITick tickable) {
		if (tickable != null)
			handler_tick_list.remove(tickable);
	}

	////////// TICK ////////////

	@Override
	public void tick() {
		for (ITick tempTick : handler_tick_list)
			tempTick.tick();
	}

}
