package com.sunsigne.reversedrebecca.system.handler;

public interface ITick {

	public default void activateT() {
		HandlerTick.getInstance().addObject(this);
	}

	void tick();

}
